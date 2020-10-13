package me.tuanhaanh.sample.features.helloworld

import me.tuanhaanh.mvx.fragmentViewModel
import me.tuanhaanh.sample.core.BaseFragment
import me.tuanhaanh.sample.core.simpleController
import me.tuanhaanh.sample.views.marquee

class HelloWorldEpoxyFragment : BaseFragment() {
    private val viewModel: HelloWorldViewModel by fragmentViewModel()

    override fun epoxyController() = simpleController(viewModel) { state ->
        marquee {
            id("marquee")
            title(state.title.value)
        }
    }
}
