package com.prashant.mvi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.prashant.mvi.ui.home.HomeViewModel
import com.prashant.mvi.ui.home.Home

@Composable
fun Navigator(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NavScreens.Home.route) {
        composable(NavScreens.Home.route) {
            val viewModel = viewModel<HomeViewModel>()
            val model by viewModel.model.collectAsState()
            Home(
//                navHostController=navHostController,
                model = model,
                onClick = {
                    viewModel.onIntent(it)
                }
            )
        }
    }

}