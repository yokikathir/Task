package com.kathir.task.colors.viewmodel
// MainViewModel.kt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kathir.task.colors.model.Photo
import com.kathir.task.colors.di.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> get() = _photos

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        // Handle exception
        _isLoading.postValue(false)
        // Log the exception or show an error message
    }

    fun fetchPhotos() {
        _isLoading.postValue(true) // Show progress bar
        viewModelScope.launch(coroutineExceptionHandler) {
            val fetchedPhotos = repository.getPhotos()
            _photos.postValue(fetchedPhotos)
            _isLoading.postValue(false) // Hide progress bar
        }
    }
}
