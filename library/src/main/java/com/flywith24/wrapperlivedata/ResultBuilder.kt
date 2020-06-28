package com.flywith24.wrapperlivedata

/**
 * @author Flywith24
 * @date   2020/6/28
 * time   17:14
 * description
 */
class ResultBuilder<T>() {
    var onLading: () -> Unit = {}
    var onSuccess: (data: T?) -> Unit = {}
    var onError: (Exception?) -> Unit = {}
}