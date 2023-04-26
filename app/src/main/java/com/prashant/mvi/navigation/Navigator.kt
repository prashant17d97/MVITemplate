package com.prashant.mvi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.prashant.mvi.ui.home.Home
import com.prashant.mvi.ui.home.HomeViewModel

@Composable
fun Navigator(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NavScreens.Home.route) {
        composable(NavScreens.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val model by viewModel.posts.collectAsState()
            Home(
//                navHostController=navHostController,
                isLoading = viewModel.loading.value,
                post = model,
                loadData = {
                    viewModel.loadData()
                }
            )
        }
    }

}