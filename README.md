## 关于 LiveData 两个常用的姿势

### 使用包装类传递事件

我们在使用 LiveData 时可能会遇到「粘性」事件的问题，该问题可以使用包装类的方式解决。解决方案见 [[译] 在 SnackBar，Navigation 和其他事件中使用 LiveData（SingleLiveEvent 案例）](https://juejin.im/post/5b2b1b2cf265da5952314b63#heading-7)



使用时是这样的

``` kotlin
class ListViewModel : ViewModel {
    private val _navigateToDetails = MutableLiveData<Event<String>>()

    val navigateToDetails : LiveData<Event<String>>
        get() = _navigateToDetails


    fun userClicksOnButton(itemId: String) {
        _navigateToDetails.value = Event(itemId)  // Trigger the event by setting a new Event as a new value
    }
}

myViewModel.navigateToDetails.observe(this, Observer {
    it.getContentIfNotHandled()?.let { // Only proceed if the event has never been handled
        startActivity(DetailsActivity...)
    }
})
```



不过这样写甚是繁琐，我们可以使用更优雅的方式解决该问题

``` kotlin
//为 LiveData<Event<T>>提供类型别名，使用 EventLiveData<T> 即可
typealias EventMutableLiveData<T> = MutableLiveData<Event<T>>

typealias EventLiveData<T> = LiveData<Event<T>>
```

使用 `typealias` 关键字，我们可以提供一个类型别名，可以这样使用

```kotlin
//等价于 MutableLiveData<Event<Boolean>>(Event(false))
val eventContent = EventMutableLiveData<Boolean>(Event(false))
```



现在声明时不用多加一层泛型了，那么使用时还是很繁琐



我们可以借助 kotlin 的 扩展函数更优雅的使用



![event 扩展函数](https://gitee.com/flywith24/Album/raw/master/img/20200605115649.png)

![使用](https://gitee.com/flywith24/Album/raw/master/img/20200605121030.png)



demo 中封装了两种形式的 LiveData，一种为 `LiveData<Boolean>`，一种为 `EventLiveData<Boolean>`，当屏幕旋转时，前者会再次回调结果，而后者由于事件已被处理而不执行 onChanged，我们通过 Toast 可观察到这一现象

![](https://gitee.com/flywith24/Album/raw/master/img/20200605121634.gif)



[java 版的可参考](https://github.com/KunMinX/Jetpack-MVVM-Best-Practice)

![](https://gitee.com/flywith24/Album/raw/master/img/20200605122206.png)



### 封装带网络状态的数据

很多时候我们在获取网络数据时要封装一层网络状态，例如：加载中，成功，失败

![](https://gitee.com/flywith24/Album/raw/master/img/20200605115950.png)



在使用时我们遇到了和上面一样的问题，多层泛型用起来很麻烦

我们依然可以使用 typealias + 扩展函数来优雅的处理该问题

![typealias](https://gitee.com/flywith24/Album/raw/master/img/20200605120336.png)



![扩展函数](https://gitee.com/flywith24/Album/raw/master/img/20200605120400.png)



![使用](https://gitee.com/flywith24/Album/raw/master/img/20200605120455.png)



demo 截图

![demo](https://gitee.com/flywith24/Album/raw/master/img/20200605120721.gif)



### Demo

demo [在这](https://github.com/Flywith24/WrapperLiveDataDemo)



## 往期文章



该系列主要介绍一些「骚操作」，它未必适合生产环境使用，但是是一些比较新颖的思路



- [【奇技淫巧】AndroidStudio Nexus3.x搭建Maven私服遇到问题及解决方案](https://juejin.im/post/5e481a28f265da570b3f235c)


- [【奇技淫巧】什么？项目里gradle代码超过200行了！你可能需要 Kotlin+buildSrc Plugin](https://juejin.im/post/5e22c2ce6fb9a02ff67d41c3)


- [【奇技淫巧】gradle依赖查找太麻烦？这个插件可能帮到你](https://juejin.im/post/5e481a28f265da570b3f235c)


- [【奇技淫巧】Android组件化不使用 Router 如何实现组件间 activity 跳转](https://juejin.im/post/5e967f35f265da47d77cd4c3)


- [【奇技淫巧】新的图片加载库？基于Kotlin协程的图片加载库——Coil](https://juejin.im/post/5ebdfb0b6fb9a0436153db22)


- [【奇技淫巧】使用 Navigation + Dynamic Feature Module 实现模块化](https://juejin.im/post/5ec50ae46fb9a047a862124f)

- [【奇技淫巧】除了 buildSrc 还能这样统一配置依赖版本？巧用 includeBuild](https://juejin.im/post/5ecde219e51d457841190d08)



我的其他系列文章 [在这里](https://github.com/Flywith24/BlogList)

## 关于我

我是 [Fly_with24](https://flywith24.gitee.io/)
- [掘金](https://juejin.im/user/57c7f6870a2b58006b1cfd6c)

- [简书](https://www.jianshu.com/u/3d5ad6043d66)

- [Github](https://github.com/Flywith24)

  

![](https://gitee.com/flywith24/Album/raw/master/img/20200508153754.jpg)

 