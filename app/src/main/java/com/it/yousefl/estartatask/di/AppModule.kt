package com.it.yousefl.estartatask.di


import com.it.yousefl.estartatask.data.remote.Api
import com.it.yousefl.estartatask.ui.main.booksrepository.BooksRepository
import com.it.yousefl.estartatask.ui.main.booksrepository.DefaultRepository
import com.it.yousefl.estartatask.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
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
    fun provideDefaultRepository(
        api: Api
    )= DefaultRepository(api) as BooksRepository


}