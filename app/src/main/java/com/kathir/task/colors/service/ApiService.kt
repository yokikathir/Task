package com.kathir.task.colors.service
import com.kathir.task.colors.model.Photo
import retrofit2.http.GET

// ApiService.kt
interface ApiService {
    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}
