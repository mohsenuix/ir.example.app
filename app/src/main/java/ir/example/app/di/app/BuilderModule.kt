package ir.example.app.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.example.app.ui.MainActivity
import ir.example.app.ui.activity.home.HomeActivity
import ir.example.app.ui.activity.home.HomeActivityModule


@Module()
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun bindHomeActivity() : HomeActivity

}