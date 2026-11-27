# 14 Java集合概念-下（List、Map、Queue 详解）

> 参考：JavaGuide《Java 集合常见面试题总结》。上篇讲了集合体系与 `Set`，本文讲解 `List`、`Map`、`Queue`。

## 1. List 详解

### 1.1 ArrayList、LinkedList、Vector 对比
| 对比项 | ArrayList | LinkedList | Vector |
| --- | --- | --- | --- |
| 底层结构 | `Object[]` 动态数组 | 双向链表 | `Object[]` 动态数组 |
| 随机访问 | O(1) | O(n) | O(1) |
| 头部插入/删除 | O(n) | O(1) | O(n) |
| 尾部插入 | 均摊 O(1) | O(1) | 均摊 O(1) |
| 中间插入/删除 | O(n)（需搬移） | 定位 O(n)，修改指针 O(1) | O(n) |
| 线程安全 | 否 | 否 | 是（方法加 `synchronized`，已不推荐） |
| 内存占用 | 数组预留空间 | 每个节点额外存前后指针 | 同 ArrayList |
| 实现 `RandomAccess` | 是 | 否 | 是 |

特别注意：
- `LinkedList` 并不一定比 `ArrayList` 插入更快——定位节点本身就是 O(n)，且链表缓存局部性差。实际开发中绝大多数场景首选 `ArrayList`。
- `LinkedList` 同时实现了 `Deque`，可当队列/栈使用，但更推荐 `ArrayDeque`。
- `Vector` 和 `Stack` 是遗留类，线程安全场景请用 `Collections.synchronizedList` 或 `CopyOnWriteArrayList`。

### 1.2 ArrayList 的扩容机制
```java
// JDK 8
private static final int DEFAULT_CAPACITY = 10;
private void grow(int minCapacity) {
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1); // 1.5 倍
    if (newCapacity < minCapacity) newCapacity = minCapacity;
    elementData = Arrays.copyOf(elementData, newCapacity);
}
```
- 无参构造不会立即分配数组，而是指向空数组，**第一次 `add` 时才扩容到 10**。
- 之后每次容量不足扩容为原来的 **1.5 倍**，底层使用 `Arrays.copyOf` 复制数组。
- 已知元素数量时，指定初始容量或使用 `ensureCapacity()` 可减少扩容次数。

### 1.3 RandomAccess 接口
`RandomAccess` 是标记接口，表示支持快速随机访问。`Collections.binarySearch` 会据此选择“下标二分”还是“迭代器二分”。遍历 `ArrayList` 用 for 循环更快，遍历 `LinkedList` 应使用迭代器/for-each，不要用下标。

### 1.4 常见陷阱
```java
// 1. Arrays.asList 返回固定长度列表
List<String> l = Arrays.asList("a", "b");
l.add("c"); // UnsupportedOperationException

// 2. subList 是原列表的视图，修改会互相影响
List<Integer> sub = list.subList(0, 2);

// 3. 遍历时删除
list.removeIf(x -> x == 2);              // 推荐
Iterator<Integer> it = list.iterator();  // 或使用 Iterator.remove()
```

- `List.of()`（JDK 9+）返回不可变列表，不允许 `null`。
- `ArrayList` 允许 `null` 和重复元素。
- `remove(int index)` 与 `remove(Object o)` 对 `List<Integer>` 容易混淆：`list.remove(1)` 删的是下标 1。

### 1.5 CopyOnWriteArrayList
- 写操作加锁并**复制整个数组**，在新数组上修改后替换引用；读操作无锁。
- 读多写少场景适用；缺点是写开销大、占内存、读到的是**弱一致性**快照（不保证实时）。
- 迭代器不会抛 `ConcurrentModificationException`，但迭代器不支持 `remove`。

## 2. Map 详解

