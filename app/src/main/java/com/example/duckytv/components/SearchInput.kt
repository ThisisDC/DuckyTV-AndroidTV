package com.example.duckytv.components

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchInput(input: String, modifier: Modifier = Modifier, onChanged: (String) -> Unit) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(12.dp))
        .background(Color.DarkGray)
        .padding(16.dp)
        .fillMaxWidth(0.6f)
    ){
        if (input.isEmpty()) {
            Text(text = "Search...", color = Color.Gray)
        }
        BasicTextField(value = input, onValueChange = onChanged,
            cursorBrush = SolidColor(Color.White), textStyle = TextStyle(color = Color.White),
            singleLine = true
        )
    }
}