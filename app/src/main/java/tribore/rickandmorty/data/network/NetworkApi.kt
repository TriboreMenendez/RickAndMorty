package tribore.rickandmorty.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import tribore.rickandmorty.data.models.ResponseResult

//"https://rickandmortyapi.com/api/character/?page=2"

interface NetworkApi {

    @GET("character")
    suspend fun getAllCharacter(
        @Query("page") page: Int
    ): ResponseResult

    @GET("character")
    suspend fun getOneCharacter(
        @Query("id") id:Int
    )

}