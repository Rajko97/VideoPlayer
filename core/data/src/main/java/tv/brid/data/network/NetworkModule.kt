package tv.brid.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tv.brid.data.network.api.YoutubeApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideYoutubeService(retrofit: Retrofit) = retrofit.create(YoutubeApi::class.java)

    @Singleton
    @Provides
    internal fun provideRetrofit() = YoutubeService.build()

}