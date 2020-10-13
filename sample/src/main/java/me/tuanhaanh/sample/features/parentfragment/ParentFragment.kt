package me.tuanhaanh.sample.features.parentfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.fragment_parent.*
import me.tuanhaanh.mvx.BaseMvRxFragment
import me.tuanhaanh.mvx.MvRxState
import me.tuanhaanh.mvx.fragmentViewModel
import me.tuanhaanh.mvx.withState
import me.tuanhaanh.sample.R
import me.tuanhaanh.sample.core.MvRxViewModel

data class CounterState(val count: Int = 0) : MvRxState
class CounterViewModel(state: CounterState) : MvRxViewModel<CounterState>(state) {
    fun incrementCount() = setState { copy(count = count + 1) }
}

class ParentFragment : BaseMvRxFragment() {

    private val viewModel: CounterViewModel by fragmentViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_parent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar.setupWithNavController(findNavController())
        textView.setOnClickListener {
            viewModel.incrementCount()
        }
        childFragmentManager.beginTransaction()
            .replace(R.id.childContainer, ChildFragment())
            .commit()
    }

    override fun invalidate() = withState(viewModel) { state ->
        textView.text = "ParentFragment: Count: ${state.count}"
    }
}