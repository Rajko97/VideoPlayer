package tv.brid.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tv.brid.data.db.dao.VideoDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    internal fun provideVideoDao(database: VideoDatabase): VideoDao = database.videoDao()

    @Singleton
    @Provides
    internal fun provideDatabase(@ApplicationContext context: Context): VideoDatabase =
        Room.databaseBuilder(context, VideoDatabase::class.java, VideoDatabase.NAME)
            .build()
}