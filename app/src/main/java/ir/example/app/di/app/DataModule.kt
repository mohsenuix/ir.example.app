package ir.example.app.di.app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import ir.example.app.data.repository.RepositoryImp
import ir.example.app.domain.repository.Repository
import javax.inject.Singleton

/**
 * Module which provides all required dependencies about network and data
 */
@Module
object DataModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideRepository(): Repository {
        return RepositoryImp()
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

}