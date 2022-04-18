package tribore.rickandmorty.presentation.screens.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tribore.rickandmorty.domain.models.CharacterDomainModel
import tribore.rickandmorty.domain.usecase.GetOneCharacterUseCase
import tribore.rickandmorty.util.LoadingStatus
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getOneCharacterUseCase: GetOneCharacterUseCase
) : ViewModel() {

    private val _detailsCharacter = MutableLiveData<CharacterDomainModel>()
    val detailsCharacter: LiveData<CharacterDomainModel> = _detailsCharacter

    private val _loadStatus = MutableLiveData<LoadingStatus>()
    val loadStatus: LiveData<LoadingStatus> = _loadStatus

    fun loadDataCharacter(id: Int) {

        viewModelScope.launch {
            try {
                _detailsCharacter.value = getOneCharacterUseCase.execute(id = id)
                _loadStatus.value = LoadingStatus.DONE
                Log.d("Test", "Success")
            } catch (e: Exception) {
                _loadStatus.value = LoadingStatus.ERROR
                Log.d("Test", "Error: ${e.message}")
            }
        }
    }

}