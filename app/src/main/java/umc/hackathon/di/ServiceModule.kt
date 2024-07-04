package umc.hackathon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import umc.hackathon.data.api.HomeApi
import umc.hackathon.data.api.NaverApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    private inline fun <reified T> Retrofit.buildService(): T {
        return this.create(T::class.java)
    }

    @Provides
    @Singleton
    fun provideNaverApi(@NetworkModule.NaverRetrofit retrofit: Retrofit): NaverApi {
        return retrofit.buildService()
    }

    @Provides
    @Singleton
    fun provideHomeApi(@NetworkModule.BaseRetrofit retrofit: Retrofit): HomeApi {
        return retrofit.buildService()
    }
}