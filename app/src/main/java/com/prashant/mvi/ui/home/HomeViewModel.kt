package com.prashant.mvi.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonElement
import com.prashant.mvi.network.ApiProcessor
import com.prashant.mvi.network.Repository
import com.prashant.mvi.network.RetrofitCalls
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _posts = MutableStateFlow(listOf<Post>())
    private val _loading = mutableStateOf(false)
    val posts: StateFlow<List<Post>> get() = _posts
    val loading: MutableState<Boolean> get() = _loading


    fun loadData() {
        viewModelScope.launch {
            _loading.value=true
            repository.apiCall(
                retrofitCall = object : ApiProcessor {
                    override suspend fun sendRequest(retrofitApi: RetrofitCalls): Response<JsonElement> {
                        return retrofitApi.getPosts()
                    }
                },
                result = {
                    _loading.value=false
                    _posts.value = it
                },
                responseMessage = { s: String, i: Int ->
                    _loading.value=true
                })
        }
    }
}