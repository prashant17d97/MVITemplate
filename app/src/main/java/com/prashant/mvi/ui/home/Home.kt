package com.prashant.mvi.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

const val TAG = "HOME"

@Composable
fun Home(
    post: List<Post> = listOf(),
    loadData: () -> Unit = {},
    isLoading: Boolean = false
) {
    LaunchedEffect(key1 = Unit, block = { loadData() })
    AnimatedVisibility(visible = isLoading) {
        Dialog(onDismissRequest = {}) {
            CircularProgressIndicator()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(post) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.Top),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = it.title?:"", style = MaterialTheme.typography.titleLarge)
                    Text(text = "${it.id?:0}", style = MaterialTheme.typography.titleMedium)
                    Text(text = "${it.userId?:0}", style = MaterialTheme.typography.titleMedium)
                    Text(text = it.body?:"", style = MaterialTheme.typography.titleMedium)
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePrev() {
    Home(
        post = listOf(
            Post(userId = 8179, id = 8834, title = "urbanitas", body = "utroque"),
            Post(userId = 8179, id = 8834, title = "urbanitas", body = "utroque"),
            Post(userId = 8179, id = 8834, title = "urbanitas", body = "utroque"),
            Post(userId = 8179, id = 8834, title = "urbanitas", body = "utroque"),
        )
    )
}
