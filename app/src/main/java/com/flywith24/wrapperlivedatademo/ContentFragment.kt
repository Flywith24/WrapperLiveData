package com.flywith24.wrapperlivedatademo

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.flywith24.wrapperlivedatademo.common.observeSingleEvent
import com.flywith24.wrapperlivedatademo.common.observeState
import kotlinx.android.synthetic.main.fragment_content.*

/**
 * @author Flywith24
 * @date   2020/6/5
 * time   10:03
 * description
 */
class ContentFragment : Fragment(R.layout.fragment_content) {
    private val mSharedViewModel by activityViewModels<SharedViewModel>()
    private val mContentViewModel by viewModels<ContentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        request.setOnClickListener {
            content.text = ""
            mContentViewModel.requestData()
        }

        mSharedViewModel.normalContent.observe(viewLifecycleOwner) { value ->
            normal.isChecked = value
            val toast = Toast.makeText(requireContext(), "normal value $value", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
        mSharedViewModel.eventContent.observeSingleEvent(viewLifecycleOwner,viewModelStore) { value ->
            event.isChecked = value
            Log.i("yyz11", "ContentFragment eventContent $value")
            val toast = Toast.makeText(requireContext(), "event value $value", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, 0)
            toast.show()
        }

        mContentViewModel.data.observeState(viewLifecycleOwner,
            onLading = {
                //loading
                progress.visibility = View.VISIBLE
            },
            onSuccess = { data ->
                //success
                progress.visibility = View.GONE
                content.text = data
            },
            onError = { exception ->
                //error
                progress.visibility = View.GONE
                Log.e("ContentFragment", "error ${exception?.message}")
            })
    }
}