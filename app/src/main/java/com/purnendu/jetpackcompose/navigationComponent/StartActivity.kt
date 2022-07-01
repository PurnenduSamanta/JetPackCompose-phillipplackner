package com.purnendu.jetpackcompose.navigationComponent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class StartActivity : ComponentActivity() {


    //navigationComponent project starting from here
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigation()
        }
    }
}