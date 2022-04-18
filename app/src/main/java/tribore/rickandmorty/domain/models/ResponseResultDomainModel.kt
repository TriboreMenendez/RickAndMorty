package tribore.rickandmorty.domain.models

import java.lang.Exception

data class ResponseResultDomainModel(
    val resultList: List<CharacterDomainModel> = listOf(),
    val errorResponse: Exception? = null
)