## [Java 基础常见知识点&面试题总结(上)](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/java-basic-questions-01.md)

#### Java 语言有哪些特点？

#### Java SE vs Java EE

#### ⭐️ JVM vs JDK vs JRE

#### ⭐️ 什么是字节码？采用字节码的好处是什么？

#### ⭐️ 为什么说 Java 语言“编译与解释并存”？

#### AOT 有什么优点？为什么不全部使用 AOT 呢？

#### Oracle JDK vs OpenJDK

#### 注释有哪几种形式？

#### 标识符和关键字的区别是什么？

#### Java 语言关键字有哪些？

#### ⭐️ 自增自减运算符

#### ⭐️ 移位运算符

### ⭐️ 基本数据类型

#### Java 中的几种基本数据类型了解么？

#### 基本类型和包装类型的区别？

#### 包装类型的缓存机制了解么？

#### 自动装箱与拆箱了解吗？原理是什么？

#### 为什么浮点数运算的时候会有精度丢失的风险？

#### 如何解决浮点数运算的精度丢失问题？

#### 超过 long 整型的数据应该如何表示？

#### ⭐️ 成员变量与局部变量的区别？

#### 静态变量有什么作用？

#### 字符型常量和字符串常量的区别？

#### 什么是方法的返回值？方法有哪几种类型？

#### 静态方法为什么不能调用非静态成员？

#### ⭐️ 静态方法和实例方法有何不同？

#### ⭐️ 重载和重写有什么区别？

#### 什么是可变长参数？

## [Java 基础常见知识点&面试题总结(中)](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/java-basic-questions-02.md)

### 面向对象基础

#### ⭐️ 面向对象和面向过程的区别

#### 创建一个对象用什么运算符？对象实例与对象引用有何不同？

#### ⭐️ 对象的相等和引用相等的区别

#### 如果一个类没有声明构造方法，该程序能正确执行吗？

#### 构造方法有哪些特点？是否可被 override?

#### ⭐️ 面向对象三大特征

#### ⭐️ 接口和抽象类有什么共同点和区别？

##### 接口和抽象类的共同点

##### 接口和抽象类的区别

#### 深拷贝和浅拷贝区别了解吗？什么是引用拷贝？


#### Object 类的常见方法有哪些？

#### == 和 equals() 的区别

#### hashCode() 有什么用？

#### 为什么要有 hashCode？

#### 为什么重写 equals() 时必须重写 hashCode() 方法？

#### ⭐️⭐️ String、StringBuffer、StringBuilder 的区别？

#### ⭐️ String 为什么是不可变的？

#### ⭐️ 字符串拼接用“+” 还是 StringBuilder?

#### String#equals() 和 Object#equals() 有何区别？

#### ⭐️ 字符串常量池的作用了解吗？

#### ⭐️ String s1 = new String("abc");这句话创建了几个字符串对象？

#### String#intern 方法有什么作用？

#### String 类型的变量和常量做“+”运算时发生了什么？

## [Java 基础常见知识点&面试题总结(下)](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/java-basic-questions-03.md)

#### Exception 和 Error 有什么区别？

#### ClassNotFoundException 和 NoClassDefFoundError 的区别

#### ⭐️ Checked Exception 和 Unchecked Exception 有什么区别？

#### 你更倾向于使用 Checked Exception 还是 Unchecked Exception？

#### Throwable 类常用方法有哪些？

#### try-catch-finally 如何使用？

#### finally 中的代码一定会执行吗？

#### 如何使用 `try-with-resources` 代替 `try-catch-finally`？

#### ⭐️ 异常使用有哪些需要注意的地方？

#### 什么是泛型？有什么作用？

#### 泛型的使用方式有哪几种？

#### 项目中哪里用到了泛型？

#### 什么是反射？

#### 反射有什么优缺点？

#### 反射的应用场景？

#### 如何实现动态代理？

#### 静态代理和动态代理有什么区别？

#### ⭐️ JDK 动态代理和 CGLIB 动态代理有什么区别？

#### ⭐️ 介绍一下动态代理在框架中的实际应用场景

#### 何谓注解？

#### 注解的解析方法有哪几种？

#### 何谓 SPI?

#### SPI 和 API 有什么区别？

#### SPI 的优缺点？

#### 什么是序列化？什么是反序列化？

#### 如果有些字段不想进行序列化怎么办？

#### 常见序列化协议有哪些？

#### 为什么不推荐使用 JDK 自带的序列化？

#### Java IO 流了解吗？

#### I/O 流为什么要分为字节流和字符流呢？

#### Java IO 中的设计模式有哪些？

#### ⭐️ BIO、NIO 和 AIO 的区别？

#### 什么是语法糖？

#### Java 中有哪些常见的语法糖？

## [为什么 Java 中只有值传递？](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/why-there-only-value-passing-in-java.md)

### 形参&实参

### 值传递&引用传递

### 为什么 Java 只有值传递？

#### 案例 1：传递基本类型参数

#### 案例 2：传递引用类型参数 1

#### 案例 3：传递引用类型参数 2

### 为什么 Java 不引入引用传递呢？


## [Java 序列化详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/serialization.md)

### 什么是序列化和反序列化？

