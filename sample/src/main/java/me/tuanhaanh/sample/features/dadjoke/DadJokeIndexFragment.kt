package me.tuanhaanh.sample.features.dadjoke

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.util.Log
import android.view.View
import me.tuanhaanh.mvx.fragmentViewModel
import me.tuanhaanh.sample.R
import me.tuanhaanh.sample.core.BaseFragment
import me.tuanhaanh.sample.core.simpleController
import me.tuanhaanh.sample.views.basicRow
import me.tuanhaanh.sample.views.loadingRow
import me.tuanhaanh.sample.views.marquee

private const val TAG = "DadJokeIndexFragment"

class DadJokeIndexFragment : BaseFragment() {

    /**
     * This will get or create a new ViewModel scoped to this Fragment. It will also automatically
     * subscribe to all state changes and call [invalidate] which we have wired up to
     * call [buildModels] in [BaseFragment].
     */
    private val viewModel: DadJokeIndexViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Use viewModel.subscribe to listen for changes. The parameter is a shouldUpdate
         * function that is given the old state and new state and returns whether or not to
         * call the subscriber. onSuccess, onFail, and propertyWhitelist ship with MvRx.
         */
        viewModel.asyncSubscribe(DadJokeIndexState::request, onFail = { error ->
            Snackbar.make(coordinatorLayout, "Jokes request failed.", Snackbar.LENGTH_INDEFINITE)
                .show()
            Log.w(TAG, "Jokes request failed", error)
        })
    }

    override fun epoxyController() = simpleController(viewModel) { state ->
        marquee {
            id("marquee")
            title("Dad Jokes")
        }

        state.jokes.forEach { joke ->
            basicRow {
                id(joke.id)
                title(joke.joke)
                clickListener { _ ->
                    navigateTo(
                        R.id.action_dadJokeIndex_to_dadJokeDetailFragment,
                        DadJokeDetailArgs(joke.id)
                    )
                }
            }
        }

        loadingRow {
            // Changing the ID will force it to rebind when new data is loaded even if it is
            // still on screen which will ensure that we trigger loading again.
            id("loading${state.jokes.size}")
            onBind { _, _, _ -> viewModel.fetchNextPage() }
        }
    }
}