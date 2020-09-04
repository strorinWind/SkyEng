package ru.strorin.skyeng.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.strorin.skyeng.R
import ru.strorin.skyeng.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
