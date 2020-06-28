package com.flywith24.wrapperlivedatademo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Flywith24
 * @date   2020/6/5
 * time   10:54
 * description
 */
class ContentViewModel : ViewModel() {
    val data = com.flywith24.wrapperlivedata.StatefulMutableLiveData<String>()

    fun requestData() {
        viewModelScope.launch {
            data.value = com.flywith24.wrapperlivedata.RequestState.Loading
            delay(2000)
            data.value = com.flywith24.wrapperlivedata.RequestState.Success("success")
        }
    }
}