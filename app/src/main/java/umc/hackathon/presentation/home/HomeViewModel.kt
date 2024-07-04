package umc.hackathon.presentation.home

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val _selectedId = MutableStateFlow<Int>(0)
    val selectedId: StateFlow<Int> get() = _selectedId

    fun setSelectedId(id: Int){
        _selectedId.value = id
    }
}