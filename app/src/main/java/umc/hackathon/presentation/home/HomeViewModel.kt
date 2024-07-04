package umc.hackathon.presentation.home

import android.util.Log
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import umc.hackathon.domain.repository.home.HomeRepository
import umc.hackathon.util.onException
import umc.hackathon.util.onSuccess

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    private val _selectedId = MutableStateFlow<Int>(0)
    val selectedId: StateFlow<Int> get() = _selectedId

    fun setSelectedId(id: Int){
        _selectedId.value = id
    }

    fun fetchTreasureList(){
        viewModelScope.launch {
            repository.fetchTreasureList().onSuccess {

            }.onException {
                Log.d("error", it.toString())
            }
        }
    }
}