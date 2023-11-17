package com.example.exex3prat1

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    private lateinit var editTextX: EditText
    private lateinit var editTextY: EditText
    private lateinit var editTextZ: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextX = findViewById(R.id.nX)
        editTextY = findViewById(R.id.nY)
        editTextZ = findViewById(R.id.nZ)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val accelerationX = event.values[0]
            val accelerationY = event.values[1]
            val accelerationZ = event.values[2]

            editTextX.setText("X: $accelerationX")
            editTextY.setText("Y: $accelerationY")
            editTextZ.setText("Z: $accelerationZ")

            if (isSignificantAcceleration(accelerationX, accelerationY, accelerationZ)) {
                val intent = Intent(this, MessageActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun isSignificantAcceleration(x: Float, y: Float, z: Float): Boolean {
        val magnitude = sqrt(x.toDouble().pow(2) + y.toDouble().pow(2) + z.toDouble().pow(2))
        val accelerationThreshold = 20

        return magnitude > accelerationThreshold
    }
}