### 常见序列化协议有哪些？

#### JDK 自带的序列化方式

#### Kryo

#### Protobuf

#### ProtoStuff

#### Hessian

#

## [泛型&通配符详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/generics-and-wildcards.md)

#### 什么是泛型？有什么作用？

#### 泛型的使用方式有哪几种？

#### 项目中哪里用到了泛型？

#### 什么是泛型擦除机制？为什么要擦除？

#### 什么是桥方法？

#### 泛型有哪些限制？为什么？

#### 以下代码是否能编译，为什么？

#### 什么是通配符？有什么作用？

#### 通配符？和常用的泛型 T 之间有什么区别？

#### 什么是无界通配符？

#### 什么是上边界通配符？什么是下边界通配符？

#### 以下代码是否能编译，为什么？

## [Java 反射机制详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/reflection.md)

### 何为反射？

### 反射的应用场景了解么？

### 谈谈反射机制的优缺点

### 反射实战

#### 获取 Class 对象的四种方式

#### 反射的一些基本操作

## [Java 代理模式详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/proxy.md)

### 1. 代理模式

### 2. 静态代理

### 3. 动态代理

#### 3.1. JDK 动态代理机制

#### 3.2. CGLIB 动态代理机制

#### 3.3. JDK 动态代理和 CGLIB 动态代理对比

### 4. 静态代理和动态代理的对比


## [BigDecimal 详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/bigdecimal.md)

### BigDecimal 介绍

#### 创建

#### 加减乘除

#### 大小比较

#### 保留几位小数

### BigDecimal 等值比较问题

## [Java 魔法类 Unsafe 详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/unsafe.md)

### Unsafe 介绍

### Unsafe 创建

### Unsafe 功能

#### 内存操作

##### 介绍

##### 典型应用

#### 内存屏障

##### 介绍

##### 典型应用

#### 对象操作

##### 介绍

##### 典型应用

#### 数组操作

##### 介绍

##### 典型应用

#### CAS 操作

##### 介绍

##### 典型应用

#### 线程调度

##### 介绍

##### 典型应用

#### Class 操作

##### 介绍

##### 典型应用

#### 系统信息

##### 介绍

##### 典型应用



## [Java SPI 机制详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/spi.md)

### SPI 介绍

#### 何谓 SPI?

#### SPI 和 API 有什么区别？

### 实战演示

#### Service Provider Interface

#### Service Provider

#### 效果展示

### ServiceLoader 是什么？


## [Java 语法糖详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/syntactic-sugar.md)

### 什么是语法糖？

### Java 中有哪些常见的语法糖？

#### switch 支持 String 与枚举

#### 泛型

#### 自动装箱与拆箱

#### 可变长参数

#### 枚举

#### 内部类

#### 条件编译

#### 断言

#### 数值字面量

#### for-each

#### try-with-resource

#### Lambda 表达式

### 可能遇到的坑

#### 泛型

#### 自动装箱与拆箱

#### 增强 for 循环



# Java 集合

## [Java 集合常见知识点&面试题总结(上)](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/collection/java-collection-questions-01.md)

### 集合概述

#### Java 集合概览

#### ⭐️ 说说 List, Set, Queue, Map 四者的区别？

#### 集合框架底层数据结构总结

##### List

##### Set

##### Queue

##### Map

#### 如何选用集合？

#### 为什么要使用集合？

### List

#### ⭐️ ArrayList 和 Array（数组）的区别？

#### ArrayList 和 Vector 的区别?（了解即可）

#### Vector 和 Stack 的区别?（了解即可）

#### ArrayList 可以添加 null 值吗？

#### ⭐️ ArrayList 插入和删除元素的时间复杂度？

#### ⭐️ LinkedList 插入和删除元素的时间复杂度？

#### LinkedList 为什么不能实现 RandomAccess 接口？

#### ⭐️ ArrayList 与 LinkedList 区别？

##### 补充内容: 双向链表和双向循环链表

##### 补充内容:RandomAccess 接口

#### ⭐️ 说一说 ArrayList 的扩容机制吧

#### ⭐️ 集合中的 fail-fast 和 fail-safe 是什么？

### Set

#### Comparable 和 Comparator 的区别

##### Comparator 定制排序

##### 重写 compareTo 方法实现按年龄来排序

#### 无序性和不可重复性的含义是什么

#### 比较 HashSet、LinkedHashSet 和 TreeSet 三者的异同

#### Queue 与 Deque 的区别

#### ArrayDeque 与 LinkedList 的区别

#### 说一说 PriorityQueue

#### 什么是 BlockingQueue？

#### BlockingQueue 的实现类有哪些？

#### ⭐️ ArrayBlockingQueue 和 LinkedBlockingQueue 有什么区别？

### 数据结构延伸阅读

## [Java 集合常见知识点&面试题总结(下)](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/collection/java-collection-questions-02.md)

### Map（重要）

#### ⭐️ HashMap 和 Hashtable 的区别

#### HashMap 和 HashSet 区别

#### ⭐️ HashMap 和 TreeMap 区别

#### HashSet 如何检查重复？

#### ⭐️ HashMap 的底层实现

##### JDK1.8 之前

