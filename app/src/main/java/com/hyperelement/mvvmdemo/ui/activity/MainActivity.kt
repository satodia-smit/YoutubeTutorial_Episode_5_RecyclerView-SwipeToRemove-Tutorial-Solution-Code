package com.hyperelement.mvvmdemo.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyperelement.mvvmdemo.R.layout
import kotlinx.android.synthetic.main.activity_main.*


private const val UPDATE_REQUEST_CODE = 123

class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setSupportActionBar(toolbar)


    }

}
