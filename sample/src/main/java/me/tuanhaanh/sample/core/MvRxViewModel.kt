package me.tuanhaanh.sample.core

import me.tuanhaanh.mvx.BaseMvRxViewModel
import me.tuanhaanh.mvx.MvRxState
import me.tuanhaanh.sample.BuildConfig


abstract class MvRxViewModel<S : MvRxState>(initialState: S) : BaseMvRxViewModel<S>(initialState, debugMode = BuildConfig.DEBUG)