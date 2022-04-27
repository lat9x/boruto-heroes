package com.tuan.borutoheroes.di

import android.app.Application
import androidx.room.Room
import com.tuan.borutoheroes.data.local.BorutoDatabase
import com.tuan.borutoheroes.data.repository.LocalDataSourceImpl
import com.tuan.borutoheroes.domain.repository.LocalDataSource
import com.tuan.borutoheroes.util.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideBorutoDatabase(application: Application): BorutoDatabase {
        return Room.databaseBuilder(
            application,
            BorutoDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(db: BorutoDatabase): LocalDataSource {
        return LocalDataSourceImpl(db = db)
    }
}