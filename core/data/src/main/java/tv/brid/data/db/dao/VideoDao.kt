package tv.brid.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import tv.brid.data.db.entities.VideoEntity

@Dao
interface VideoDao {

    @Query("SELECT * FROM ${VideoEntity.TABLE_NAME}")
    fun getAll(): Flow<List<VideoEntity>>

    @Query("SELECT * FROM ${VideoEntity.TABLE_NAME} WHERE ${VideoEntity.COLUMN_ID}=:id")
    fun get(id: String): Flow<VideoEntity>

    @Transaction
    suspend fun updateVideos(videos: List<VideoEntity>) {
        insertOrUpdate(videos)
        deleteExcept(videos.map { it.id })
    }

    @Transaction
    suspend fun insertOrUpdate(videos: List<VideoEntity>) {
        insertOrIgnore(videos).also { insertResult ->
            videos
                .filterIndexed { index, _ ->
                    insertResult[index] == -1L
                }
                .takeIf { it.isNotEmpty() }
                ?.let {
                    update(it)
                }
        }
    }

    @Update
    suspend fun update(items: List<VideoEntity>): Int

    /**
     * Inserts list of videos to database.
     *
     * A method that returns the inserted rows ids will return -1 for rows that are not inserted
     * since this strategy will ignore the row if there is a conflict.
     *
     * @return List of results of insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnore(videos: List<VideoEntity>): List<Long>

    /**
     * Deletes videos from database which ids are not in parameters list.
     */
    @Query("DELETE FROM ${VideoEntity.TABLE_NAME} WHERE ${VideoEntity.COLUMN_ID} NOT IN (:ids)")
    suspend fun deleteExcept(ids: List<String>)
}