##### JDK1.8 之后

#### ⭐️ HashMap 的长度为什么是 2 的幂次方

#### ⭐️ HashMap 多线程操作导致死循环问题

#### ⭐️ HashMap 为什么线程不安全？

#### HashMap 常见的遍历方式？

#### ⭐️ ConcurrentHashMap 和 Hashtable 的区别

#### ⭐️ ConcurrentHashMap 线程安全的具体实现方式/底层具体实现

#### ⭐️ JDK 1.7 和 JDK 1.8 的 ConcurrentHashMap 实现有什么不同？

#### ConcurrentHashMap 为什么 key 和 value 不能为 null？

#### ⭐️ ConcurrentHashMap 能保证复合操作的原子性吗？

### Collections 工具类（不重要）

#### 排序操作

#### 查找，替换操作

#### 同步控制

## [Java 容器使用注意事项总结](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/collection/java-collection-precautions-for-use.md)

### 集合判空

### 集合转 Map

### 集合遍历

### 集合去重

### 集合转数组

### 数组转集合

# Java IO

## [IO 基础知识总结](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/io/io-basis.md)

### IO 流简介

### 字节流

#### InputStream（字节输入流）

#### OutputStream（字节输出流）

### 字符流

#### Reader（字符输入流）

#### Writer（字符输出流）

### 字节缓冲流

#### BufferedInputStream（字节缓冲输入流）

#### BufferedOutputStream（字节缓冲输出流）

### 字符缓冲流

### 打印流

### 随机访问流

## [IO 设计模式总结](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/io/io-design-patterns.md)

### 装饰器模式

### 适配器模式

### 工厂模式

### 观察者模式

## [IO 模型详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/io/io-model.md)

### 前言

### I/O

#### 何为 I/O?

#### 有哪些常见的 IO 模型？

### Java 中 3 种常见 IO 模型

#### BIO (Blocking I/O)

#### NIO (New I/O)

#### AIO (Asynchronous I/O)

### 参考

## [NIO 核心知识总结](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/io/nio-basis.md)

### NIO 简介

### NIO 核心组件

#### Buffer（缓冲区）

#### Channel（通道）

#### Selector（选择器）

# Java 并发

## [Java 并发常见知识点&面试题总结（上）](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/java-concurrent-questions-01.md)

### 线程

#### ⭐️ 什么是线程和进程？

##### 何为进程？

##### 何为线程？

#### Java 线程和操作系统的线程有啥区别？

#### ⭐️ 请简要描述线程与进程的关系，区别及优缺点？

##### 程序计数器为什么是私有的？

##### 虚拟机栈和本地方法栈为什么是私有的？

##### 一句话简单了解堆和方法区

#### 如何创建线程？

#### ⭐️ 说说线程的生命周期和状态？

#### 什么是线程上下文切换？

#### Thread#sleep() 方法和 Object#wait() 方法对比

#### 为什么 wait() 方法不定义在 Thread 中？

#### 可以直接调用 Thread 类的 run 方法吗？

### 多线程

#### 并发与并行的区别

#### 同步和异步的区别

#### ⭐️ 为什么要使用多线程？

#### ⭐️ 单核 CPU 支持 Java 多线程吗？

#### ⭐️ 单核 CPU 上运行多个线程效率一定会高吗？

#### 使用多线程可能带来什么问题？

#### 如何理解线程安全和不安全？

### ⭐️ 死锁

#### 什么是线程死锁？

#### 如何检测死锁？

#### 如何预防和避免线程死锁？

## [Java 并发常见知识点&面试题总结（中）](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/java-concurrent-questions-02.md)

### ⭐️ JMM（Java 内存模型）

### ⭐️ volatile 关键字

#### 如何保证变量的可见性？

#### 如何禁止指令重排序？

##### 4 种内存屏障类型

##### volatile 读写操作的内存屏障插入策略

##### 从内存屏障角度理解 DCL 必须使用 volatile

#### volatile 与 happens-before 的关系

#### volatile 可以保证原子性么？

### ⭐️ 乐观锁和悲观锁

#### 什么是悲观锁？

#### 什么是乐观锁？

#### 如何实现乐观锁？

##### 版本号机制

##### CAS 算法

#### Java 中 CAS 是如何实现的？

#### CAS 算法存在哪些问题？

##### ABA 问题

##### 循环时间长开销大

##### 只能保证一个共享变量的原子操作

#

### synchronized 关键字

#### synchronized 是什么？有什么用？

#### 如何使用 synchronized？

#### 构造方法可以用 synchronized 修饰么？

#### ⭐️ synchronized 底层原理了解吗？

##### synchronized 同步语句块的情况

##### synchronized 修饰方法的情况

##

#### JDK1.6 之后的 synchronized 底层做了哪些优化？锁升级原理了解吗？

#### synchronized 的偏向锁为什么被废弃了？

#### ⭐️ synchronized 和 volatile 有什么区别？

##### volatile 与 synchronized 的性能对比

### ReentrantLock

#### ReentrantLock 是什么？

#### 公平锁和非公平锁有什么区别？

#### ⭐️ synchronized 和 ReentrantLock 有什么区别？

##### 两者都是可重入锁

