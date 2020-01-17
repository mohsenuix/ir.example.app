package ir.example.app

import android.content.Context
import androidx.multidex.MultiDex
import androidx.room.Room
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ir.example.app.data.db.Database
import ir.example.app.di.app.DaggerAppComponent
import timber.log.Timber


class App : DaggerApplication() {


    lateinit var db : Database

    override fun onCreate() {
        super.onCreate()
        timber()
//        db()
    }

    private fun timber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    private fun db(){
        db = Room.databaseBuilder(applicationContext, Database::class.java, "db")
            .fallbackToDestructiveMigration().build()
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}