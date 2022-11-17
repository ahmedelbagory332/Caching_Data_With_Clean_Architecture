package com.example.cachingdatawithcleanarchitecture


import android.app.Application
import androidx.room.Room
import com.example.data.mapper.RestaurantMapper
import com.example.data.local.RestaurantDatabase
import com.example.data.remote.RestaurantApi
import com.example.data.repository.RestaurantRepositoryImpl
import com.example.domain.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(RestaurantApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): RestaurantApi =
        retrofit.create(RestaurantApi::class.java)

    @Provides
    @Singleton
    fun provideRestaurantRepository(api: RestaurantApi,db: RestaurantDatabase,restaurantMapper: RestaurantMapper): RestaurantRepository =
        RestaurantRepositoryImpl(api,db,restaurantMapper)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : RestaurantDatabase =
        Room.databaseBuilder(app, RestaurantDatabase::class.java, "restaurant_database")
            .build()

}