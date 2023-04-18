package com.eurisko.alballam.tv.di


import com.eurisko.alballam.tv.BuildConfig
import com.eurisko.alballam.tv.data.ApiService
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun loggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return interceptor
  }


  @Provides
  @Singleton
  fun okHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient {

    return OkHttpClient.Builder()
      .addInterceptor(
        loggingInterceptor
      )
      .connectTimeout(10, TimeUnit.SECONDS)
      .writeTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .cache(null)
      .build()
  }



  @Provides
  @Singleton
  fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
      .addConverterFactory(GsonConverterFactory.create(Gson()))//GsonConverterFactory.create(gson))
      .client(okHttpClient)
      .build()
  }

  @Provides
  @Singleton
  fun getApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
  }


}