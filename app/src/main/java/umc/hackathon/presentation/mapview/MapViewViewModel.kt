package umc.hackathon.presentation.mapview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import umc.hackathon.data.repository.NaverRepositoryImpl
import umc.hackathon.domain.repository.NaverRepository
import umc.hackathon.util.onException
import umc.hackathon.util.onSuccess
import javax.inject.Inject

@HiltViewModel
class MapViewViewModel @Inject constructor(
    private val repository: NaverRepositoryImpl
): ViewModel(){
    private val _latLng = MutableSharedFlow<LatLng>()
    val latLng: SharedFlow<LatLng> get() = _latLng

    private val _addressQuery = MutableStateFlow<String>("")
    val addressQuery: StateFlow<String> get() = _addressQuery

    private val _isInMapView = MutableStateFlow<Boolean>(false)
    val isInMapView: StateFlow<Boolean> get() = _isInMapView

    fun setIsInMapView(isInMapView: Boolean){
        _isInMapView.value = isInMapView
    }


    fun fetchGeocoding(query: String) {
        viewModelScope.launch {
            repository.fetchGeocoding(query).onSuccess { response ->
                val result = response.addresses.firstOrNull()
                if (result == null) {
                    return@launch
                } else {
                    with(result) {
                        val latLng = LatLng(y.toDouble(), x.toDouble())
                        _latLng.emit(latLng)

                    }
                }
                Log.d("naver", response.toString())
            }.onException {
                Log.d("error", it.toString())
            }
        }
    }
}