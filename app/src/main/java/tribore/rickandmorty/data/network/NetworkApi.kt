package tribore.rickandmorty.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tribore.rickandmorty.data.models.ResponseResult
import tribore.rickandmorty.data.models.ResultsApiModel
import tribore.rickandmorty.util.Constant.NETWORK_GET_ALL
import tribore.rickandmorty.util.Constant.NETWORK_GET_ONE

interface NetworkApi {

    @GET(NETWORK_GET_ALL)
    suspend fun getAllCharacter(
        @Query("page") page: Int
    ): ResponseResult

    @GET(NETWORK_GET_ONE)
    suspend fun getOneCharacter(
        @Path("id") id: Int
    ): ResultsApiModel

}