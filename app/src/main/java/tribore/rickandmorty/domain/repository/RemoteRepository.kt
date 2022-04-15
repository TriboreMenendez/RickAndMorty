package tribore.rickandmorty.domain.repository

import tribore.rickandmorty.domain.models.PersonDomainModel

interface RemoteRepository {

    suspend fun getAll(): List<PersonDomainModel>

}