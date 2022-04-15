package tribore.rickandmorty.domain.models

data class PersonDomainModel(
    val name: String,
    val race: String,
    val gender: String,
    val urlAvatar: String,
    val status: String,
    val location: Location,
    val episode: List<String>
    )