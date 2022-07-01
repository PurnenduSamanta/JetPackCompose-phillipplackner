package com.purnendu.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class Timer : ComponentActivity() {


    //Not completed yet
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {


            Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()) {
                TimerComposable(
                    100, Color.Red, inActiveBarColor = Color.Gray, activeBarColor = Color.Yellow,
                    modifier = Modifier
                        .width(500.dp)
                        .height(500.dp)
                )
            }

        }
    }

    @Composable
    fun TimerComposable(
        totalTime: Long,
        handleColor: Color,
        inActiveBarColor: Color,
        activeBarColor: Color,
        modifier: Modifier = Modifier,
        initialValue: Float = 0f,
        strokeWidth: Dp = 5.dp
    ) {
        var size by remember {
            mutableStateOf(IntSize.Zero)
        }

        var value by remember {
            mutableStateOf(initialValue)
        }

        var currentTime by remember {
            mutableStateOf(totalTime)
        }

        var isTimerRunning by remember {
            mutableStateOf(false)
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .padding(50.dp)
                .onSizeChanged {
                    size = it
                }
        ) {

            Canvas(modifier = modifier)
            {
                drawArc(
                    color = inActiveBarColor,
                    startAngle = -215f,
                    sweepAngle = 215f,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)

                )
                drawArc(
                    color = activeBarColor,
                    startAngle = 215f,
                    sweepAngle = 215f * value,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)

                )
                val center = Offset(size.width / 2f, size.height / 2f)
                val angle = (250f * value + 145f) * (PI / 180f).toFloat()
                val r = size.width / 2f
                val a = cos(angle) * r
                val b = sin(angle) * r
                drawPoints(
                    points = listOf(Offset(center.x + a, center.y + b)),
                    pointMode = PointMode.Points,
                    color = handleColor,
                    strokeWidth = (strokeWidth * 3f).toPx(),
                    cap = StrokeCap.Round
                )
            }


        }
    }


}