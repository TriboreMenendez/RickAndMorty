package tribore.rickandmorty.data.repository

import tribore.rickandmorty.data.network.NetworkApi
import tribore.rickandmorty.domain.models.PersonDomainModel
import tribore.rickandmorty.domain.repository.RemoteRepository

class RepositoryImpl(private val network: NetworkApi): RemoteRepository {

    override suspend fun getAll(): List<PersonDomainModel> {
        return network
            .getAllPerson()
            .results
            .map { it.toDomain() }
    }
}