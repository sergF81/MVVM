package com.example.recycleviewtask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recycleviewtask.R
import com.example.recycleviewtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //загрузка нашего фрагмента ListFragment
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeHolder, ListFragment())
            .commit()
    }
}