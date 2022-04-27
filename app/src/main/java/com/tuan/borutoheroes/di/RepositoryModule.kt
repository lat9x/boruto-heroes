package com.tuan.borutoheroes.di

import android.app.Application
import com.tuan.borutoheroes.data.repository.DataStoreOperationsImpl
import com.tuan.borutoheroes.data.repository.Repository
import com.tuan.borutoheroes.domain.repository.DataStoreOperations
import com.tuan.borutoheroes.domain.use_cases.UseCases
import com.tuan.borutoheroes.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.tuan.borutoheroes.domain.use_cases.get_hero.GetHeroUseCase
import com.tuan.borutoheroes.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.tuan.borutoheroes.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.tuan.borutoheroes.domain.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDataStoreOperations(
        application: Application
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = application)
    }

    @Singleton
    @Provides
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository = repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository = repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository = repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository = repository),
            getHeroUseCase = GetHeroUseCase(repository = repository)
        )
    }
}