package tribore.rickandmorty.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)