package com.purnendu.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


class MaintainAllScreenSizes : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {


            val windowInfo = rememberWindowInfo()

            if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                )
                {
                    items(10)
                    {
                        Text(
                            text = "Item $it",
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.Green)
                                .padding(10.dp)
                        )

                    }

                    items(10)
                    {
                        Text(
                            text = "Item $it", modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.Blue)
                                .padding(10.dp)
                        )

                    }

                }
            } else {

                Row()
                {

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                    )
                    {
                        items(10)
                        {
                            Text(
                                text = "Item $it",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = Color.Green)
                                    .padding(10.dp)
                            )

                        }

                    }

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                    )
                    {
                        items(10)
                        {
                            Text(
                                text = "Item $it",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = Color.Blue)
                                    .padding(10.dp)
                            )

                        }

                    }


                }
            }


        }
    }

}


@Composable
fun rememberWindowInfo(): WindowInfo {
    val configuration = LocalConfiguration.current
    return WindowInfo(
        screenHeightInfo = when {
            configuration.screenHeightDp < 600 -> WindowInfo.WindowType.Compact
            configuration.screenHeightDp < 840 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenWidthInfo = when {
            configuration.screenWidthDp < 480 -> WindowInfo.WindowType.Compact
            configuration.screenWidthDp < 900 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenWidth = configuration.screenWidthDp.dp,
        screenHeight = configuration.screenHeightDp.dp

    )
}


data class WindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Dp,
    val screenHeight: Dp
) {
    sealed class WindowType {
        object Compact : WindowType()
        object Medium : WindowType()
        object Expanded : WindowType()
    }
}