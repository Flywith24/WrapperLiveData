package com.flywith24.wrapperlivedatademo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author Flywith24
 * @date   2020/6/4
 * time   21:13
 * description
 */
class SharedViewModel : ViewModel() {
    /**
     * 包装类，用于使用 LiveData 传递事件
     * 正常来说，LiveData 可搭配 ViewModel 来进行视图控制器之间的数据共享
     * 在观察者处于 STARTED 到 RESUMED 之间的状态时，可以接收到 LiveData 的数据
     * 并且在屏幕旋转等场景重新执行 observe 方法时，LiveData 会将上次的数据传递回来
     *
     * 这没什么问题，但如果想要使用 LiveData 传递「事件」而不是「数据」时，会出现「粘性事件」的问题
     * 导致收到过时的「事件」
     *
     * 使用包装类的方式可以解决该问题
     * 详情参考
     * https://juejin.im/post/5e834bb5f265da480d61668d#heading-1
     *
     * https://juejin.im/post/5b2b1b2cf265da5952314b63#heading-7
     */
    //等价于 MutableLiveData<Event<Boolean>>(Event(false))
    val eventContent =
        com.flywith24.wrapperlivedata.EventMutableLiveData<Boolean>(
            com.flywith24.wrapperlivedata.Event(
                false
            )
        )

    /**
     * 正常的使用，用于对比
     */
    val normalContent = MutableLiveData<Boolean>(false)
}