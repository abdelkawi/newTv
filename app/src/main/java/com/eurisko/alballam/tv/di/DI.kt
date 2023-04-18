package com.eurisko.alballam.tv.di

import android.content.Context
import android.content.SharedPreferences
import com.eurisko.alballam.tv.UserManager
import com.eurisko.alballam.tv.data.RemoteDataSource
import com.eurisko.alballam.tv.data.RemoteDataSourceImpl
import com.eurisko.alballam.tv.data.Repository
import com.eurisko.alballam.tv.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DI {
  @Provides
  @Singleton
  fun provideRepo(repositoryImpl: RepositoryImpl): Repository = repositoryImpl

  @Provides
  @Singleton
  fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource = remoteDataSourceImpl

  @Provides
  @Singleton
  fun providesUserManager(sharedPreferences: SharedPreferences): UserManager = UserManager(sharedPreferences)

  @Provides
  @Singleton
  fun providesSharedPref(@ApplicationContext context: Context): SharedPreferences {
    return context.getSharedPreferences("user", Context.MODE_PRIVATE)
  }
}