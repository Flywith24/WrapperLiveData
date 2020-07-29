package com.flywith24.wrapperlivedata

import androidx.lifecycle.ViewModelStore

/**
 * 使用 包装类 解决 LiveData 粘性事件的问题
 * 详见 https://juejin.im/post/5b2b1b2cf265da5952314b63
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    private var map = HashMap<ViewModelStore, Boolean>()

    /**
     * 整个事件只需要唯一观察者消费
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * 每个观察者仅能消费一次事件
     * 根据同观察者判断事件是否消费
     * 如果该观察者已消费数据，则返回null
     * 否则标记已消费并返回数据
     */
    fun getContentIfNotHandled(viewModelStore: ViewModelStore): T? {
        return if (map.contains(viewModelStore)) {
            null
        } else {
            map[viewModelStore] = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}