package com.miniprojet.flickrandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miniprojet.flickrandroid.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}