package com.example

import android.app.Application
import com.example.core.data.SettingsRepository

class BPCApplication : Application() {
    lateinit var settingsRepository: SettingsRepository
        private set

    override fun onCreate() {
        super.onCreate()
        settingsRepository = SettingsRepository(this)
    }
}
