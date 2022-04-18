package tribore.rickandmorty.data.repository

import tribore.rickandmorty.data.models.ResultsApiModel
import tribore.rickandmorty.domain.models.LocationDomainModel
import tribore.rickandmorty.domain.models.CharacterDomainModel

fun ResultsApiModel.toDomain(): CharacterDomainModel {

    val location = LocationDomainModel(this.location.name)

    return CharacterDomainModel(
        id = this.id,
        name = this.name,
        race = this.species,
        gender = this.gender,
        urlAvatar = this.image,
        status = this.status,
        location = location,
        episode = this.episode
    )

}