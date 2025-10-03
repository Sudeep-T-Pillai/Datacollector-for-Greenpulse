package com.greenpulse.datacollector.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class AccelerometerListener : SensorEventListener {
    private val _acceroData = MutableStateFlow<FloatArray?>(null)
    val sensorData: StateFlow<FloatArray?> = _acceroData.asStateFlow()
    private val gravity = FloatArray(3)
    private val linearAcceleration = FloatArray(3)
    var timestamp: Long = 0
    var values: FloatArray = FloatArray(3)

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val alpha = 0.8f

            // Low-pass filter for gravity
            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0]
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1]
            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2]

            // High-pass filter for linear acceleration
            linearAcceleration[0] = event.values[0] - gravity[0]
            linearAcceleration[1] = event.values[1] - gravity[1]
            linearAcceleration[2] = event.values[2] - gravity[2]

            // Save timestamp (UTC epoch millis)
            timestamp = System.currentTimeMillis()

            // Save filtered values
            values = linearAcceleration.copyOf()
            _acceroData.value = linearAcceleration.copyOf()
            Log.d("Accelerometer", "X=${values[0]}, Y=${values[1]}, Z=${values[2]}, time=$timestamp")
        }
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No-op
    }
}
