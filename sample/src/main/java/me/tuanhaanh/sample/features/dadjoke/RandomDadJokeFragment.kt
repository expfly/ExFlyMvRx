package me.tuanhaanh.sample.features.dadjoke

import me.tuanhaanh.sample.core.BaseFragment
import me.tuanhaanh.sample.core.MvRxViewModel
import me.tuanhaanh.sample.core.simpleController
import me.tuanhaanh.sample.models.Joke
import me.tuanhaanh.sample.network.DadJokeService
import io.reactivex.schedulers.Schedulers
import me.tuanhaanh.mvx.*
import me.tuanhaanh.sample.views.basicRow
import me.tuanhaanh.sample.views.loadingRow
import me.tuanhaanh.sample.views.marquee
import org.koin.android.ext.android.inject

data class RandomDadJokeState(val joke: Async<Joke> = Uninitialized) : MvRxState

class RandomDadJokeViewModel(
    initialState: RandomDadJokeState,
    private val dadJokeService: DadJokeService
) : MvRxViewModel<RandomDadJokeState>(initialState) {
    init {
        fetchRandomJoke()
    }

    fun fetchRandomJoke() {
        dadJokeService.random().subscribeOn(Schedulers.io()).execute { copy(joke = it) }
    }

    companion object : MvRxViewModelFactory<RandomDadJokeViewModel, RandomDadJokeState> {

        override fun create(viewModelContext: ViewModelContext, state: RandomDadJokeState): RandomDadJokeViewModel {
            val service: DadJokeService by viewModelContext.activity.inject()
            return RandomDadJokeViewModel(state, service)
        }
    }
}

class RandomDadJokeFragment : BaseFragment() {
    private val viewModel: RandomDadJokeViewModel by fragmentViewModel()

    override fun epoxyController() = simpleController(viewModel) { state ->
        marquee {
            id("marquee")
            title("Dad Joke")
        }

        /**
         * Async overrides the invoke operator so we can just call it. It will return the value if
         * it is Success or null otherwise.
         */
        val joke = state.joke()
        if (joke == null) {
            loadingRow {
                id("loading")
            }
            return@simpleController
        }

        basicRow {
            id("joke")
            title(joke.joke)
            clickListener { _ -> viewModel.fetchRandomJoke() }
        }
    }
}