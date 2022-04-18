package tribore.rickandmorty.domain.models

data class CharacterDomainModel(
    val id: Int,
    val name: String,
    val race: String,
    val gender: String,
    val urlAvatar: String,
    val status: String,
    val location: LocationDomainModel,
    val episode: List<String>
)