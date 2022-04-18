package tribore.rickandmorty.domain.usecase

import tribore.rickandmorty.domain.models.CharacterDomainModel
import tribore.rickandmorty.domain.repository.RemoteRepository

class GetOneCharacterUseCase(private val repo: RemoteRepository) {

    suspend fun execute(id: Int): CharacterDomainModel {
        return repo.getOne(id)
    }
}