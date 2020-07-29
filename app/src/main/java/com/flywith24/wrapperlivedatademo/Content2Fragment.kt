package com.flywith24.wrapperlivedatademo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.flywith24.wrapperlivedata.observeSingleEvent

/**
 * @author Flywith24
 * @date   2020/6/5
 * time   23:31
 * description
 */
class Content2Fragment : Fragment(R.layout.fragment_content2) {

    private val mSharedViewModel by activityViewModels<SharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 每个观察者[viewModelStore]仅能消费一次事件
        // 如果想让事件仅能被一个观察者消费，则不传入 [viewModelStore] 参数
        mSharedViewModel.eventContent.observeSingleEvent(viewLifecycleOwner, viewModelStore) {
            Log.i(TAG, "Content2Fragment eventContent $it")
        }
    }

    companion object {
        private const val TAG = "Content2Fragment"
    }
}