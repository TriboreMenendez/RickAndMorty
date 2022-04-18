package tribore.rickandmorty.domain.models

import java.lang.Exception

data class AllResultDomainModel(
    val resultList: List<CharacterDomainModel> = listOf(),
    val errorResponse: Exception? = null
)