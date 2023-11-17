package com.example.exex2prat2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null
    private var gyroscopeSensor: Sensor? = null

    private lateinit var textViewLight: TextView
    private lateinit var textViewGyroscope: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewLight = findViewById(R.id.nLuminosity)
        textViewGyroscope = findViewById(R.id.nGyroscope)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        if (lightSensor == null) {
            textViewLight.text = "Este dispositivo não possui um sensor de luminosidade."
        }

        if (gyroscopeSensor == null) {
            textViewGyroscope.text = "Este dispositivo não possui um sensor de giroscópio."
        }
    }

    override fun onResume() {
        super.onResume()

        lightSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }

        gyroscopeSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_LIGHT -> {
                val lightValue = event.values[0]
                textViewLight.text = "Nível de luminosidade: $lightValue lux"
            }
            Sensor.TYPE_GYROSCOPE -> {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                textViewGyroscope.text = "Giroscópio: x=$x, y=$y, z=$z"
            }
        }
    }
}
