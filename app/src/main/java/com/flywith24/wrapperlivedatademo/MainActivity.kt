package com.flywith24.wrapperlivedatademo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.flywith24.wrapperlivedatademo.common.Event
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Flywith24
 * @date   2020/6/4
 * time   21:13
 * description
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val mSharedViewModel by viewModels<SharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //点击按钮对 LiveData 的值取反
        button.setOnClickListener {
            val eventValue = mSharedViewModel.eventContent.value?.peekContent()
            val normalValue = mSharedViewModel.normalContent.value

            eventValue?.let { value ->
                mSharedViewModel.eventContent.value = Event(!value)
            }
            normalValue?.let { value ->
                mSharedViewModel.normalContent.value = !value
            }
        }
    }
}