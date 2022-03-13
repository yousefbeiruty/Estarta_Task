package com.it.yousefl.mstarttask.di

import android.content.Context
import androidx.room.Room
import com.it.yousefl.mstarttask.data.local.EventDao
import com.it.yousefl.mstarttask.data.local.EventDatabase
import com.it.yousefl.mstarttask.data.remote.Api
import com.it.yousefl.mstarttask.repositories.EventRepository
import com.it.yousefl.mstarttask.repositories.DefaultRepository
import com.it.yousefl.mstarttask.utils.Constants.BASE_URL
import com.it.yousefl.mstarttask.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApi():Api{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDatabase( @ApplicationContext contex: Context)= Room.databaseBuilder(contex,
        EventDatabase::class.java,DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideEventDao(database: EventDatabase)=database.eventDao()

    @Singleton
    @Provides
    fun provideDefaultRepository(
        dao: EventDao,
        api: Api
    )= DefaultRepository(dao,api) as EventRepository


}