##### synchronized 依赖于 JVM 而 ReentrantLock 依赖于 API

##### ReentrantLock 比 synchronized 增加了一些高级功能

#### 可中断锁和不可中断锁有什么区别？

#### ReentrantReadWriteLock 是什么？

#### ReentrantReadWriteLock 适合什么场景？

#### 共享锁和独占锁有什么区别？

#### 线程持有读锁还能获取写锁吗？

#### 读锁为什么不能升级为写锁？

#### StampedLock 是什么？

#### StampedLock 的性能为什么更好？

#### StampedLock 适合什么场景？

#### StampedLock 的底层原理了解吗？

### Atomic 原子类

### 参考

## [Java 并发常见知识点&面试题总结（下）](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/java-concurrent-questions-03.md)

#### ThreadLocal 有什么用？

#### ⭐️ ThreadLocal 原理了解吗？

#### ⭐️ ThreadLocal 内存泄露问题是怎么导致的？

##### 为什么 Entry 的 key 要设计为弱引用？

##### 线程池场景下的特殊风险

##### 阿里巴巴 Java 开发手册的强制规约

#### ⭐️ 如何跨线程传递 ThreadLocal 的值？

##### InheritableThreadLocal 原理

##### TransmittableThreadLocal 原理


### 线程池

#### 什么是线程池？

#### ⭐️ 为什么要用线程池？

#### 如何创建线程池？

#### ⭐️ 为什么不推荐使用内置线程池？

#### ⭐️ 线程池常见参数有哪些？如何解释？

#### 线程池的核心线程会被回收吗？

#### 核心线程空闲时处于什么状态？

#### ⭐️ 线程池的拒绝策略有哪些？

#### 如果希望饱和时尽量不丢弃任务，应该选择哪个拒绝策略？

#### CallerRunsPolicy 拒绝策略有什么风险？如何解决？

#### 线程池常用的阻塞队列有哪些？

#### ⭐️ 线程池处理任务的流程了解吗？

#### ⭐️ 线程池中线程异常后，销毁还是复用？

#### ⭐️ 如何给线程池命名？

#### 如何设定线程池的大小？

#### ⭐️ 如何动态修改线程池的参数？

#### ⭐️ 如何设计一个能够根据任务的优先级来执行的线程池？

### Future

#### Future 接口有什么用？

#### Callable 和 Future 有什么关系？

#### CompletableFuture 类有什么用？

#### ⭐️ 一个任务需要依赖另外两个任务执行完之后再执行，怎么设计？

#### ⭐️ 使用 CompletableFuture，有一个任务失败，如何处理异常？

#### ⭐️ 在使用 CompletableFuture 的时候为什么要自定义线程池？

### AQS

#### AQS 是什么？

#### ⭐️ AQS 的原理是什么？

#### Semaphore 有什么用？

#### Semaphore 的原理是什么？

#### CountDownLatch 有什么用？

#### CountDownLatch 的原理是什么？

#### 用过 CountDownLatch 么？什么场景下用的？

#### CyclicBarrier 有什么用？

#### CyclicBarrier 的原理是什么？


## [Java 锁详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/java-lock.md)

### 一把锁到底保护什么？

### 互斥锁：同一时刻只允许一个线程进入

### 读写锁：读读共享，写操作独占

### 自旋锁：不阻塞，先原地等一会儿

### 悲观锁、乐观锁和 CAS 的位置

### synchronized 的底层：monitor、字节码和内存语义

### synchronized 锁优化：别把“锁升级”背成固定口诀

### ReentrantLock、Condition 和 AQS 怎么接上

### Java 锁该怎么选？

### 常见坑



### 参考资料

## [乐观锁和悲观锁详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/optimistic-lock-and-pessimistic-lock.md)

### 什么是悲观锁？

### 什么是乐观锁？

### 如何实现乐观锁？

#### 版本号机制

#### CAS 算法


## [CAS 详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/cas.md)

### Java 中 CAS 是如何实现的？

### CAS 算法存在哪些问题？

#### ABA 问题

#### 循环时间长开销大

#### 只能保证一个共享变量的原子操作

## [JMM（Java 内存模型）详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/jmm.md)

### 从 CPU 缓存模型说起

### 指令重排序

### JMM(Java Memory Model)

#### 什么是 JMM？为什么需要 JMM？

#### JMM 是如何抽象线程和主内存之间的关系？

#### Java 内存区域和 JMM 有何区别？

#### happens-before 原则是什么？

#### happens-before 常见规则有哪些？谈谈你的理解？

#### happens-before 和 JMM 什么关系？


#### 原子性

#### 可见性

#### 有序性



## [Java 线程池详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/java-thread-pool-summary.md)

### 线程池介绍

### Executor 框架介绍

### ThreadPoolExecutor 类介绍（重要）

#### 线程池参数分析

#### 线程池生命周期状态

#### Worker 工作线程机制

#### 4 种拒绝策略的实际应用场景

#### 线程池创建的两种方式

#### 线程池常用的阻塞队列总结

### 线程池原理分析（重要）

#### 线程池示例代码

#### 线程池原理分析

#### 几个常见的对比

##### `Runnable` vs `Callable`

