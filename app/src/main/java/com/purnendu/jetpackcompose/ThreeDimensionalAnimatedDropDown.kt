package com.purnendu.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ThreeDimensionalAnimatedDropDown : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Surface(
                color = Color.Yellow,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AnimatedDropDown(
                    text = "Animated Dropdown",
                    modifier = Modifier.padding(15.dp)
                )

            }

        }
    }

    @Composable
    fun AnimatedDropDown(
        modifier: Modifier = Modifier,
        text: String,
        initiallyOpened: Boolean = false,
    ) {

        var isOpen by remember {
            mutableStateOf(initiallyOpened)
        }

        val alpha = animateFloatAsState(
            targetValue = if (isOpen) 1f else 0f,
            animationSpec = tween(
                durationMillis = 300
            )
        )

        val rotateX = animateFloatAsState(
            targetValue = if (isOpen) 0f else -90f,
            animationSpec = tween(
                durationMillis = 300
            )
        )

        Column(
            modifier = modifier.fillMaxWidth()
        ) {

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = text,
                    color = Color.Green,
                    fontSize = 20.sp
                )

                Icon(imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop Down",
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable {
                            isOpen = !isOpen
                        }
                        .scale(if (isOpen) -1f else 1f))

            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        transformOrigin = TransformOrigin(0.5f, 0f)
                        rotationX = rotateX.value
                    }
                    .alpha(alpha.value)
                    .fillMaxHeight(0.4f)
                    .background(Color.Green)


            ) {
                Text(
                    text = "The text is now revealed",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 30.sp
                )

            }

        }


    }
}