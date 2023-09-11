package com.aydanilozyurek.kotlindelegates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

// by --> delegation
class MainActivity : AppCompatActivity(), LifeCycleLogger by LifeCycleLoggerImplementation(){
    // property delegate
    private val myVariable by lazy {
        println("this is a lazy implementation")
        10
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerLifecycleOwner(this)
    }
}

interface LifeCycleLogger{
    fun registerLifecycleOwner(owner : LifecycleOwner)
}

class LifeCycleLoggerImplementation : LifeCycleLogger, LifecycleEventObserver{
    override fun registerLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event)
        {
            Lifecycle.Event.ON_RESUME -> println("on resume executed")
            Lifecycle.Event.ON_PAUSE -> println("on pause executed")
            else -> Unit

        }
    }
}