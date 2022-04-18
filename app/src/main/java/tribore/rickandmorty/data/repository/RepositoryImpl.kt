package tribore.rickandmorty.data.repository

import tribore.rickandmorty.data.network.NetworkApi
import tribore.rickandmorty.domain.models.AllResultDomainModel
import tribore.rickandmorty.domain.models.CharacterDomainModel
import tribore.rickandmorty.domain.repository.RemoteRepository

class RepositoryImpl(private val network: NetworkApi) : RemoteRepository {

    private var moreResult = true
    private var lastRequestedPage = 1
    private var isRequestInProgress = false

    override suspend fun getAll(): AllResultDomainModel {
        return checkingTheResult()
    }

    override suspend fun getOne(id: Int): CharacterDomainModel {
        return network.getOneCharacter(id = id).toDomain()
    }

    private suspend fun checkingTheResult(): AllResultDomainModel {
        if (moreResult && !isRequestInProgress) {
            isRequestInProgress = true
            return try {
                val response = network.getAllCharacter(lastRequestedPage)
                val allCharacterResult = response.results.map { it.toDomain() }
                if (response.info.next != null) lastRequestedPage++ else moreResult = false
                isRequestInProgress = false
                AllResultDomainModel(resultList = allCharacterResult)
            } catch (e: Exception) {
                isRequestInProgress = false
                AllResultDomainModel(errorResponse = e)
            }
        }
        else return AllResultDomainModel()
    }
}