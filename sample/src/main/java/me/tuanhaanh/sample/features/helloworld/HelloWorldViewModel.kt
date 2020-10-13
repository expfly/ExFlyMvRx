package me.tuanhaanh.sample.features.helloworld

import me.tuanhaanh.mvx.MvRxState
import me.tuanhaanh.sample.core.MvRxViewModel

data class HelloWorldState(val title: HelloWorld = HelloWorld()) : MvRxState

// This is done to have a non primitive type element in the state object
data class HelloWorld (val value: String = "Hello World")

class HelloWorldViewModel(initialState: HelloWorldState) : MvRxViewModel<HelloWorldState>(initialState)