##### `execute()` vs `submit()`

##### `shutdown()`VS`shutdownNow()`

##### `isTerminated()` VS `isShutdown()`

### 几种常见的内置线程池

#### FixedThreadPool

##### 介绍

##### 执行任务过程介绍

##### 为什么不推荐使用 `FixedThreadPool`？

#### SingleThreadExecutor

##### 介绍

##### 执行任务过程介绍

##### 为什么不推荐使用 `SingleThreadExecutor`？

#### CachedThreadPool

##### 介绍

##### 执行任务过程介绍

##### 为什么不推荐使用 `CachedThreadPool`？

#### ScheduledThreadPool

##### 介绍

##### ScheduledThreadPoolExecutor 和 Timer 对比

### 线程池最佳实践


## [Java 线程池最佳实践](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/java-thread-pool-best-practices.md)

### 正确声明线程池

### 线程池参数怎么估算？

### 有界队列应该设置多大？

### 线程数为什么要和下游容量一起设计？

### 队列堆积后怎么处理？

### CallerRunsPolicy 能形成背压吗？

### 不同业务要不要隔离线程池？

### 任务超时后会自动停止吗？

### 异步任务的异常为什么容易丢？

### 线程池应该监控哪些指标？

### 动态调参能解决什么问题？

### 还有哪些容易忽略的问题？

#### 不要重复创建线程池

#### 不要让长任务占满共享线程池

#### 清理线程上下文

#### 正确关闭线程池

### 使用虚拟线程后还需要传统线程池吗？

### 面试中怎么回答线程池参数配置？


## [ThreadLocal 详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/threadlocal.md)

### 前言

### 目录

### `ThreadLocal` 代码演示

### `ThreadLocal` 的数据结构

### GC 之后 key 是否为 null？

### `ThreadLocal.set()` 方法源码详解

### `ThreadLocalMap` Hash 算法

### `ThreadLocalMap` Hash 冲突

### `ThreadLocalMap.set()` 详解

#### `ThreadLocalMap.set()` 原理图解

#### `ThreadLocalMap.set()` 源码详解

### `ThreadLocalMap` 过期 key 的探测式清理流程

### `ThreadLocalMap` 扩容机制

### `ThreadLocalMap.get()` 详解

#### `ThreadLocalMap.get()` 图解

#### `ThreadLocalMap.get()` 源码详解

### `ThreadLocalMap` 过期 key 的启发式清理流程

### `InheritableThreadLocal`

### `ThreadLocal` 项目中使用实战

#### `ThreadLocal` 使用场景

#### Feign 远程调用解决方案

#### 线程池异步调用，requestId 传递

#### 使用 MQ 发送消息给第三方系统

## [Java 并发容器总结](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/java-concurrent-collections.md)

### ConcurrentHashMap

### CopyOnWriteArrayList

### ConcurrentLinkedQueue

### BlockingQueue

#### BlockingQueue 简介

#### ArrayBlockingQueue

#### LinkedBlockingQueue

#### PriorityBlockingQueue

### ConcurrentSkipListMap


## [Atomic 原子类总结](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/atomic-classes.md)

### Atomic 原子类介绍

### 基本类型原子类

### 数组类型原子类

### 引用类型原子类

### 对象的属性修改类型原子类

## [AQS 详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/aqs.md)

### AQS 介绍

### AQS 原理

#### AQS 快速了解

##### AQS 的作用是什么？

##### AQS 为什么使用 CLH 锁队列的变体？

##### AQS 的性能比较好，原因是什么？

##### AQS 中为什么 Node 节点需要不同的状态？

#### AQS 核心思想

#### Node 节点 waitStatus 状态含义

#### 自定义同步器

#### AQS 资源共享方式

#### 独占模式与共享模式的深入对比

##### 特性对比

##### `state` 在不同同步器中的语义

#### AQS 资源获取源码分析（独占模式）

##### `tryAcquire()` 分析

##### `addWaiter()` 分析

##### `acquireQueued()` 分析

#### AQS 资源释放源码分析（独占模式）

#### 图解 AQS 工作原理（独占模式）

#### AQS 资源获取源码分析（共享模式）

##### `tryAcquireShared()` 分析

##### `doAcquireShared()` 分析

##### 为什么需要 `PROPAGATE` 状态？

#### AQS 资源释放源码分析（共享模式）

#### Condition 条件队列的工作机制

##### 什么是 Condition？

##### AQS 中的两种队列

##### Condition 的核心工作流程

##### `await()` 核心源码分析

#### 公平锁与非公平锁的性能差异分析


## [CompletableFuture 详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/concurrent/completablefuture-intro.md)

### Future 介绍

### CompletableFuture 介绍

### CompletableFuture 常见操作

#### 创建 CompletableFuture

##### new 关键字

##### 静态工厂方法

#### 处理异步结算的结果

#### 异常处理

#### 组合 CompletableFuture

#### 等待多个 CompletableFuture 完成

### CompletableFuture 使用建议

#### 使用自定义线程池

#### 尽量避免使用 get()

#### 正确进行异常处理

#### 合理组合多个异步任务


# MySQL

