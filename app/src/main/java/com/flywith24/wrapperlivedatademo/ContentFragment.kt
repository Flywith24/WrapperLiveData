package com.flywith24.wrapperlivedatademo

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.flywith24.wrapperlivedata.observeState
import kotlinx.android.synthetic.main.fragment_content.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        // 每个观察者[viewModelStore]仅能消费一次事件
        // 如果想让事件仅能被一个观察者消费，则不传入 [viewModelStore] 参数
        /* mSharedViewModel.eventContent.observeSingleEvent(
             viewLifecycleOwner,
             viewModelStore
         ) { value ->
             event.isChecked = value
             Log.i(TAG, "ContentFragment eventContent $value")
             val toast = Toast.makeText(requireContext(), "event value $value", Toast.LENGTH_SHORT)
             toast.setGravity(Gravity.BOTTOM, 0, 0)
             toast.show()
         }*/

        mContentViewModel.data.observeState(viewLifecycleOwner) {
            onLading = {
                //loading
                progress.visibility = View.VISIBLE
            }
            onSuccess = { data ->
                //success
                progress.visibility = View.GONE
                content.text = data
            }
            onError = { exception ->
                //error
                progress.visibility = View.GONE
                Log.e(TAG, "error ${exception?.message}")
            }
        }
        lifecycleScope.launch {
            mSharedViewModel.stateContent.collect { value ->
                event.isChecked = value
                Log.i(TAG, "ContentFragment eventContent $value")
                val toast =
                    Toast.makeText(requireContext(), "event value $value", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 0)
                toast.show()
            }
        }
    }

    companion object {
        private const val TAG = "ContentFragment"
    }
}