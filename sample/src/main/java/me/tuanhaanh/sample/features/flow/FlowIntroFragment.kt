package me.tuanhaanh.sample.features.flow

import androidx.navigation.fragment.findNavController
import me.tuanhaanh.mvx.activityViewModel
import me.tuanhaanh.sample.R
import me.tuanhaanh.sample.core.BaseFragment
import me.tuanhaanh.sample.core.simpleController
import me.tuanhaanh.sample.views.basicRow
import me.tuanhaanh.sample.views.marquee

class FlowIntroFragment : BaseFragment() {

    private val viewModel by activityViewModel(FlowViewModel::class)

    override fun epoxyController() = simpleController {
        marquee {
            id("marquee")
            title("Intro")
            subtitle("Set the initial counter value")
        }

        arrayOf(0, 10, 50, 100, 1_000, 10_000).forEach { count ->
            basicRow {
                id(count)
                title("$count")
                clickListener { _ ->
                    viewModel.setCount(count)
                    findNavController().navigate(R.id.action_flowIntroFragment_to_flowCounterFragment)
                }
            }
        }
    }
}