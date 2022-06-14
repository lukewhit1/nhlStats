package com.example.nhlstatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nhlstatsapp.databinding.ActivityMainBinding
import com.example.nhlstatsapp.view.fragment.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.main, SearchFragment()).commit()
    }
}