package ir.example.app.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.example.app.ui.activity.home.HomeViewModel
import ir.example.app.ui.fragments.photoDetail.PhotoDetailViewModel

@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(clazz = PhotoDetailViewModel::class)
    abstract fun bindPhotoDetailViewModel(viewModel: PhotoDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(clazz = HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel


}