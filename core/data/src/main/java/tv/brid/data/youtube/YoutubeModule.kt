package tv.brid.data.youtube

import android.content.Context
import com.maxrave.kotlinyoutubeextractor.YTExtractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class YoutubeModule {

    @Singleton
    @Provides
    internal fun provideYTExtractor(
        @ApplicationContext context: Context
    ): YTExtractor = YTExtractor(
        con = context,
        CACHING = true,
        LOGGING = false,
        retryCount = 3
    )
}