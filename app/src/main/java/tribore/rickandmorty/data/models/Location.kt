package tribore.rickandmorty.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    val name: String,
    val url: String
)