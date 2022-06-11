package com.purnendu.jetpackcompose

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2

class DraggableMusicKnob : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                MusicKnob(modifier = Modifier
                    .fillMaxSize(), onValueChanged = { print(it) })
            }


        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun MusicKnob(
        modifier: Modifier,
        limitingAngle: Float = 25f,
        onValueChanged: (Float) -> Unit
    ) {
        var rectangle: Rect? = null
        var rotation by remember {
            mutableStateOf(limitingAngle)
        }

        var touchX by remember {
            mutableStateOf(0f)
        }
        var touchY by remember {
            mutableStateOf(0f)
        }
        var centreX by remember {
            mutableStateOf(0f)
        }
        var centreY by remember {
            mutableStateOf(0f)
        }

        Image(
            painter = painterResource(id = R.drawable.music_knob),
            contentDescription = "Music Knob",
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    val windowBounds = it.boundsInWindow()
                    rectangle = windowBounds
                    centreX = windowBounds.size.width / 2f
                    centreY = windowBounds.size.height / 2f
                    Log.d("Coordinate", "centreX $centreX")
                    Log.d("Coordinate", "centreY $centreY")

                }
                .pointerInteropFilter { event ->

                    touchX = event.x
                    touchY = event.y

                    Log.d("Coordinate", "touchX ${event.x}")
                    Log.d("Coordinate", "touchY ${event.y}")
                    val angle = -atan2(centreX - touchX, centreY - touchY) * (180f / PI).toFloat()
                    val angle1 = atan2(centreY - touchY, -centreX + touchX) * (180f / PI).toFloat()
                    Log.d("Coordinate", "Angle is $angle")

                    when (event.action) {

                        MotionEvent.ACTION_DOWN,
                        MotionEvent.ACTION_MOVE -> {
                            if (angle !in -limitingAngle..limitingAngle) {
                                val fixedAngle = if (angle in -180f..-limitingAngle) {
                                    360f + angle
                                } else {
                                    angle
                                }
                                rotation = fixedAngle
                                val percetage =
                                    (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
                                onValueChanged(percetage)
                                true
                            } else
                                false
                        }
                        else -> false
                    }
                }
                .rotate(rotation)
        )

        Canvas(modifier = modifier)
        {
            drawLine(
                color = Color.Blue,
                start = Offset(centreX, centreY),
                end = Offset(touchX, touchY),
                strokeWidth = 8f
            )
            drawLine(
                color = Color.Blue,
                start = Offset(centreX, centreY),
                end = Offset(touchX, centreY),
                strokeWidth = 8f
            )
            drawLine(
                color = Color.Blue,
                start = Offset(touchX, centreY),
                end = Offset(touchX, touchY),
                strokeWidth = 8f
            )
//            rectangle?.topLeft?.let {
//                rectangle?.size?.let { it1 ->
//
//                    drawRect(
//                        topLeft = it,
//                        size = it1,
//                        color = Color.LightGray
//                    )
//
//                }
//            }
            drawPoints(
                points = listOf(
                    rectangle!!.topLeft,
                    rectangle!!.topRight, rectangle!!.bottomLeft, rectangle!!.bottomRight
                ),
                color = Color.Red,
                pointMode = PointMode.Points,
                strokeWidth = 8f
            )

        }


    }
}