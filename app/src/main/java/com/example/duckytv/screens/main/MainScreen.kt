package com.example.duckytv.screens.main

import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.example.duckytv.R
import com.example.duckytv.components.ChannelCard
import com.example.duckytv.components.CountriesMenu
import com.example.duckytv.components.SearchInput
import com.example.duckytv.data.DataOrException
import com.example.duckytv.models.Channel
import com.example.duckytv.navigation.AppScreens

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    var input by rememberSaveable { mutableStateOf("") }

    val channelsData = produceState<DataOrException<Channel, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getChannels()
    }.value

    val channels = channelsData.data.filter {
        it.name.contains(input, true) || it.country.contains(input, true)
    }


    Surface(modifier = Modifier.fillMaxSize()) {
    if(channelsData.loading == true){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.duck),
                contentDescription = "logo",
                modifier = Modifier.clip(CircleShape)
            )
        }
    }else if(channelsData.data.isNotEmpty()) {
        TvLazyColumn(
            modifier = modifier,
        ) {
            item {
                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp)) {
                    SearchInput(input = input){
                        input = it
                    }
                }

            }
            items(channels){
                ChannelCard(channel = it, modifier = Modifier){
                    navController.navigate("${AppScreens.PlayerScreen.name}/${it.id}")
                }
            }
        }
    }else{
        Text(text = "No channel found.")
    }
}
}