### 2.1 常见 Map 对比
| 对比项 | HashMap | LinkedHashMap | TreeMap | Hashtable | ConcurrentHashMap |
| --- | --- | --- | --- | --- | --- |
| 底层结构 | 数组 + 链表 + 红黑树 | HashMap + 双向链表 | 红黑树 | 数组 + 链表 | 数组 + 链表 + 红黑树 |
| 是否有序 | 无序 | 插入/访问顺序 | key 排序 | 无序 | 无序 |
| key 允许 `null` | 一个 | 一个 | 否 | 否 | 否 |
| value 允许 `null` | 是 | 是 | 是 | 否 | 否 |
| 线程安全 | 否 | 否 | 否 | 是（整表 `synchronized`） | 是（CAS + `synchronized` 锁桶） |
| 查找复杂度 | 平均 O(1) | 平均 O(1) | O(log n) | O(1) | 平均 O(1) |

### 2.2 HashMap 底层结构（JDK 8）
- 数组（`Node<K,V>[] table`）+ 链表 + 红黑树。
- 链表长度 ≥ 8 且数组长度 ≥ 64 时链表转红黑树；若数组长度 < 64，则优先**扩容**而不是转树。
- 红黑树节点数 ≤ 6 时退化回链表。
- 默认初始容量 16，负载因子 0.75，容量始终是 2 的幂。

### 2.3 put 流程
1. 计算 hash：`(h = key.hashCode()) ^ (h >>> 16)`（扰动函数，让高位参与运算，减少冲突）。
2. `table` 为空则 `resize()` 初始化。
3. 下标 `i = (n - 1) & hash`，该位置为空则直接放入。
4. 不为空：key 相同（hash 相等且 `==`/`equals`）则覆盖 value；若是树节点则走红黑树插入；否则遍历链表，尾插，必要时转树。
5. `++size > threshold` 则 `resize()` 扩容。

### 2.4 为什么容量是 2 的幂
- 用 `(n - 1) & hash` 代替取模 `hash % n`，位运算更快。
- 扩容时元素新位置只有两种可能：**原下标**或**原下标 + 旧容量**，通过 `hash & oldCap` 是否为 0 判断，无需重新计算 hash。
- `new HashMap<>(10)` 会通过 `tableSizeFor` 调整为 16。

### 2.5 JDK 7 与 JDK 8 的 HashMap 区别
| 对比项 | JDK 7 | JDK 8 |
| --- | --- | --- |
| 结构 | 数组 + 链表 | 数组 + 链表 + 红黑树 |
| 插入方式 | 头插法 | 尾插法 |
| hash 扰动 | 4 次位运算 + 5 次异或 | 1 次位运算 + 1 次异或 |
| 并发扩容 | 可能形成环形链表，死循环 | 不会死循环，但仍有数据覆盖 |

### 2.6 HashMap 的线程不安全表现
- 并发 `put` 同一个桶时，后写入的值可能覆盖先写入的值，造成数据丢失。
- 并发 `put` 与 `resize` 可能导致数据丢失或 `size` 不准。
- JDK 7 头插法并发扩容可能出现环形链表。
- 线程安全请使用 `ConcurrentHashMap`。

### 2.7 HashMap 与 Hashtable 的区别
- `Hashtable` 所有方法加 `synchronized`，并发性能差，已淘汰。
- `Hashtable` 不允许 `null` key / value，`HashMap` 允许一个 `null` key。
- 默认容量 `Hashtable` 为 11，扩容为 `2n + 1`；`HashMap` 为 16，扩容为 2 倍。

### 2.8 LinkedHashMap 与 LRU 缓存
`LinkedHashMap` 支持**访问顺序**（`accessOrder = true`），配合 `removeEldestEntry` 可轻松实现 LRU：

```java
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
```

### 2.9 TreeMap
- 基于红黑树，key 按自然排序或 `Comparator` 排序，提供 `firstKey()`、`floorKey()`、`headMap()`、`subMap()` 等导航方法。
- key 不允许 `null`；判重依据与 `TreeSet` 相同，是比较结果为 0。

### 2.10 ConcurrentHashMap
| 对比项 | JDK 7 | JDK 8+ |
| --- | --- | --- |
| 结构 | `Segment` 数组 + `HashEntry` 数组 + 链表 | `Node` 数组 + 链表 + 红黑树 |
| 锁粒度 | 分段锁（`ReentrantLock` 锁 Segment） | 锁单个桶头节点（`synchronized`）+ CAS |
| 并发度 | Segment 个数（默认 16） | 随桶数量增长 |

