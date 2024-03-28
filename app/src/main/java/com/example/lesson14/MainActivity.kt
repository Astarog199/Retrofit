package com.example.lesson14

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.lesson14.databinding.ActivityMainBinding
import com.example.lesson14.ui.main.BlankFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.but.setOnClickListener {
            supportFragmentManager.commit {
               replace<BlankFragment>(R.id.fragment_container)
               addToBackStack(BlankFragment::class.java.simpleName)
            }
        }
    }


}