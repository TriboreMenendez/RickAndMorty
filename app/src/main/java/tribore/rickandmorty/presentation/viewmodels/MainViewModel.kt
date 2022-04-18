package tribore.rickandmorty.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tribore.rickandmorty.domain.models.CharacterDomainModel
import tribore.rickandmorty.domain.usecase.GetAllPersonUseCase
import tribore.rickandmorty.util.LoadingStatus
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getAllPersonUseCase: GetAllPersonUseCase) :
    ViewModel() {

    private val _allPersonRickAndMorty = MutableLiveData<List<CharacterDomainModel>>()
    val allCharacterRickAndMorty: LiveData<List<CharacterDomainModel>> = _allPersonRickAndMorty

    private val _loadStatus = MutableLiveData<LoadingStatus>()
    val loadStatus: LiveData<LoadingStatus> = _loadStatus

    init {
        loadData()
    }

    fun loadData() {
        _loadStatus.value = LoadingStatus.LOADING
        viewModelScope.launch {
            val requestResult = getAllPersonUseCase.execute()
            if (requestResult.errorResponse == null && requestResult.resultList.isNotEmpty()) {
                _loadStatus.value = LoadingStatus.DONE
                _allPersonRickAndMorty.value =
                    _allPersonRickAndMorty.value?.plus(requestResult.resultList)
                        ?: requestResult.resultList

            } else {
                _loadStatus.value = LoadingStatus.ERROR
            }
        }
    }

}