package tv.brid.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tv.brid.data.network.NetworkModule
import tv.brid.data.repositories.FakeVideoRepositoryImpl
import tv.brid.data.repositories.YoutubeRepositoryImpl
import tv.brid.domain.VideosRepository
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
//    fun bindVideosRepository(youtubeRepository: YoutubeRepositoryImpl): VideosRepository
    fun bindVideosRepository(fakeVideosRepository: FakeVideoRepositoryImpl): VideosRepository
}