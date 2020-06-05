package com.flywith24.wrapperlivedatademo.common

import androidx.lifecycle.LiveData
import java.lang.Exception

/**
 * @author Flywith24
 * @date   2020/6/5
 * time   10:52
 * description
 */
sealed class RequestState<out T> {
    object Loading : RequestState<Nothing>()
    data class Success<out T>(val data: T?) : RequestState<T>()
    data class Error(val code: String? = null, val error: Exception? = null) :
        RequestState<Nothing>()
}

