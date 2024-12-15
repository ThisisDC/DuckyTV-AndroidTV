package com.example.duckytv.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import com.example.duckytv.models.Channel

@OptIn(ExperimentalTvMaterial3Api::class,)
@Composable
fun ChannelCard(modifier: Modifier = Modifier, channel: Channel, onClick: () -> Unit) {
    Box(modifier = modifier.padding(horizontal = 15.dp, vertical = 10.dp),) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(text = channel.name, fontSize = 20.sp)
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.DarkGray)
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    text = channel.country,
                    fontSize = 14.sp
                )
            }
            Button(onClick = { onClick() },) {
                Icon(imageVector = Icons.Rounded.PlayArrow, contentDescription = "Play")
            }
        }
    }
}