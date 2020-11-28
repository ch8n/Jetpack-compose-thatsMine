package com.ch8n.thatsmine.ui.splash

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.ch8n.thatsmine.R
import com.ch8n.thatsmine.base.skeletal.ComposeFragment

class SplashFragment : ComposeFragment() {

    override fun Compose(context: Context): ComposeView {
        return ComposeView(context).apply {
            setContent {
                SplashView()
            }
        }
    }

    companion object {
        fun newInstance() = SplashFragment()
    }


}