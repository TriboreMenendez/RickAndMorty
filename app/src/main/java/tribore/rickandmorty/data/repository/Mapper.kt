package tribore.rickandmorty.data.repository

import tribore.rickandmorty.data.models.Results
import tribore.rickandmorty.domain.models.Location
import tribore.rickandmorty.domain.models.PersonDomainModel

fun Results.toDomain(): PersonDomainModel {

    val location = Location(this.location.name)

    return PersonDomainModel(
        name = this.name,
        race = this.species,
        gender = this.gender,
        urlAvatar = this.image,
        status = this.status,
        location = location,
        episode = this.episode
    )

}