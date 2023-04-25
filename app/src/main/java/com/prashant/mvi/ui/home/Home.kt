package com.prashant.mvi.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Home(
//    navHostController: NavHostController,
    model: HomeModel,
    onClick: (HomeIntent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Counter: ${model.counter}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = { onClick(HomeIntent.Increment) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Increment")
        }
        Button(
            onClick = { onClick(HomeIntent.Decrement) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Decrement")
        }
    }
}

@Preview
@Composable
fun HomePrev() {
    Home(
//        navHostController = rememberNavController(),
        model = HomeModel(counter = 0),
        onClick = {})
}
