package com.example.artspaceapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.artspaceapp.presentation.ArtViewModel
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray
                ) {
                    ArtSpaceScreen(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}


@Composable
fun ArtSpaceScreen(
    viewModel: ArtViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val listOfArts = viewModel.arts.collectAsState()
    val index = viewModel.index.collectAsState()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        //Image
        AsyncImage(
            model = listOfArts.value[index.value].image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .weight(1.5f),
            contentScale = ContentScale.None
        )
        Spacer(modifier = Modifier.height(32.dp))

        //Description Card
        ArtDescription(
            artName = listOfArts.value[index.value].name,
            artist = listOfArts.value[index.value].artist,
            year = listOfArts.value[index.value].year,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        //Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (index.value != 0) {
                        viewModel.updateIndex(index.value - 1)
                    }
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text(text = "Previous")
            }
            Button(
                onClick = {
                    if (index.value < viewModel.arts.value.size - 1) {
                        viewModel.updateIndex(index.value + 1)
                    }
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun ArtDescription(
    artName: String,
    artist: String,
    year: Int,
    modifier: Modifier = Modifier
) {
    Card {
        Column(modifier = modifier) {
            Text(
                text = artName,
                fontSize = 18.sp
            )
            Row {
                Text(
                    text = artist,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = "$year",
                    fontSize = 14.sp,
                )
            }
        }
    }
}





