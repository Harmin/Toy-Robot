package com.myxplor.toyrobot

import android.app.Application
import com.myxplor.toyrobot.di.commonModule
import com.myxplor.toyrobot.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ToyRobot : Application() {

    override fun onCreate() {
        super.onCreate()

        // Koin setup
        val moduleList = listOf(
            commonModule,
            viewModelModule
        )

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@ToyRobot)
            modules(moduleList)
        }
    }
}