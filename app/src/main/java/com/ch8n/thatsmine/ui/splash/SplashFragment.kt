package com.ch8n.thatsmine.ui.splash

import android.content.Context
import androidx.compose.ui.platform.ComposeView
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