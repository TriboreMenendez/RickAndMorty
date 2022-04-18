package tribore.rickandmorty.domain.usecase

import tribore.rickandmorty.domain.models.ResponseResultDomainModel
import tribore.rickandmorty.domain.repository.RemoteRepository

class GetAllPersonUseCase(private val repo: RemoteRepository) {

    suspend fun execute(): ResponseResultDomainModel {

        return repo.getAll()
    }

}