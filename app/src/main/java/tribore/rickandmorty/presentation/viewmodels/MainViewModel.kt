package tribore.rickandmorty.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tribore.rickandmorty.domain.models.PersonDomainModel
import tribore.rickandmorty.domain.usecase.GetAllPersonUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getAllPersonUseCase: GetAllPersonUseCase) :
    ViewModel() {

    private val _allPersonRickAndMorty = MutableLiveData<List<PersonDomainModel>>()
    val allPersonRickAndMorty: LiveData<List<PersonDomainModel>> = _allPersonRickAndMorty

    val error = MutableLiveData("false")

    init {
        loadData()
        Log.d("test", "Init view model")
    }

    private fun loadData() {
        viewModelScope.launch {
                _allPersonRickAndMorty.value = getAllPersonUseCase.execute()
                error.value = "Успешно"
        }
    }

}