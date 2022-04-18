package tribore.rickandmorty.domain.repository

import tribore.rickandmorty.domain.models.AllResultDomainModel
import tribore.rickandmorty.domain.models.CharacterDomainModel

interface RemoteRepository {

    suspend fun getAll(): AllResultDomainModel

    suspend fun getOne(id: Int): CharacterDomainModel

}