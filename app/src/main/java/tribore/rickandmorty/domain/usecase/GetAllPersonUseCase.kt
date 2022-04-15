package tribore.rickandmorty.domain.usecase

import tribore.rickandmorty.domain.models.PersonDomainModel
import tribore.rickandmorty.domain.repository.RemoteRepository

class GetAllPersonUseCase(private val repo: RemoteRepository) {

    suspend fun execute(): List<PersonDomainModel> {

        return repo.getAll()
    }

}