package com.ch8n.thatsmine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.ui.tooling.preview.Preview
import com.ch8n.thatsmine.base.ThatsMineTheme
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.StoreConfig
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.prefs.RestoreFormDataStoreImpl
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.proto.SerializersSettings
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.proto.SettingsProtoStoreImpl
import com.ch8n.thatsmine.domain.models.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThatsMineTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThatsMineTheme {
        Greeting("Android")
    }
}