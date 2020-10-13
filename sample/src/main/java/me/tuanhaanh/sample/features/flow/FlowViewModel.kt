package me.tuanhaanh.sample.features.flow

import me.tuanhaanh.mvx.MvRxState
import me.tuanhaanh.mvx.PersistState
import me.tuanhaanh.sample.core.MvRxViewModel

/**
 * [PersistState] will persist the count if Android kills the process in the background
 * and restores it in a new process.
 */
data class FlowState(@PersistState val count: Int = 0) : MvRxState

class FlowViewModel(initialState: FlowState) : MvRxViewModel<FlowState>(initialState) {

    fun setCount(count: Int) = setState { copy(count = count) }
}