package ru.geekbrains.nasa

import android.app.Application
import ru.geekbrains.nasa.di.AppComponent
import ru.geekbrains.nasa.di.DaggerAppComponent
import ru.geekbrains.nasa.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        // DaggerAppComponent создается сам
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}