package com.flywith24.wrapperlivedata

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe

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
    init: ResultBuilder<T>.() -> Unit
) {
    val result = ResultBuilder<T>().apply(init)

    observe(owner) { state ->
        when (state) {
            is RequestState.Loading -> result.onLading.invoke()
            is RequestState.Success -> result.onSuccess(state.data)
            is RequestState.Error -> result.onError(state.error)
        }
    }
}