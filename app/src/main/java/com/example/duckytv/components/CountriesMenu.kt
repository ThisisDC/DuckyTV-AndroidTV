package com.example.duckytv.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CountriesMenu(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(items.indexOf(selectedItem)) }

    Column {
        Button (
            onClick = { expanded = !expanded },
            modifier = Modifier.focusable() // Make it focusable
        ) {
            Text(selectedItem)
        }

        // Show the dropdown items when expanded
        if (expanded) {
            LazyColumn(
                modifier = Modifier
                    .focusable()
                    .background(Color.LightGray) // Or any desired style
            ) {
                itemsIndexed(items) { index, item ->
                    CountryItem(
                        item = item,
                        isSelected = index == selectedIndex,
                        onClick = {
                            selectedIndex = index
                            onItemSelected(item)
                            expanded = false // Close the dropdown
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CountryItem(
    item: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // Style the item based on selection state
    val backgroundColor = if (isSelected) Color.DarkGray else Color.Transparent
    val textColor = if (isSelected) Color.White else Color.Black

    // Make the item focusable and clickable
    Box(
        modifier = Modifier
            .focusable()
            .clickable(onClick = onClick)
            .background(backgroundColor)
            .padding(8.dp)
    ) {
        Text(item, color = textColor)
    }
}
