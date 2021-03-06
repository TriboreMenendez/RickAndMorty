package tribore.rickandmorty.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseResult(
    val info: Info,
    val results: List<ResultsApiModel>
)