- 桶为空时用 **CAS** 插入；桶不为空时对**头节点加 `synchronized`** 后链表/红黑树插入。
- 不允许 `null` key / value：并发下 `get` 返回 `null` 无法区分“不存在”和“值为 null”。
- 复合操作（先检查再写入）不是原子的，应使用 `putIfAbsent`、`computeIfAbsent`、`merge` 等原子方法。
- 扩容时支持多线程**协助迁移**（`transfer`），迁移完的桶用 `ForwardingNode` 标记。
- `size()` 通过 `baseCount` + `CounterCell[]` 累加，是近似值。

```java
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
map.merge("a", 1, Integer::sum);          // 计数
map.computeIfAbsent("b", k -> 0);
```

### 2.11 Map 的遍历方式
```java
// 推荐：entrySet，一次取 key 和 value
for (Map.Entry<String, Integer> e : map.entrySet()) {
    System.out.println(e.getKey() + "=" + e.getValue());
}

// JDK 8+
map.forEach((k, v) -> System.out.println(k + "=" + v));

// 只需要 key / value
map.keySet();
map.values();
```
避免“先遍历 `keySet` 再 `get(key)`”，会多一次查找。

### 2.12 Map 的常用方法（JDK 8+）
| 方法 | 作用 |
| --- | --- |
| `getOrDefault(k, d)` | key 不存在返回默认值 |
| `putIfAbsent(k, v)` | key 不存在才放入 |
| `computeIfAbsent(k, f)` | key 不存在时计算并放入 |
| `computeIfPresent(k, f)` | key 存在时重新计算 |
| `merge(k, v, f)` | 合并（常用于计数、分组） |

### 2.13 hashCode 冲突与 key 的选择
- key 应使用**不可变对象**（如 `String`、`Integer`），并正确重写 `hashCode()` 与 `equals()`。
- 可变对象作 key，修改后会找不到对应 value。

## 3. Queue 与 Deque 详解

### 3.1 Queue 与 Deque 的区别
- `Queue`：单端队列，先进先出（FIFO），只能一端入、另一端出。
- `Deque`：双端队列，两端都可入队出队，既可作队列也可作栈。

| Queue 方法 | 失败抛异常 | 失败返回特殊值 |
| --- | --- | --- |
| 入队 | `add(e)` | `offer(e)` |
| 出队 | `remove()` | `poll()` |
| 查看队首 | `element()` | `peek()` |

### 3.2 ArrayDeque 与 LinkedList
| 对比项 | ArrayDeque | LinkedList |
| --- | --- | --- |
| 底层结构 | 循环数组 | 双向链表 |
| 允许 `null` | 否 | 是 |
| 性能 | 通常更快、内存更省 | 节点开销大 |
| 扩容 | 容量不足时扩容 | 无需扩容 |

作栈或队列优先使用 `ArrayDeque`。不要使用遗留的 `Stack` 类。

### 3.3 PriorityQueue
- 基于**二叉小顶堆**（默认）实现，使用数组存储，出队总是返回最小（或最大）元素。
- 入队、出队 O(log n)，查看队首 O(1)。
- 通过 `Comparable` 或 `Comparator` 排序；不允许 `null` 和不可比较对象；**非线程安全**。
- 遍历顺序**不保证有序**，只有队首保证最值。

```java
Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
maxHeap.offer(3); maxHeap.offer(1); maxHeap.offer(5);
maxHeap.poll(); // 5
```

### 3.4 BlockingQueue 阻塞队列
线程安全的队列，队列为空时获取元素的线程阻塞，队列满时插入元素的线程阻塞，常用于生产者-消费者模型。

| 实现 | 特点 |
| --- | --- |
| `ArrayBlockingQueue` | 数组实现，**有界**，一把 `ReentrantLock` |
| `LinkedBlockingQueue` | 链表实现，默认容量 `Integer.MAX_VALUE`（慎用无界），读写两把锁 |
| `PriorityBlockingQueue` | 无界优先级阻塞队列，基于堆 |
| `SynchronousQueue` | 不存储元素，一次 `put` 必须等待一次 `take` |
| `DelayQueue` | 延迟队列，元素到期才能取出 |

