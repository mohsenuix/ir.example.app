package ir.example.app.di.app

import android.app.Activity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ir.example.app.App
import ir.example.app.di.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        AndroidSupportInjectionModule::class,
        BuilderModule::class, ViewModelModule::class,
        DataModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent

    }

    val activityInjector: DispatchingAndroidInjector<Activity>
}