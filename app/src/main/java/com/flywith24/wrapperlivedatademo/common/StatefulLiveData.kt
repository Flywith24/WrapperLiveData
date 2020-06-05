package com.flywith24.wrapperlivedatademo.common

import androidx.annotation.MainThread
import androidx.lifecycle.observe
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.lang.Exception

/**
 * @author Flywith24
 * @date   2020/6/5
 * time   10:53
 * description
 */
typealias StatefulLiveData<T> = LiveData<RequestState<T>>
typealias StatefulMutableLiveData<T> = MutableLiveData<RequestState<T>>

@MainThread
inline fun <T> StatefulLiveData<T>.observeState(
    owner: LifecycleOwner,
    crossinline onLading: () -> Unit = {},
    crossinline onSuccess: (T?) -> Unit = {},
    crossinline onError: (Exception?) -> Unit = {}
) {
    observe(owner) { state ->
        when (state) {
            is RequestState.Loading -> onLading.invoke()
            is RequestState.Success -> onSuccess(state.data)
            is RequestState.Error -> onError(state.error)
        }
    }
}