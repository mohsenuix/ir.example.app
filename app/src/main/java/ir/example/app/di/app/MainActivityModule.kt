package ir.example.app.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.example.app.ui.fragments.photoDetail.PhotoDetailFragment

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributePhotoDetailFragment(): PhotoDetailFragment

}