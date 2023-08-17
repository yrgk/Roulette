package com.example.roulette

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roulette.ui.theme.RouletteTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RouletteTheme {

                var rotation by remember { mutableStateOf(0f) }

                var number by remember { mutableStateOf("0") }

                val angle by animateFloatAsState(
                    targetValue = rotation,
                    tween(durationMillis = 2000),
                    finishedListener = {
                        var index = ((360f - (it % 360)) / 10f).roundToInt()

                        if (index == RouletteSectors.sectors.size ) {
                            number = RouletteSectors.sectors[0]
                        } else {
                            number = RouletteSectors.sectors[index]
                        }
                    }
                )


                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                        //.background(Color(42, 115, 42)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    
                    Text(
                        text = number,
                        fontSize = 60.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,

                        modifier = Modifier
                            .padding(top = 30.dp, bottom = 50.dp)
                    )


                    Box(contentAlignment = Alignment.TopCenter,) {

                        Image(
                            painter = painterResource(id = R.drawable.roulette),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(40.dp)
                                .rotate(angle)
                            )

                        Image(
                            painter = painterResource(id = R.drawable.arrow),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(start = 150.dp, end = 150.dp)
                        )

                    }

                    Button(
                        onClick = { rotation = (720..1080).random().toFloat() + angle },
                        modifier = Modifier.width(150.dp)//.height(0.dp)
                    ) {
                        Text(text = "Spin")
                    }
                    
                }
            }
        }
    }
}
