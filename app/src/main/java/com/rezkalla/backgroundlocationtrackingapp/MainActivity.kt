package com.rezkalla.backgroundlocationtrackingapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.rezkalla.backgroundlocationtrackingapp.location.LocationService
import com.rezkalla.data.SettingRepository
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var switchEnable: Switch
    private var settingRepository: SettingRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            0
        )
        btnStart = findViewById(R.id.buttonStart)
        btnStop = findViewById(R.id.buttonStop)
        switchEnable = findViewById(R.id.switch1)
        settingRepository = SettingRepository(this.getPreferences(MODE_PRIVATE))
        btnStart.setOnClickListener {
            Intent(applicationContext, LocationService::class.java).apply {
                action = LocationService.ACTION_START
                startService(this)
            }
        }
        btnStop.setOnClickListener {
            Intent(applicationContext, LocationService::class.java).apply {
                action = LocationService.ACTION_STOP
                startService(this)
            }
        }
        switchEnable.isChecked = settingRepository?.isEnabled ?: false
        switchEnable.setOnCheckedChangeListener { _, isChecked ->
            settingRepository?.isEnabled = isChecked
        }
        Toast.makeText(this, settingRepository?.nickName, Toast.LENGTH_SHORT).show()
    }

}