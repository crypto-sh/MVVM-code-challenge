package com.mobiquity.di



import com.core.api.MobiquityApi
import com.mobiquity.repositories.MainRepository
import com.mobiquity.repositories.MainRepositoryImpl
import com.mobiquity.viewModel.MainViewModel
import com.mobiquity.viewModel.MainViewModelImpl
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    @AppScope
    fun provideMainRepository(services : MobiquityApi) : MainRepository {
        return MainRepositoryImpl(services)
    }

    @Provides
    @AppScope
    fun provideMainViewModel(repository: MainRepository) : MainViewModel {
        return MainViewModelImpl(repository)
    }

}