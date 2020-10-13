package me.tuanhaanh.sample.features.parentfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.tuanhaanh.mvx.BaseMvRxFragment
import me.tuanhaanh.mvx.withState
import kotlinx.android.synthetic.main.fragment_parent.textView
import me.tuanhaanh.mvx.parentFragmentViewModel
import me.tuanhaanh.sample.R

class ChildFragment : BaseMvRxFragment() {

    private val viewModel: CounterViewModel by parentFragmentViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textView.setOnClickListener {
            viewModel.incrementCount()
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        textView.text = "ChildFragment: Count: ${state.count}"
    }
}