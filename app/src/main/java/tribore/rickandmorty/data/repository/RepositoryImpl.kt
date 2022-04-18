package tribore.rickandmorty.data.repository

import tribore.rickandmorty.data.models.ResponseResult
import tribore.rickandmorty.data.network.NetworkApi
import tribore.rickandmorty.domain.models.ResponseResultDomainModel
import tribore.rickandmorty.domain.repository.RemoteRepository

class RepositoryImpl(private val network: NetworkApi) : RemoteRepository {

    private var moreResult = true
    private var lastRequestedPage = 1
    private var isRequestInProgress = false

    override suspend fun getAll(): ResponseResultDomainModel {
        return checkingTheResult()
    }

    private suspend fun checkingTheResult(): ResponseResultDomainModel {
        if (moreResult && !isRequestInProgress) {
            isRequestInProgress = true
            return try {
                val response = network.getAllCharacter(lastRequestedPage)
                val allCharacterResult = response.results.map { it.toDomain() }
                if (response.info.next != null) lastRequestedPage++ else moreResult = false
                isRequestInProgress = false
                ResponseResultDomainModel(resultList = allCharacterResult)
            } catch (e: Exception) {
                isRequestInProgress = false
                ResponseResultDomainModel(errorResponse = e)
            }
        }
        else return ResponseResultDomainModel()
    }
}