| 方法 | 作用 |
| --- | --- |
| `put(e)` / `take()` | 阻塞式入队 / 出队 |
| `offer(e, timeout, unit)` / `poll(timeout, unit)` | 超时等待 |

## 4. 集合通用知识

### 4.1 fail-fast 与 fail-safe
| 对比项 | fail-fast | fail-safe |
| --- | --- | --- |
| 代表 | `ArrayList`、`HashMap`、`HashSet` | `CopyOnWriteArrayList`、`ConcurrentHashMap` |
| 机制 | 迭代时检测 `modCount`，不一致抛 `ConcurrentModificationException` | 遍历副本或弱一致迭代器 |
| 缺点 | 只能尽力检测 | 可能读到旧数据，副本有内存开销 |

### 4.2 Iterator 与 ListIterator
- `Iterator`：单向遍历，支持 `hasNext()`、`next()`、`remove()`。
- `ListIterator`：仅 `List` 支持，双向遍历，支持 `add`、`set`、`hasPrevious()`、`previous()`。

### 4.3 Collections 与 Arrays 工具类
| 方法 | 作用 |
| --- | --- |
| `Collections.sort / reverse / shuffle` | 排序 / 反转 / 打乱 |
| `Collections.max / min` | 最大 / 最小 |
| `Collections.binarySearch` | 二分查找（需先排序） |
| `Collections.unmodifiableXxx` | 只读视图 |
| `Collections.synchronizedXxx` | 同步包装 |
| `Arrays.sort` | 基本类型：双轴快排；对象：TimSort |
| `Arrays.asList` | 数组转固定长度列表 |

## 5. 常见面试题

### 5.1 ArrayList 与 LinkedList 的区别
见 1.1 对比表；核心是数组 vs 链表，随机访问与插入删除的取舍，以及内存与缓存局部性。

### 5.2 ArrayList 与 Array 的区别
- `Array` 长度固定，可存基本类型；`ArrayList` 动态扩容，只能存对象（基本类型需装箱），提供丰富 API。

### 5.3 HashMap 为什么线程不安全？
见 2.6。

### 5.4 HashMap 的 get 流程
计算 hash → 定位桶 → 头节点匹配则返回 → 树节点走红黑树查找，链表则遍历，用 `hash` + `equals` 比较 key。

### 5.5 ConcurrentHashMap 为什么 key、value 不能为 null？
`get()` 返回 `null` 时无法判断是不存在还是 value 为 `null`，而并发下不能再用 `containsKey` 二次检查（两次调用之间可能被修改）。

### 5.6 ConcurrentHashMap 与 Hashtable 的区别
- 底层：JDK 8 `ConcurrentHashMap` 数组 + 链表 + 红黑树；`Hashtable` 数组 + 链表。
- 线程安全：前者 CAS + 锁桶，后者整表 `synchronized`，并发性能差距大。

### 5.7 Comparable 与 Comparator 的区别
见上篇 9.6。

### 5.8 PriorityQueue 如何实现堆排序思想
插入时元素加到数组末尾并**上浮**（siftUp），删除堆顶时将末尾元素放到堆顶并**下沉**（siftDown）。

## 6. 小结
| 需求 | 推荐 |
| --- | --- |
| 随机访问多、尾部增删 | `ArrayList` |
| 并发读多写少的列表 | `CopyOnWriteArrayList` |
| 键值映射 | `HashMap` |
| 键值映射 + 保持顺序 / LRU | `LinkedHashMap` |
| 键有序 / 范围查询 | `TreeMap` |
| 并发键值映射 | `ConcurrentHashMap` |
| 栈 / 双端队列 | `ArrayDeque` |
| 优先级 / Top K | `PriorityQueue` |
| 生产者-消费者 | `ArrayBlockingQueue` / `LinkedBlockingQueue` |
