# KotlinSample

Kotlin相关的案例

## 关于我

| 公众号   | 掘金     |  知乎    |  CSDN   |   简书   |   思否  |   哔哩哔哩  |   今日头条
|---------|---------|--------- |---------|---------|---------|---------|---------|
| [我的Android开源之旅](https://ss.im5i.com/2021/06/14/6tqAU.png)  |  [点我](https://juejin.im/user/598feef55188257d592e56ed/posts)    |   [点我](https://www.zhihu.com/people/xuexiangjys/posts)       |   [点我](https://xuexiangjys.blog.csdn.net/)  |   [点我](https://www.jianshu.com/u/6bf605575337)  |   [点我](https://segmentfault.com/u/xuexiangjys)  |   [点我](https://space.bilibili.com/483850585)  |   [点我](https://img.rruu.net/image/5ff34ff7b02dd)

----

## Kotlin Coroutines

* Coroutine：协程的概念和原理：协程是什么以及它的作用和特点，图解分析协程的工作原理。
* Coroutine builders：协程的构建，协程构建器创建协程的三种方式。
* CoroutineScope：协程作用域，协程运行的上下文环境，用来提供函数支持，也是用来增加限制。常见的7种作用域（包含Lifecycle支持的协程）以及作用域的分类和行为规则。
* Job & Deferred：协程的句柄，实现对协程的控制和管理，Deferred有返回值。
* CoroutineDispatcher：协程调度器,确定相应的协程在那个线程上执行，调度器的四种模式以及withContext主要是为了切换协程上下文环境。
* CoroutineContext：协程上下文，表示协程的运行环境，包括协程调度器、代表协程本身的Job、协程名称、协程ID以及组合上下文的使用。
* CoroutineStart：一个枚举类，为协程构建器定义四中启动模式。
* suspend：挂起函数，Kotlin 协程最核心的关键字。一种避免阻塞线程并用更简单、更可控的操作替代线程阻塞的方法：协程挂起和恢复。

### 集成 Coroutines

```
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0"
```

### CoroutineContext

> CoroutineContext即协程的上下文，是 Kotlin 协程的一个基本结构单元。巧妙的运用协程上下文是至关重要的，以此来实现正确的线程行为、生命周期、异常以及调试。它包含用户定义的一些数据集合，这些数据与协程密切相关。

* Job: 控制协程的生命周期；
    * Deferred（async创建返回，可调用`await`获取返回结果)
    * SupervisorJob(特殊的 Job，里面的子Job不相互影响，一个子Job失败了，不影响其他子Job的执行)
* CoroutineDispatcher: 向合适的线程分发任务；
    * Dispatchers.Default（一个CPU密集型任务调度器）
    * Dispatchers.IO（和Default共用一个共享的线程池）
    * Dispatchers.Unconfined（未定义线程池，默认在启动线程）
    * Dispatchers.Main（主线程，在Android上就是UI线程）
* CoroutineName: 协程的名称，调试的时候很有用；
* CoroutineExceptionHandler: 处理未被捕捉的异常。

### CoroutineStart

> 协程启动模式

* CoroutineStart.DEFAULT: 协程创建后立即开始调度(可能在执行前被取消)
* CoroutineStart.ATOMIC: 协程创建后立即开始调度，协程执行到第一个挂起点之前不响应取消(将调度和执行两个步骤合二为一)
* CoroutineStart.LAZY: 只要协程被需要时，包括主动调用该协程的start、join或者await等函数时才会开始调度
* CoroutineStart.UNDISPATCHED: 立即执行协程体中的内容（没有调度的过程）

### CoroutineScope

> 协程作用域。定义协程必须指定其 `CoroutineScope`。`CoroutineScope` 可被看作是一个具有超能力的 `ExecutorService` 的轻量级版本。`CoroutineScope` 会跟踪所有协程，同样它还可以取消由它所启动的所有协程。

* GlobalScope - 全局作用域，不推荐使用
* runBlocking{} - 主要用于测试
* MainScope - 可用于开发（SupervisorJob() + Dispatchers.Main）
* LifecycleOwner.lifecycleScope - 推荐使用
* ViewModel.viewModelScope - 推荐使用

### coroutineScope & supervisorScope

> 两个函数都是挂起函数，需要运行在协程内或挂起函数内。

`coroutineScope`和`supervisorScope`都会返回一个作用域，它俩的差别就是异常传播。

* `coroutineScope` 内部的异常会向上传播，子协程未捕获的异常会向上传递给父协程，任何一个子协程异常退出，会导致整体的退出；
* `supervisorScope` 内部的异常不会向上传播，一个子协程异常退出，不会影响父协程和兄弟协程的运行。

----

## 相关文章

* [Kotlin 的变量、函数和类型](https://juejin.cn/post/6844903918888026126)
* [Kotlin 里那些「不是那么写的」](https://juejin.cn/post/6844903920536551431)
* [Kotlin 的协程用力瞥一眼](https://juejin.cn/post/6844903949686800392)
* [万字长文 - Kotlin 协程进阶](https://juejin.cn/post/6950616789390721037)
* [Kotlin Coroutines Flow 系列(二) Flow VS RxJava2](https://juejin.cn/post/6844904057534939149)
* [Kotlin 协程实战进阶](https://blog.csdn.net/m0_37796683/article/details/119424009)

## 如果觉得项目还不错，可以考虑打赏一波

> 你的打赏是我维护的动力，我将会列出所有打赏人员的清单在下方作为凭证，打赏前请留下打赏项目的备注！

![pay.png](https://raw.githubusercontent.com/xuexiangjys/Resource/master/img/pay/pay.png)

## 联系方式

> 更多资讯内容，欢迎扫描关注我的个人微信公众号:【我的Android开源之旅】

![](https://s1.ax1x.com/2022/04/27/LbGMJH.jpg)
