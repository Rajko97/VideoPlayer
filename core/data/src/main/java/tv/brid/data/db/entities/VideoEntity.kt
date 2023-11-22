package tv.brid.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import tv.brid.domain.models.Video

@Entity(tableName = VideoEntity.TABLE_NAME)
data class VideoEntity(
    @ColumnInfo(name = COLUMN_ID)
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = COLUMN_TITLE)
    val title: String = "",
    @ColumnInfo(name = COLUMN_DESC)
    val desc: String = "",
    @ColumnInfo(name = COLUMN_THUMBNAIL)
    val thumbnail: String = "",
    @ColumnInfo(name = COLUMN_SOURCE)
    val sourceUrl: String = ""
) {
    companion object {
        const val TABLE_NAME = "videos"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESC = "desc"
        const val COLUMN_THUMBNAIL = "thumbnail"
        const val COLUMN_SOURCE = "src"
    }
}

fun VideoEntity.toVideoData() =
    Video(
        id = id,
        title = title,
        description = desc,
        thumbnailUrl = thumbnail,
        sourceUrl = sourceUrl
    )