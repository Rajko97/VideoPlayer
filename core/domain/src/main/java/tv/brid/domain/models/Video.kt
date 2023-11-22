package tv.brid.domain.models

data class Video(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val sourceUrl: String
)