## [MySQL 常见知识点&面试题总结](https://github.com/Snailclimb/JavaGuide/blob/main/docs/database/mysql/mysql-questions-01.md)

### MySQL 基础

#### 什么是关系型数据库？

#### 什么是 SQL？

#### 什么是 MySQL？

#### ⭐️MySQL 有什么优点？

### MySQL 字段类型

#### ⭐️整数类型的 UNSIGNED 属性有什么用？

#### CHAR 和 VARCHAR 的区别是什么？

#### VARCHAR(100)和 VARCHAR(10)的区别是什么？

#### DECIMAL 和 FLOAT/DOUBLE 的区别是什么？

#### 为什么不推荐使用 TEXT 和 BLOB？

#### ⭐️DATETIME 和 TIMESTAMP 的区别是什么？如何选择？

#### NULL 和 '' 的区别是什么？

#### ⭐️Boolean 类型如何表示？

#### ⭐️手机号存储用 INT 还是 VARCHAR？

### MySQL 基础架构

### MySQL 存储引擎

#### MySQL 支持哪些存储引擎？默认使用哪个？

#### MySQL 存储引擎架构了解吗？

#### ⭐️MyISAM 和 InnoDB 有什么区别？

#### MyISAM 和 InnoDB 如何选择？

### ⭐️MySQL 索引

#### 索引是什么？

#### 索引为什么快？

#### MySQL 索引底层数据结构是什么？

#### 为什么 InnoDB 没有使用哈希作为索引的数据结构？

#### 为什么 InnoDB 没有使用 B 树作为索引的数据结构？

#### 什么是覆盖索引？

#### 请解释一下 MySQL 的联合索引及其最左前缀原则

#### SELECT \* 会导致索引失效吗？

#### 哪些字段适合创建索引？

#### 索引失效的原因有哪些？

### MySQL 查询缓存

### ⭐️MySQL 日志

### ⭐️MySQL 事务

#### 什么是事务？

#### 什么是数据库事务？

#### 并发事务带来了哪些问题?

##### 脏读（Dirty read）

##### 丢失修改（Lost to modify）

##### 不可重复读（Unrepeatable read）

##### 幻读（Phantom read）

#### 不可重复读和幻读有什么区别？

#### 并发事务的控制方式有哪些？

#### SQL 标准定义了哪些事务隔离级别?

#### MySQL 的默认隔离级别是什么?

#### MySQL 的隔离级别是基于锁实现的吗？

### MySQL 锁

#### 表级锁和行级锁了解吗？有什么区别？

#### 行级锁的使用有什么注意事项？

#### InnoDB 有哪几类行锁？

#### 共享锁和排他锁呢？

#### 意向锁有什么作用？

#### 当前读和快照读有什么区别？

#### 自增锁有了解吗？

### ⭐️MySQL 性能优化

#### 能用 MySQL 直接存储文件（比如图片）吗？

#### MySQL 如何存储 IP 地址？

#### 有哪些常见的 SQL 优化手段？

#### 如何分析 SQL 的性能？

#### 读写分离和分库分表了解吗？

#### 深度分页如何优化？

#### 数据冷热分离如何做？

#### MySQL 性能怎么优化？

### MySQL 学习资料推荐

## [MySQL 高性能优化规范建议总结](https://github.com/Snailclimb/JavaGuide/blob/main/docs/database/mysql/mysql-high-performance-optimization-specification-recommendations.md)

### 数据库命名规范

### 数据库基本设计规范

#### 所有表必须使用 InnoDB 存储引擎

#### 数据库和表的字符集统一使用 UTF8

#### 所有表和字段都需要添加注释

#### 尽量控制单表数据量的大小，建议控制在 500 万以内

#### 谨慎使用 MySQL 分区表

#### 经常一起使用的列放到一个表中

#### 禁止在表中建立预留字段

#### 禁止在数据库中存储文件（比如图片）这类大的二进制数据

#### 不要被数据库范式所束缚

#### 禁止在线上做数据库压力测试

#### 禁止从开发环境、测试环境直接连接生产环境数据库

### 数据库字段设计规范

#### 优先选择符合存储需要的最小的数据类型

#### 避免使用 TEXT、BLOB 数据类型，最常见的 TEXT 类型可以存储 64k 的数据

#### 避免使用 ENUM 类型

#### 尽可能把所有列定义为 NOT NULL

#### 一定不要用字符串存储日期

#### 同财务相关的金额类数据必须使用 decimal 类型

#### 单表不要包含过多字段

### 索引设计规范

#### 限制每张表上的索引数量，建议单张表索引不超过 5 个

#### 禁止使用全文索引

#### 禁止给表中的每一列都建立单独的索引

#### 每个 InnoDB 表必须有个主键

#### 常见索引列建议

#### 如何选择索引列的顺序

#### 避免建立冗余索引和重复索引（增加了查询优化器生成执行计划的时间）

#### 对于频繁的查询，优先考虑使用覆盖索引

#### 索引 SET 规范

### 数据库 SQL 开发规范

#### 尽量不在数据库做运算，复杂运算需移到业务应用里完成

#### 优化对性能影响较大的 SQL 语句

#### 充分利用表上已经存在的索引

