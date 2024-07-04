package umc.hackathon.presentation.create

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

//import umc.hackathon.data.repository.Repository

//class CreateViewModel(private val repository: Repository) : ViewModel() {

@HiltViewModel
class CreateViewModel @Inject constructor(

) : ViewModel() {
    private val _selectedPhotos = MutableLiveData<List<Uri>>()
    val selectedPhotos: LiveData<List<Uri>> get() = _selectedPhotos

    private val _uploadResponse = MutableLiveData<ResponseBody>()
    val uploadResponse: LiveData<ResponseBody> get() = _uploadResponse

    fun addSelectedPhotos(photos: List<Uri>) {
        val currentPhotos = _selectedPhotos.value.orEmpty().toMutableList()
        currentPhotos.addAll(photos)
        _selectedPhotos.value = currentPhotos
    }

    fun removePhoto(uri: Uri) {
        val currentPhotos = _selectedPhotos.value.orEmpty().toMutableList()
        currentPhotos.remove(uri)
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
}
