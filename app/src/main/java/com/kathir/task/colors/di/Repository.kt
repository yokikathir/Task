package com.kathir.task.colors.di

import com.kathir.task.colors.model.Photo
import com.kathir.task.colors.service.ApiService

class Repository(private val apiService: ApiService) {
    suspend fun getPhotos(): List<Photo> {
        return apiService.getPhotos()
    }
}

