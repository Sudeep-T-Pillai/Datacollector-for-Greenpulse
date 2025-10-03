package com.greenpulse.datacollector


import android.R.attr.label
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.greenpulse.datacollector.sensors.AccelerometerListener
import com.greenpulse.datacollector.ui.theme.DatacollectorTheme


class MainActivity : ComponentActivity() {
    private lateinit var accSensor: AccelerometerListener
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatacollectorTheme {
                Scaffold{ innerpadding ->
                Column (
                    verticalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                 EmptySpace(245)
                 TextBox("Transportation",Modifier.padding(innerpadding))
                 EmptySpace(15)
                 ButtonCompose(
                     Text = "Start",
                     onClick = {
                     Toast.makeText(this@MainActivity, "Starting the collection \uD83E\uDEE9", Toast.LENGTH_SHORT).show()
                         sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
                         accSensor = AccelerometerListener()
                         val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                         accelerometer?.let {
                             sensorManager.registerListener(
                                 accSensor,
                                 it,
                                 SensorManager.SENSOR_DELAY_NORMAL
                             )
                         }
                 },
                     modifier = Modifier
                         .fillMaxWidth() // take full width
                         .wrapContentWidth(Alignment.CenterHorizontally)) // center horizontally
                 ButtonCompose(
                     Text = "Stop",
                     onClick = {
                         Toast.makeText(this@MainActivity,"Stoping the collection \uD83E\uDEE9 ", Toast.LENGTH_SHORT).show()
                         sensorManager.unregisterListener(accSensor)
                     },

                     modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
                 )

                  }
               }
            }
        }
    }
}

@Composable
fun TextBox(text: String,modifier: Modifier = Modifier) {
    val swith = remember { androidx.compose.runtime.mutableStateOf("") }
    OutlinedTextField(
        value = swith.value,
        onValueChange = { swith.value = it },
        label = { Text("Enter your $text") },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
    var info = swith.value
}

@Composable
fun EmptySpace(emptySpace: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth().height(emptySpace.dp)
    )
}

@Composable
fun ButtonCompose(Text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(onClick = onClick, modifier = modifier) {
        Text("$Text")
    }
}