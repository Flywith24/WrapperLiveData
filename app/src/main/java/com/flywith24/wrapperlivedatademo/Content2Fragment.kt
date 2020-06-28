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
        mSharedViewModel.eventContent.observeSingleEvent(viewLifecycleOwner,viewModelStore) {
            Log.i("yyz11", "Content2Fragment eventContent $it")
        }
    }
}