package umc.hackathon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import umc.hackathon.data.api.NaverApi
import umc.hackathon.data.repository.NaverRepositoryImpl
import umc.hackathon.domain.repository.NaverRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesNaverRepository(
        naverApi: NaverApi
    ): NaverRepository = NaverRepositoryImpl(naverApi)
}