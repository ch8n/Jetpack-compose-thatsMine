package com.ch8n.thatsmine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ch8n.thatsmine.databinding.FragmentSplashBinding
import com.ch8n.thatsmine.ui.splash.SplashFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .add(binding.container.id,SplashFragment.newInstance())
            .commit()
    }
}