#### 禁止使用 SELECT \* 必须使用 SELECT `<字段列表>` 查询

#### 禁止使用不含字段列表的 INSERT 语句

#### 建议使用预编译语句进行数据库操作

#### 避免数据类型的隐式转换

#### 避免使用子查询，可以把子查询优化为 join 操作

#### 避免使用 JOIN 关联太多的表

#### 减少同数据库的交互次数

#### 对应同一列进行 or 判断时，使用 in 代替 or

#### 禁止使用 order by rand() 进行随机排序

#### WHERE 从句中禁止对列进行函数转换和计算

#### 在明显不会有重复值时使用 UNION ALL 而不是 UNION

#### 拆分复杂的大 SQL 为多个小 SQL

#### 程序连接不同的数据库使用不同的账号，禁止跨库查询


#### 超 100 万行的批量写 (UPDATE、DELETE、INSERT) 操作，要分批多次进行操作

#### 对于大表使用 pt-online-schema-change 修改表结构

#### 禁止为程序使用的账号赋予 super 权限

#### 对于程序连接数据库账号，遵循权限最小原则

## [MySQL 索引详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/database/mysql/mysql-index.md)

### 索引介绍

### 索引的优缺点

### 索引底层数据结构选型

#### Hash 表

#### 二叉查找树（BST）

#### AVL 树

#### 红黑树

#### B 树& B+ 树

### 索引类型总结

### 主键索引（Primary Key）

### 二级索引

### 聚簇索引与非聚簇索引

#### 聚簇索引（聚集索引）

##### 聚簇索引介绍

##### 聚簇索引的优缺点

#### 非聚簇索引（非聚集索引）

##### 非聚簇索引介绍

##### 非聚簇索引的优缺点

##### 非聚簇索引一定回表查询吗（覆盖索引）？

### 覆盖索引和联合索引

#### 覆盖索引

#### 联合索引

#### 最左前缀匹配原则

### 索引下推

### 正确使用索引的一些建议

#### 选择合适的字段创建索引

#### 避免索引失效

#### 被频繁更新的字段应该慎重建立索引

#### 限制每张表上的索引数量

#### 尽可能的考虑建立联合索引而不是单列索引

#### 注意避免冗余索引

#### 字符串类型的字段使用前缀索引代替普通索引

#### 删除长期未使用的索引

#### 知道如何分析 SQL 语句是否走索引查询

### 数据结构延伸阅读

## [MySQL 索引失效场景总结](https://github.com/Snailclimb/JavaGuide/blob/main/docs/database/mysql/mysql-index-invalidation.md)

### SELECT \* 查询（成本权衡）

### 违背最左前缀原则

### 在索引列上进行计算、函数或类型转换

### LIKE 模糊查询以通配符开头

### OR 连接与 Index Merge

### IN / NOT IN 使用不当

### 隐式类型转换

### ORDER BY 排序优化陷阱



# Gradle

## [Gradle 核心概念总结](https://github.com/Snailclimb/JavaGuide/blob/main/docs/tools/gradle/gradle-core-concepts.md)

### Gradle 介绍

### Groovy 介绍

### Gradle 优势

### Gradle Wrapper 介绍

#### 生成 Gradle Wrapper

#### 更新 Gradle Wrapper

#### 自定义 Gradle Wrapper

### Gradle 任务

### Gradle 插件

### Gradle 构建生命周期

#### 初始化阶段

#### 配置阶段

#### 运行阶段


# Spring

## [Spring 常见知识点&面试题总结](https://github.com/Snailclimb/JavaGuide/blob/main/docs/system-design/framework/spring/spring-knowledge-and-questions-summary.md)

### Spring 基础

#### 什么是 Spring 框架?

#### Spring 包含的模块有哪些？

##### Core Container

##### AOP

##### Data Access/Integration

##### Spring Web

##### Messaging

##### Spring Test

#### ⭐️Spring,Spring MVC,Spring Boot 之间什么关系?

### Spring IoC

#### ⭐️什么是 IoC?

#### ⭐️IoC 解决了什么问题?

#### 什么是 Spring Bean？

#### 将一个类声明为 Bean 的注解有哪些?

#### @Component 和 @Bean 的区别是什么？

#### 注入 Bean 的注解有哪些？

#### ⭐️@Autowired 和 @Resource 的区别是什么？

#### 注入 Bean 的方式有哪些？

#### ⭐️构造函数注入还是 Setter 注入？

#### ⭐️Bean 的作用域有哪些?

#### ⭐️Bean 是线程安全的吗？

#### ⭐️Bean 的生命周期了解么?

### Spring AOP

#### ⭐️⭐️⭐️谈谈自己对于 AOP 的了解

#### ⭐️Spring AOP 和 AspectJ AOP 有什么区别？

#### ⭐️AOP 常见的通知类型有哪些？

#### 多个切面的执行顺序如何控制？

### Spring MVC

#### 说说自己对于 Spring MVC 了解?

#### Spring MVC 的核心组件有哪些？

#### ⭐️SpringMVC 工作原理了解吗?

#### 统一异常处理怎么做？

### Spring 框架中用到了哪些设计模式？

### ⭐️Spring 的循环依赖

