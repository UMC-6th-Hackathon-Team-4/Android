package umc.hackathon.presentation.create

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import umc.hackathon.data.repository.CreateRepositoryImpl
import umc.hackathon.domain.model.Treasure
import umc.hackathon.util.onSuccess
import javax.inject.Inject

//import umc.hackathon.data.repository.Repository

//class CreateViewModel(private val repository: Repository) : ViewModel() {

@HiltViewModel
class CreateSelectImageViewModel @Inject constructor(
    private val repository: CreateRepositoryImpl
) : ViewModel() {
    private val _selectedPhotos = MutableStateFlow<List<Treasure>>(emptyList())
    val selectedPhotos: StateFlow<List<Treasure>> get() = _selectedPhotos

    private val _selectedPhotosShared = MutableSharedFlow<List<Treasure>>()
    val selectedPhotosShared: SharedFlow<List<Treasure>> get() = _selectedPhotosShared

    private val _uploadResponse = MutableLiveData<ResponseBody>()
    val uploadResponse: LiveData<ResponseBody> get() = _uploadResponse

    private val _selectedIndex = MutableStateFlow(1)
    val selectedIndex = _selectedIndex.asStateFlow()

    private val _currentLastIndex = MutableStateFlow(0)
    val currentLastIndex = _currentLastIndex.asStateFlow()

    private val _isWritten = MutableStateFlow(false)
    val isWritten = _isWritten.asStateFlow()

    fun setSelectedIndex(index: Int){
        _selectedIndex.value = index
    }

    fun setIsWritten(isWritten: Boolean){
        if (isWritten){
            updateIsWritten()
        }
        _isWritten.value = isWritten
    }

    private fun updateIsWritten() {
        val currentIndex = _selectedIndex.value
        _selectedPhotos.update { list ->
            list.map { treasure ->
                if (treasure.index == currentIndex) {
                    treasure.copy(isWritten = true)
                } else {
                    treasure
                }
            }
        }
        Log.d("update selected", _selectedPhotos.value.toString())
    }

    fun addSelectedPhotos(photos: List<Treasure>) {
        _selectedPhotos.value = photos
        Log.d("_selectedPhotos", _selectedPhotos.value.toString())
    }

    fun initSelectedPhotos(){
        viewModelScope.launch {
            _selectedPhotosShared.emit(_selectedPhotos.value)
        }
    }

    fun removePhoto(index: Int) {
        val currentPhotos = _selectedPhotos.value.orEmpty().toMutableList()
        val temp = currentPhotos.find { it.index == index }
        if(temp != null){
            currentPhotos.remove(temp)
        }
        _selectedPhotos.value = currentPhotos
    }

    /* api 연결 위함
    fun uploadPost(title: String, content: String, images: List<MultipartBody.Part>) {
        viewModelScope.launch {
            val titlePart = title.toRequestBody(MultipartBody.FORM)
            val contentPart = content.toRequestBody(MultipartBody.FORM)
            val response = repository.uploadPost(titlePart, contentPart, images)
            _uploadResponse.postValue(response)
        }
    }

     */
    /*
        fun create(){
            viewModelScope.launch {
                repository.createTreasure().onSuccess {

                }
            }
        }

     */
}
