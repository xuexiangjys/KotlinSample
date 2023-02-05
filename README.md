# KotlinSample

Kotlin相关的案例

## 关于我

| 公众号   | 掘金     |  知乎    |  CSDN   |   简书   |   思否  |   哔哩哔哩  |   今日头条
|---------|---------|--------- |---------|---------|---------|---------|---------|
| [我的Android开源之旅](https://t.1yb.co/Irse)  |  [点我](https://juejin.im/user/598feef55188257d592e56ed/posts)    |   [点我](https://www.zhihu.com/people/xuexiangjys/posts)       |   [点我](https://xuexiangjys.blog.csdn.net/)  |   [点我](https://www.jianshu.com/u/6bf605575337)  |   [点我](https://segmentfault.com/u/xuexiangjys)  |   [点我](https://space.bilibili.com/483850585)  |   [点我](https://img.rruu.net/image/5ff34ff7b02dd)

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

## Kotlin语法

### let、with、run、apply、also等作用域函数

函数	| 函数块对象引用   | 返回值	| 是否拓展函数    | 使用场景
|---|---|---|---|---
let	| it | Lambda表达式结果	| 是	| 1.适用于处理不为null的操作场景；2.明确一个变量所处的特定作用域范围内可使用。
with| this	| Lambda表达式结果	| 否(上下文对象作为参数)	| 适用于同一个对象的公有属性和函数调用。
run	| this	| Lambda表达式结果	| 是	| 适用于let函数和with函数的任何场景。对对象中的属性进行赋值和计算结果；在需要表达式的地方运行语句。
apply	| this	| 返回this(对象本身)	| 是 | 1.一般用于对象实例初始化的时候，需要对对象中的属性进行赋值；2.动态inflate一个View的时候需要给View绑定数据。
also| it| 返回this(对象本身) | 是 | 适用于let函数的任何场景，对传入的对象进行操作，一般用于多个拓展函数的链式调用。

* 1.`let`函数一般统一做空判断处理。
* 2.`with`函数在同一个对象的多个方法时，可以省去类名重复。
* 3.`run`函数适用于`let`函数和`with`函数的任何场景。`run`=`let`+`with`。
* 4.`apply`函数整体上和`run`函数相似，唯一不同就是它的返回值是对象本身。
* 5.`also`适用于`let`函数的任何场景，唯一不同就是它的返回值是对象本身。

### 变量基础

* var：可变变量    var <表识符> : <类型>? = null/指定值
* val：不可变变量   val <表识符> : <类型>? = null/指定值
* `?`：表示该变量可为空
* !!：不做空检查，判定一定非空，空的话会报错
* lateinit： 修饰var变量，默认你会初始化，不做空检查
* by lazy {}：修饰val变量，程序第一次使用到这个变量(或者对象)时再初始化
* const：修饰val变量，代表常量
* `data class`的copy()函数：进行对象的浅拷贝
* `vararg`：代表可变数量的参数
* `as`和`as?`：进行类型强转

### 位运算

> Kotlin中的 位运算符 只对Int和Long两种 数据类型 起作用

* 与: and(bits) == &
* 或: or(bits) == |
* 左移: shl(bits) == <<
* 右移: shr(bits) == >>

### 数组和集合操作

* 1.数组集合的创建

```kotlin
// 数组
val intArray = intArrayOf(1, 2, 3)
// 集合
val intList = listOf(1, 2, 3)
val intSet = setOf(4, 5, 4, 6, 7, 5)
// 可变集合
val mutableList = mutableListOf(1, 2, 3)
mutableList.add(4)
val mutableMap = mutableMapOf(
    1 to "1",
    2 to "2",
    3 to "3"
)
```

* 2.forEach：遍历

```kotlin
intArrayOf(1, 2, 3).forEach { i ->
  Log.e(TAG, "遍历：$i")
}
```

* 3.filter：过滤

```kotlin
// 遍历每个元素并按给的条件进行过滤，最终形成新的集合
// 结果：[4, 8]
intArrayOf(1, 2, 3, 4, 8).filter { i -> i > 3 }
```

* 4.map：变换、

```kotlin
// 遍历每个元素并执行给定表达式，最终形成新的集合
// 结果：[3, 6, 9]
intArrayOf(1, 2, 3).map { i -> i * 3 }
```

* 5.flatMap：变换、创建、合并

```kotlin
// 遍历每个元素，并为每个元素创建新的集合，最后合并到一个集合中
// 结果：[3, 6, 9]
intArrayOf(1, 2, 3).flatMap { i ->
    listOf("${i * 3}", "a") // 👈 生成新集合
}
```

* 6.find：寻找第一个符合要求的内容

```kotlin
listOf(1, 2, 3, 4).find { it > 2 }
```

* 7.any：判断是否存在符合要求的内容

```kotlin
listOf(1, 2, 3, 4).any { it > 2 }
```

* 8.count：获取符合要求内容的数量

```kotlin
listOf(1, 2, 3, 4).count { it > 2 }
```

* 9.maxBy：获取最大的内容

```kotlin
listOf(1, 2, 3, 4).maxByOrNull { it }
```

### 反射使用

* 1.反射设置/获取某个对象的成员属性: 

**xx.javaClass.getDeclaredField("xx")**

```kotlin
student.javaClass.getDeclaredField("Name").run {
  isAccessible = true
  // 设置
  set(student, "xuexiang") 
  // 获取
  get(student)
}
```

* 2.反射设置/获取某个对象的静态属性: 

**xx::class.java.getDeclaredField("xx")**

```kotlin
Student::class.java.getDeclaredField("TotalNumber").run {
  isAccessible = true
  // 设置
  set(null, 1111)
  // 获取
  get(null)
}
```

* 3.反射执行某对象的成员方法: 

**xx.javaClass.getDeclaredMethod(xx, xx::class.java)**

```kotlin
student.javaClass.getDeclaredMethod("getName", Integer::class.java).run {
  isAccessible = true
  Log.e(TAG, "invokeMethod: ${invoke(student, 23)}")
}
```

* 4.反射执行某个类的静态方法: 

**xx::class.java.getDeclaredMethod(xx, xx::class.java)**

```kotlin
Student::class.java.getDeclaredMethod("incrementTotalNumber", Int::class.java).run {
  isAccessible = true
  Log.e(TAG, "invokeStaticMethod: ${invoke(null, 23)}")
}
```

* 5.反射构建实例: 

**xx::class.java.getDeclaredConstructor(xx::class.java...)**

```kotlin
Student::class.java.getDeclaredConstructor(Int::class.java, String::class.java).run {
  isAccessible = true
  val std = newInstance(666, "xiaohuang")
  Log.e(TAG, "newInstance: $std")
  
  GoodStudent::class.java.getDeclaredConstructor(Student::class.java).run {
    isAccessible = true
    val goodStd = newInstance(std)
    Log.e(TAG, "GoodStudent: $goodStd")
  }
}
```

----


## 相关文章

* [Kotlin 语言中文站](https://www.kotlincn.net/docs/reference/)
* [Kotlin 的变量、函数和类型](https://juejin.cn/post/6844903918888026126)
* [Kotlin 里那些「不是那么写的」](https://juejin.cn/post/6844903920536551431)
* [Kotlin 的协程用力瞥一眼](https://juejin.cn/post/6844903949686800392)
* [Kotlin 里那些「更方便的」](https://juejin.cn/post/6844903923061358605)
* [万字长文 - Kotlin 协程进阶](https://juejin.cn/post/6950616789390721037)
* [Kotlin Coroutines Flow 系列(二) Flow VS RxJava2](https://juejin.cn/post/6844904057534939149)
* [Kotlin 协程实战进阶](https://blog.csdn.net/m0_37796683/article/details/119424009)
* [Kotlin系列文章](https://blog.csdn.net/m0_37796683/category_10088599.html)

## 如果觉得项目还不错，可以考虑打赏一波

> 你的打赏是我维护的动力，我将会列出所有打赏人员的清单在下方作为凭证，打赏前请留下打赏项目的备注！

![pay.png](https://raw.githubusercontent.com/xuexiangjys/Resource/master/img/pay/pay.png)

## 联系方式

> 更多资讯内容，欢迎扫描关注我的个人微信公众号:【我的Android开源之旅】

![](https://s1.ax1x.com/2022/04/27/LbGMJH.jpg)
