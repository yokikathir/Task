package com.kathir.task.colors.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.kathir.task.colors.model.Photo
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kathir.task.colors.di.Repository
import com.kathir.task.colors.service.RetrofitInstance
import com.kathir.task.colors.viewmodel.MainViewModel
import com.kathir.task.colors.viewmodel.MainViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : ComponentActivity() {
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(
        Repository(
            RetrofitInstance.apiService)
    )
    )
    val photosState by viewModel.photos.observeAsState(initial = emptyList())
    val isLoading = viewModel.isLoading.observeAsState(initial = false).value
    LaunchedEffect(Unit) {
        viewModel.fetchPhotos()
    }
    Surface {
        Box(modifier = Modifier.fillMaxSize()) {
            // Content
            Column(modifier = Modifier.fillMaxSize()) {
                PhotoList(photos = photosState)
            }
            // Loading indicator
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    LinearProgressIndicator(
                        modifier = Modifier.wrapContentSize(),
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Composable
fun PhotoList(photos: List<Photo>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(photos) { photo ->
            PhotoItem(photo = photo)
        }
    }
}

@Composable
fun PhotoItem(photo: Photo) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), // Match parent width
        contentAlignment = Alignment.Center // Center alignment
    ) {
        Image(
            painter = rememberImagePainter(
                data = photo.url,
                builder = {
                    // You can add additional parameters here, such as placeholder, error, etc.
                }
            ),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}
