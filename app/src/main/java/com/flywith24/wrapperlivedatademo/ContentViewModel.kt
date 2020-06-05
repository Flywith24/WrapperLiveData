package com.flywith24.wrapperlivedatademo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flywith24.wrapperlivedatademo.common.RequestState
import com.flywith24.wrapperlivedatademo.common.StatefulLiveData
import com.flywith24.wrapperlivedatademo.common.StatefulMutableLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Flywith24
 * @date   2020/6/5
 * time   10:54
 * description
 */
class ContentViewModel : ViewModel() {
    val data = StatefulMutableLiveData<String>()

    fun requestData() {
        viewModelScope.launch {
            data.value = RequestState.Loading
            delay(2000)
            data.value = RequestState.Success("success")
        }
    }
}