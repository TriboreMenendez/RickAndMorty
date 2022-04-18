package tribore.rickandmorty.domain.usecase

import tribore.rickandmorty.domain.models.AllResultDomainModel
import tribore.rickandmorty.domain.repository.RemoteRepository

class GetAllCharacterUseCase(private val repo: RemoteRepository) {

    suspend fun execute(): AllResultDomainModel {
        return repo.getAll()
    }
}