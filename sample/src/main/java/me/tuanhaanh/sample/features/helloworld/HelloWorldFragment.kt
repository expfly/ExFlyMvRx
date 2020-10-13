package me.tuanhaanh.sample.features.helloworld

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import me.tuanhaanh.mvx.BaseMvRxFragment
import me.tuanhaanh.sample.R
import me.tuanhaanh.sample.views.Marquee

class HelloWorldFragment : BaseMvRxFragment() {
    private lateinit var marquee: Marquee

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_hello_world, container, false).apply {
            findViewById<Toolbar>(R.id.toolbar).setupWithNavController(findNavController())
            marquee = findViewById(R.id.marquee)
        }

    override fun invalidate() {
        marquee.setTitle("Hello World")
    }
}