#### Spring 循环依赖了解吗，怎么解决？

#### @Lazy 能解决循环依赖吗？

#### SpringBoot 允许循环依赖发生么？

### ⭐️Spring 事务

#### Spring 管理事务的方式有几种？

#### Spring 事务中哪几种事务传播行为?

#### Spring 事务中的隔离级别有哪几种?

#### @Transactional(rollbackFor = Exception.class)注解了解吗？

### Spring Data JPA

#### 如何使用 JPA 在数据库中非持久化一个字段？

#### JPA 的审计功能是做什么的？有什么用？

#### 实体之间的关联关系注解有哪些？

### Spring Security

#### 有哪些控制请求访问权限的方法？

#### hasRole 和 hasAuthority 有区别吗？

#### ⭐️如何对密码进行加密？

#### 如何优雅更换系统使用的加密算法？

### 参考

## [Spring/Spring Boot 常用注解总结](https://github.com/Snailclimb/JavaGuide/blob/main/docs/system-design/framework/spring/spring-common-annotations.md)

### Spring Boot 基础注解

### Spring Bean

#### ⭐️⭐️⭐️依赖注入（Dependency Injection, DI）

#### Bean 作用域

#### Bean 注册

### 配置

#### 声明配置类

#### 读取配置信息

#### 加载指定的配置文件

### MVC

#### HTTP 请求

##### GET 请求

##### POST 请求

##### PUT 请求

##### DELETE 请求

##### PATCH 请求

#### 参数绑定

##### 从 URL 路径中提取参数

##### 绑定查询参数

##### 绑定请求体中的 JSON 数据

### 数据校验

#### 一些常用的字段验证的注解

#### 验证请求体(RequestBody)

#### 验证请求参数(Path Variables 和 Request Parameters)

#### 创建表

#### 主键生成策略

#### 字段映射

#### 忽略字段

#### 大字段存储

#### 枚举类型映射

#### 审计功能

#### 修改和删除操作

#### 关联关系

### JSON 数据处理

#### 过滤 JSON 字段

#### 格式化 JSON 数据

#### 扁平化 JSON 对象

### 测试

## [SpringBoot 入门指南](https://github.com/Snailclimb/springboot-guide)

这是独立的 Spring Boot 入门项目，而不是 JavaGuide 中的一篇文章。以下列出该项目 README 中的知识点目录分类。

### 重要知识点

#### 基础

#### 进阶

#### 补充

## [IoC & AOP详解（快速搞懂）](https://github.com/Snailclimb/JavaGuide/blob/main/docs/system-design/framework/spring/ioc-and-aop.md)

### IoC （Inversion of control ）

#### ⭐️⭐️⭐️什么是 IoC?

#### IoC 解决了什么问题?

#### ⭐️⭐️⭐️IoC 和 DI 有区别吗？

### AOP（Aspect oriented programming）

#### ⭐️⭐️⭐️⭐️什么是 AOP？

#### AOP 为什么叫面向切面编程？

#### AOP 常见的通知类型有哪些？

#### AOP 解决了什么问题？

#### AOP 的应用场景有哪些？

#### AOP 实现方式有哪些？

### 参考

## [Spring 事务详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/system-design/framework/spring/spring-transaction.md)

### ⭐️⭐️什么是事务？

### 事务的特性（ACID）了解么?

### 详谈 Spring 对事务的支持

#### Spring 支持两种方式的事务管理

##### 编程式事务管理

##### 声明式事务管理

#### Spring 事务管理接口介绍

##### PlatformTransactionManager:事务管理接口

##### TransactionDefinition:事务属性

##### TransactionStatus:事务状态

#### 事务属性详解

##### 事务传播行为

##### 事务隔离级别

##### 事务超时属性

##### 事务只读属性

##### 事务回滚规则

#### @Transactional 注解使用详解

##### `@Transactional` 的作用范围

##### `@Transactional` 的常用配置参数

##### `@Transactional` 事务注解原理

##### Spring AOP 自调用问题

##### `@Transactional` 的使用注意事项总结

### 参考

## [Spring 中的设计模式详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/system-design/framework/spring/spring-design-patterns-summary.md)

### ⭐️⭐️⭐️控制反转(IoC)和依赖注入(DI)

### 工厂设计模式

### 单例设计模式

### 代理设计模式

#### 代理模式在 AOP 中的应用

#### Spring AOP 和 AspectJ AOP 有什么区别?

### 模板方法

### 观察者模式

#### Spring 事件驱动模型中的三种角色

##### 事件角色

##### 事件监听者角色

##### 事件发布者角色

#### Spring 的事件流程总结

### 适配器模式

#### Spring AOP 中的适配器模式

#### Spring MVC 中的适配器模式

### 装饰者模式



### 参考

## [SpringBoot 自动装配原理详解](https://github.com/Snailclimb/JavaGuide/blob/main/docs/system-design/framework/spring/spring-boot-auto-assembly-principles.md)

### 前言

### 什么是 SpringBoot 自动装配？

### SpringBoot 是如何实现自动装配的？

#### @EnableAutoConfiguration:实现自动装配的核心注解

#### AutoConfigurationImportSelector:加载自动装配类



