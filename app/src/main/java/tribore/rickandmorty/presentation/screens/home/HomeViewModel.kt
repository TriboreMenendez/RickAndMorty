package tribore.rickandmorty.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tribore.rickandmorty.domain.models.CharacterDomainModel
import tribore.rickandmorty.domain.usecase.GetAllCharacterUseCase
import tribore.rickandmorty.util.LoadingStatus
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllCharacterUseCase: GetAllCharacterUseCase) :
    ViewModel() {

    private val _allCharacterRickAndMorty = MutableLiveData<List<CharacterDomainModel>>()
    val allCharacterRickAndMorty: LiveData<List<CharacterDomainModel>> = _allCharacterRickAndMorty

    private val _loadStatus = MutableLiveData<LoadingStatus>()
    val loadStatus: LiveData<LoadingStatus> = _loadStatus

    init {
        loadData()
    }

    fun loadData() {
        _loadStatus.value = LoadingStatus.LOADING

        viewModelScope.launch {
            val requestResult = getAllCharacterUseCase.execute()

            if (requestResult.errorResponse == null && requestResult.resultList.isNotEmpty()) {
                _loadStatus.value = LoadingStatus.DONE
                _allCharacterRickAndMorty.value =
                    _allCharacterRickAndMorty.value?.plus(requestResult.resultList)
                        ?: requestResult.resultList

            } else {
                _loadStatus.value = LoadingStatus.ERROR
            }
        }
    }

}