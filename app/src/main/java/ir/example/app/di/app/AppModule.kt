package ir.example.app.di.app

import android.content.Context
import dagger.Module
import dagger.Provides
import ir.example.app.App
import ir.example.app.data.extractor.NetworkJobExecutor
import ir.example.app.di.scope.ForApplication
import ir.example.app.domain.executer.PostExecutionThread
import ir.example.app.domain.executer.UseCaseExecutor
import ir.example.app.domain.repository.ConnectivityManager
import ir.example.app.ui.executer.UiThreadExecutor
import ir.example.app.util.ConnectivityManagerImp
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideUseCaseExecutor(): UseCaseExecutor {
        return NetworkJobExecutor()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun postExecutionThread(): PostExecutionThread = UiThreadExecutor()


    @Provides
    @JvmStatic
    @ForApplication
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    @JvmStatic
    @Singleton
    fun provideConnectivityManager(connectivityManagerImp: ConnectivityManagerImp)
            : ConnectivityManager = connectivityManagerImp
}
