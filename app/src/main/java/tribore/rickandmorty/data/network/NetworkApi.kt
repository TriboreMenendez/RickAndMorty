package tribore.rickandmorty.data.network

import retrofit2.http.GET
import tribore.rickandmorty.data.models.ResponseResult

interface NetworkApi {

    @GET("character")
    suspend fun getAllPerson(): ResponseResult

}