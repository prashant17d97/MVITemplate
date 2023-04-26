package com.prashant.mvi.network

import android.util.Log
import com.prashant.mvi.MainActivity
import com.prashant.mvi.network.NetworkExtensionFunction.isNetworkAvailable
import com.prashant.mvi.network.NetworkExtensionFunction.jsonArrayToData
import com.prashant.mvi.network.NetworkExtensionFunction.jsonElementToData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import javax.inject.Inject

class Repository @Inject constructor(
    val retrofitCalls: RetrofitCalls
) {
    suspend inline fun <reified Generic> apiCall(
        retrofitCall: ApiProcessor,
        crossinline result: (List<Generic>) -> Unit,
        crossinline responseMessage: (message: String, code: Int) -> Unit
    ) {
        val context = MainActivity.weakReference.get() as MainActivity
        try {
            if (!context.isNetworkAvailable()) {
//                getStringResource(R.string.your_device_offline).showToast()
                return
            } else {
                val response = flow {
//                    showProgress()
                    emit(retrofitCall.sendRequest(retrofitCalls))
                }.flowOn(Dispatchers.IO)
                response.catch { exception ->
                    exception.printStackTrace()
//                    (exception.message ?: "").showToast()
                }.collect {
                    when (it.code()) {
                        /**Some error occurred*/
                        in 100..199 -> {
                            responseMessage(it.message(), it.code())
                            Log.e(
                                TAG,
                                "Repository:---> Message:${it.message()} ResponseCode: ${it.code()}"
                            )
                        }

                        /**
                         * Successful
                         * */
                        200 -> {
                            Log.e(
                                TAG,
                                "Repository:---> Message:${it.message()} ResponseCode: ${it.code()}"
                            )
                            if (it.isSuccessful && it.body()?.isJsonNull == false) {
                                jsonArrayToData(it.body()) { resultData ->
                                    result(resultData)
                                }
                                responseMessage(it.message(), it.code())
                            }
                        }

                        in 300..399 -> {
                            responseMessage(it.message(), it.code())
//                            getStringResource(R.string.someError).showToast()
                            Log.e(
                                TAG,
                                "Repository:---> Message:${it.message()} ResponseCode: ${it.code()}"
                            )
                        }

                        /**Unauthorized*/
                        401 -> {
//                            (MainActivity.weakReference.get() as MainActivity).sessionExpired(bool = true)
                            responseMessage(it.message(), it.code())
                            Log.e(
                                TAG,
                                "Repository:---> Message:${it.message()} ResponseCode: ${it.code()}"
                            )
                        }

                        /**Page not found*/
                        404 -> {
                            responseMessage(it.message(), it.code())
                            Log.e(
                                TAG,
                                "Repository:---> Message:${it.message()} ResponseCode: ${it.code()}"
                            )
                        }

                        /**Server Error*/
                        in 501..509 -> {
                            responseMessage(it.message(), it.code())
                            Log.e(
                                TAG,
                                "Repository:---> Message:${it.message()} ResponseCode: ${it.code()}"
                            )
                        }

                        else -> {
                            /**ClientErrors*/
                            val res = it.errorBody()!!.string()
                            val jsonObject = JSONObject(res)
                            when {
                                jsonObject.has("message") -> {
                                    responseMessage(jsonObject.getString("message"), it.code())
                                    Log.e(
                                        TAG,
                                        "Repository:---> Message:${jsonObject.getString("message")} ResponseCode: ${it.code()}"
                                    )
                                }

                                jsonObject.has("error") -> {
                                    val message =
                                        jsonObject.getJSONObject("error").getString("text") ?: ""
                                    responseMessage(message, it.code())
                                    Log.e(
                                        TAG,
                                        "Repository:---> Message:${message} ResponseCode: ${it.code()}"
                                    )

//                                    getStringResource(R.string.someError).showToast()
                                }

                                else -> {
//                                    getStringResource(R.string.someError).showToast()
                                }
                            }
                        }
                    }
                }
            }
        } catch (exception: Exception) {
            Log.e(TAG, "call: ${exception.message}")
        }

    }

    companion object {
        const val TAG = "Repository"
    }
}