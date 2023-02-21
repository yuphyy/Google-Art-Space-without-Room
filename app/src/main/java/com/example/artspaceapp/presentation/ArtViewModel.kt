package com.example.artspaceapp.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artspaceapp.domain.Art
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(): ViewModel() {

    val index = MutableStateFlow(0)

    private val _arts = MutableStateFlow(
        mutableListOf(
            Art(
                image = "https://storage.googleapis.com/pod_public/1300/120735.jpg",
                name = "Saturday Morning",
                artist = "Angle Sasmita",
                year = 2021
            ),
            Art(
                image = "https://images.pexels.com/photos/910411/pexels-photo-910411.jpeg?cs=srgb&dl=pexels-gareth-davies-910411.jpg&fm=jpg",
                name = "Morning",
                artist = "ZXC",
                year = 2023
            ),
            Art(
                image = "https://www.thespruce.com/thmb/c3znkzZgMeuvzBy4wH13jVllfUo=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/plants-with-big-flowers-4138211-hero-b10becb169064cc4b3c7967adc1b22e1.jpg",
                name = "Large Blooms",
                artist = "Abibulla Akimov",
                year = 2021
            )
        )
    )

    val arts = _arts.asStateFlow()

    fun updateIndex(value: Int){
        viewModelScope.launch {
            index.emit(value)
        }
    }
}