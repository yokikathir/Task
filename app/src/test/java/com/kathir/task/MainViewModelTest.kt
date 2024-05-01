package com.kathir.task

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kathir.task.colors.di.Repository
import com.kathir.task.colors.model.Photo
import com.kathir.task.colors.viewmodel.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var repository: Repository

    @Before
    fun setup() {
        repository = Mockito.mock(Repository::class.java)
        mainViewModel = MainViewModel(repository)
    }

    @Test
    suspend fun fetchPhotos_shouldUpdatePhotosLiveDataWithExpectedData() {
        // Given
        val expectedPhotos = listOf(
            Photo("https://via.placeholder.com/600/92c952"),
            Photo("https://via.placeholder.com/600/92c952")
        )
        val liveData = MutableLiveData<List<Photo>>()
        liveData.value = expectedPhotos
        Mockito.`when`(repository.getPhotos()).thenReturn(expectedPhotos)
        // When
        mainViewModel.fetchPhotos()

        // Then
        val actualPhotos = LiveDataTestUtil.getValue(mainViewModel.photos)
        assert(actualPhotos == expectedPhotos)
    }
}
