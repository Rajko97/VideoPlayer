package tv.brid.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tv.brid.data.db.dao.VideoDao
import tv.brid.data.db.entities.VideoEntity

@Database(
    entities = [VideoEntity::class],
    version = VideoDatabase.VERSION,
    exportSchema = false
)
abstract class VideoDatabase : RoomDatabase() {

    companion object {
        const val NAME = "VideoDatabase"
        const val VERSION = 1
    }

    abstract fun videoDao(): VideoDao
}