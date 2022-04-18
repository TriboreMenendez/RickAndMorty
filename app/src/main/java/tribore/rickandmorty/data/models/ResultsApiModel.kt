package tribore.rickandmorty.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultsApiModel(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationApiModel,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)