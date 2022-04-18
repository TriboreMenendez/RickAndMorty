package tribore.rickandmorty.domain.repository

import tribore.rickandmorty.domain.models.ResponseResultDomainModel

interface RemoteRepository {

    suspend fun getAll(): ResponseResultDomainModel

}