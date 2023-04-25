package com.prashant.mvi.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// ViewModel: Handles the business logic and updates the Model
class HomeViewModel : ViewModel() {
    private val _model = MutableStateFlow(HomeModel(counter = 0))
    val model: StateFlow<HomeModel> get() = _model


    fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.Increment -> {
                _model.value = _model.value.copy(counter = _model.value.counter + 1)
            }
            HomeIntent.Decrement -> {
                if (_model.value.counter>0) {
                    _model.value = _model.value.copy(counter = _model.value.counter - 1)
                }
            }

            HomeIntent.DoNothing -> {}
        }
    }
}