# 13 Java集合概念-上（集合体系与 Set 详解）

> 参考：JavaGuide《Java 集合常见面试题总结》。本文先梳理集合整体框架，再重点讲解 `Set` 家族。

## 1. 集合概述

### 1.1 Java 集合框架总览
Java 集合（Collection Framework）主要由两大接口派生：`Collection` 和 `Map`。

| 接口 | 特点 | 常见实现 |
| --- | --- | --- |
| `List` | 有序、可重复，可按下标访问 | `ArrayList`、`LinkedList`、`Vector`、`CopyOnWriteArrayList` |
| `Set` | 元素唯一（不可重复） | `HashSet`、`LinkedHashSet`、`TreeSet`、`CopyOnWriteArraySet` |
| `Queue` | 按特定规则排队，有序、可重复 | `PriorityQueue`、`ArrayDeque`、`LinkedList`、`ArrayBlockingQueue` |
| `Map` | 键值对，key 唯一，value 可重复 | `HashMap`、`LinkedHashMap`、`TreeMap`、`Hashtable`、`ConcurrentHashMap` |

```text
Iterable
  └── Collection
        ├── List   : ArrayList / LinkedList / Vector -> Stack
        ├── Set    : HashSet -> LinkedHashSet ; SortedSet -> NavigableSet -> TreeSet
        └── Queue  : PriorityQueue / Deque -> ArrayDeque / LinkedList

Map : HashMap -> LinkedHashMap ; SortedMap -> NavigableMap -> TreeMap ; Hashtable ; ConcurrentHashMap
```

特别注意：`Map` 并不继承 `Collection`，它是独立的接口体系。

### 1.2 为什么要使用集合
数组长度固定、类型单一，增删元素需要自己搬运数据；集合则：
- 容量可动态扩展。
- 提供丰富的增删改查、排序、去重等 API。
- 底层封装了多种数据结构（数组、链表、哈希表、红黑树、堆），按需选择。
- 配合泛型可在编译期检查类型。

### 1.3 List、Set、Queue、Map 的区别
| 对比项 | List | Set | Queue | Map |
| --- | --- | --- | --- | --- |
| 是否有序 | 有序（插入顺序） | 一般无序 | 有序（按队列规则） | 一般无序 |
| 是否允许重复 | 允许 | 不允许 | 允许 | key 不允许，value 允许 |
| 访问方式 | 下标 / 迭代 | 迭代 | 队头 / 队尾 | 通过 key |
| 典型场景 | 有序列表 | 去重、集合运算 | 排队、任务调度 | 缓存、映射、统计 |

### 1.4 如何选择集合
- 需要键值对 → `Map`；需要排序 → `TreeMap`；需要线程安全 → `ConcurrentHashMap`。
- 只存元素：
  - 需要**保证唯一** → `Set`；
  - 需要**保证顺序/可重复** → `List`；
  - 需要**排队/优先级** → `Queue`。

## 2. Set 概述

### 2.1 什么是 Set
`Set` 是 `Collection` 的子接口，**不允许存放重复元素**，没有提供按下标访问的方法。

```java
public interface Set<E> extends Collection<E> { ... }
```

Set 的主要接口体系：
- `Set`：基础接口。
- `SortedSet`：有序 Set，元素按顺序排列，提供 `first()`、`last()`、`headSet()`、`tailSet()`、`subSet()`。
- `NavigableSet`：在 `SortedSet` 基础上增加 `lower()`、`floor()`、`ceiling()`、`higher()` 等导航方法。
- `EnumSet`：专门用于枚举类型的高效 Set，底层为位向量。

### 2.2 Set 的常用方法
| 方法 | 作用 |
| --- | --- |
| `boolean add(E e)` | 添加元素，已存在则返回 `false` |
| `boolean remove(Object o)` | 删除元素 |
| `boolean contains(Object o)` | 是否包含 |
| `int size()` / `boolean isEmpty()` | 元素个数 / 是否为空 |
| `boolean addAll(Collection c)` | 并集 |
| `boolean retainAll(Collection c)` | 交集 |
| `boolean removeAll(Collection c)` | 差集 |
| `boolean containsAll(Collection c)` | 是否为子集 |
| `Iterator<E> iterator()` | 迭代器 |
| `void clear()` | 清空 |

集合运算示例：
```java
Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 3, 4));
Set<Integer> b = new HashSet<>(Arrays.asList(3, 4, 5, 6));

Set<Integer> union = new HashSet<>(a);
union.addAll(b);          // 并集 [1,2,3,4,5,6]

Set<Integer> inter = new HashSet<>(a);
inter.retainAll(b);       // 交集 [3,4]

Set<Integer> diff = new HashSet<>(a);
diff.removeAll(b);        // 差集 [1,2]
```

### 2.3 三大实现类对比
| 对比项 | HashSet | LinkedHashSet | TreeSet |
| --- | --- | --- | --- |
| 底层结构 | `HashMap`（数组 + 链表 + 红黑树） | `LinkedHashMap`（哈希表 + 双向链表） | `TreeMap`（红黑树） |
| 是否有序 | 无序 | 按插入顺序 | 按排序规则有序 |
| 是否允许 `null` | 允许一个 | 允许一个 | 不允许（自然排序下会 NPE） |
| 判重依据 | `hashCode()` + `equals()` | `hashCode()` + `equals()` | `compareTo()` / `compare()` 返回 0 |
| 增删查复杂度 | 平均 O(1) | 平均 O(1) | O(log n) |
| 线程安全 | 否 | 否 | 否 |
| 适用场景 | 单纯去重 | 去重且需保持顺序 | 去重且需排序/范围查询 |

## 3. HashSet 详解

### 3.1 底层实现
`HashSet` 内部持有一个 `HashMap`，元素作为 key，value 统一使用一个静态的占位对象：

```java
public class HashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, java.io.Serializable {
    private transient HashMap<E, Object> map;
    private static final Object PRESENT = new Object();

    public HashSet() {
        map = new HashMap<>();
    }

    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    public boolean remove(Object o) {
        return map.remove(o) == PRESENT;
    }

    public boolean contains(Object o) {
        return map.containsKey(o);
    }
}
```

要点：
- `add` 返回 `true` 表示 key 之前不存在（`put` 返回 `null`）；返回 `false` 表示元素已存在。
- 重复元素**不会替换**原有的 key（`HashMap` 只覆盖 value，key 保持旧引用）。
- 所有 `HashSet` 元素共享同一个 `PRESENT`，不额外占用空间。

### 3.2 构造方法与初始容量
```java
// 默认：初始容量 16，负载因子 0.75
public HashSet()
// 指定初始容量
public HashSet(int initialCapacity)
// 指定初始容量与负载因子
public HashSet(int initialCapacity, float loadFactor)
// 用另一个集合初始化：容量 = max(c.size()/.75f + 1, 16)
public HashSet(Collection<? extends E> c)
```

特别注意：若能预估元素个数，建议指定初始容量，避免多次扩容（`resize`）带来的性能损耗。常用估算：`(int) (expectedSize / 0.75f) + 1`。

### 3.3 add() 的判重流程
`HashSet` 的判重完全依赖 `HashMap.put()`：

1. 调用 `key.hashCode()`，再经扰动函数 `h ^ (h >>> 16)` 得到 hash。
2. 通过 `(n - 1) & hash` 定位数组下标。
3. 下标位置为空 → 直接插入。
4. 下标位置不为空：
   - 头节点 hash 相同且（`==` 或 `equals`）相同 → 视为重复；
   - 否则若是红黑树节点 → 按树的方式查找/插入；
   - 否则遍历链表，找到相同 key 则重复，到末尾则尾插新节点（链表长度 ≥ 8 且数组长度 ≥ 64 时转为红黑树）。
5. 元素个数超过 `threshold = capacity * loadFactor` 时触发扩容，容量翻倍。

```java
Set<String> set = new HashSet<>();
System.out.println(set.add("Java")); // true
System.out.println(set.add("Java")); // false
```

### 3.4 hashCode() 与 equals() 的约定
Java 对 `hashCode()` 和 `equals()` 有如下约定：
1. 两个对象 `equals` 相等，则 `hashCode` **必须**相等。
2. 两个对象 `hashCode` 相等，`equals` **不一定**相等（哈希冲突）。
3. 同一对象多次调用 `hashCode()`，结果应保持一致（前提是参与计算的字段未修改）。

**为什么重写 `equals()` 必须重写 `hashCode()`？** 示例：

```java
class User {
    String name;
    User(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(name, ((User) o).name);
    }
    // 没有重写 hashCode()
}

Set<User> set = new HashSet<>();
set.add(new User("Tom"));
set.add(new User("Tom"));
System.out.println(set.size()); // 通常是 2，而不是 1
```

两个 `User` 逻辑相等，但默认 `hashCode()` 与对象身份相关，hash 不同、落入不同桶，`equals` 根本不会被调用，于是被当成两个元素。正确写法：

```java
@Override
public int hashCode() {
    return Objects.hash(name);
}
```

特别注意：
- **不要在对象放入 `HashSet` 之后修改参与 `hashCode` 计算的字段**，否则该元素会“丢失”——`contains` 和 `remove` 都找不到它，造成内存泄漏。
- 优先使用不可变对象（如 `String`、`Integer`、`record`）作为元素。

### 3.5 HashSet 的遍历与无序性
`HashSet` 的遍历顺序取决于桶下标和链表/树结构，**不保证与插入顺序一致**，扩容后顺序还可能变化，因此不要依赖其顺序。

```java
Set<String> set = new HashSet<>(Arrays.asList("banana", "apple", "cherry"));
for (String s : set) {
    System.out.println(s);
}
```

### 3.6 HashSet 的优缺点
- 优点：增删查平均 O(1)，使用简单。
- 缺点：无序；非线程安全；元素的 `hashCode`/`equals` 实现不当会退化为 O(n) 甚至功能错误；额外存储 `HashMap.Node`，空间开销比 `ArrayList` 大。

## 4. LinkedHashSet 详解

### 4.1 底层实现
`LinkedHashSet` 继承自 `HashSet`，通过调用父类的特殊构造方法，使内部 `map` 使用 `LinkedHashMap`：

```java
// HashSet 中的包级私有构造方法
HashSet(int initialCapacity, float loadFactor, boolean dummy) {
    map = new LinkedHashMap<>(initialCapacity, loadFactor);
}

// LinkedHashSet 构造方法
public LinkedHashSet() {
    super(16, .75f, true);
}
```

`LinkedHashMap` 在 `HashMap` 的基础上给每个节点增加 `before` 和 `after` 指针，形成一条**双向链表**，记录元素的插入顺序。

### 4.2 特点
- 具有 `HashSet` 的 O(1) 查找性能，同时**迭代顺序等于插入顺序**。
- **重复插入同一元素不会改变其顺序**（顺序按首次插入计）。
- 迭代速度只与元素个数有关，与容量无关（遍历的是链表），在容量很大而元素较少时优于 `HashSet`。
- 比 `HashSet` 多维护两个指针，内存开销略大，插入略慢。

```java
Set<String> set = new LinkedHashSet<>();
set.add("c"); set.add("a"); set.add("b"); set.add("a");
System.out.println(set); // [c, a, b]
```

### 4.3 应用场景
- 去重的同时需要保持原有顺序（如对 `List` 去重）：

```java
List<Integer> list = Arrays.asList(3, 1, 3, 2, 1);
List<Integer> distinct = new ArrayList<>(new LinkedHashSet<>(list)); // [3, 1, 2]
```

- JDK 21 起新增 `SequencedSet` 接口，`LinkedHashSet` 实现了它，提供 `getFirst()`、`getLast()`、`reversed()` 等方法。

## 5. TreeSet 详解

### 5.1 底层实现
`TreeSet` 基于 `NavigableMap`（默认 `TreeMap`，即**红黑树**）实现，元素同样作为 key，value 为 `PRESENT`：

```java
public class TreeSet<E> extends AbstractSet<E> implements NavigableSet<E>, Cloneable, java.io.Serializable {
    private transient NavigableMap<E, Object> m;
    private static final Object PRESENT = new Object();

    public TreeSet() {
        this(new TreeMap<E, Object>());
    }

    public boolean add(E e) {
        return m.put(e, PRESENT) == null;
    }
}
```

红黑树是一种自平衡二叉查找树，插入、删除、查找时间复杂度均为 O(log n)，且中序遍历结果天然有序。

### 5.2 排序方式
**1）自然排序**：元素实现 `Comparable<T>` 接口。

```java
class Student implements Comparable<Student> {
    String name;
    int age;

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.age, o.age);
    }
}

Set<Student> set = new TreeSet<>();
```

**2）定制排序**：构造 `TreeSet` 时传入 `Comparator`。

```java
Set<Student> set = new TreeSet<>(
    Comparator.comparingInt((Student s) -> s.age).thenComparing(s -> s.name)
);
```

特别注意：
- 既没有实现 `Comparable` 又没有传入 `Comparator` 时，`add` 会抛出 `ClassCastException`。
- `TreeSet` **判断重复的依据是比较结果为 0**，而不是 `equals()`。如果 `compareTo` 只比较 `age`，两个年龄相同、姓名不同的学生会被认为是同一个元素而丢失一个。
- 应保证 `compareTo` 与 `equals` 的结果一致，否则会造成违反 `Set` 契约的怪异行为。
- 不允许 `null`：自然排序下 `null` 无法调用 `compareTo`，会抛出 `NullPointerException`。

### 5.3 NavigableSet 导航方法
```java
TreeSet<Integer> set = new TreeSet<>(Arrays.asList(10, 20, 30, 40, 50));

set.first();        // 10  最小
set.last();         // 50  最大
set.lower(30);      // 20  严格小于 30 的最大元素
set.floor(30);      // 30  小于等于 30 的最大元素
set.higher(30);     // 40  严格大于 30 的最小元素
set.ceiling(35);    // 40  大于等于 35 的最小元素
set.headSet(30);    // [10, 20]          小于 30
set.tailSet(30);    // [30, 40, 50]      大于等于 30
set.subSet(20, 40); // [20, 30]          [20, 40)
set.descendingSet();// [50, 40, 30, 20, 10]
set.pollFirst();    // 取出并删除最小元素
```

### 5.4 适用场景
排行榜、按范围查询（如区间、时间窗口）、需要始终保持有序的去重集合。

## 6. 其他 Set 实现

### 6.1 EnumSet
- 只能存放同一种枚举类型的元素。
- 底层使用**位向量**（元素 ≤ 64 个用 `RegularEnumSet`，一个 `long`；否则用 `JumboEnumSet`），速度极快、占用极小。
- 迭代顺序为枚举的声明顺序，不允许 `null`，非线程安全。

```java
EnumSet<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
EnumSet<DayOfWeek> workdays = EnumSet.complementOf(weekend);
```

### 6.2 不可变 Set（JDK 9+）
```java
Set<String> s = Set.of("a", "b", "c");
```
- 不允许 `null`，也不允许重复元素（否则 `IllegalArgumentException`）。
- 修改操作抛出 `UnsupportedOperationException`。
- 迭代顺序**不固定**，每次 JVM 运行可能不同。

### 6.3 Collections 工具方法
```java
Collections.unmodifiableSet(set);   // 只读视图（原集合变化会反映到视图）
Collections.synchronizedSet(set);   // 线程安全包装
Collections.emptySet();             // 空集合
Collections.singleton(e);           // 单元素不可变集合
```

## 7. Set 的线程安全问题

### 7.1 为什么不安全
`HashSet`、`LinkedHashSet`、`TreeSet` 的底层都是非线程安全的 Map：
- 多线程同时 `add` 可能导致数据丢失、`size` 不准确。
- JDK 7 及以前的 `HashMap` 在并发扩容时可能形成环形链表导致死循环；JDK 8 虽然改为尾插法避免了死循环，但仍然存在数据覆盖等问题。
- 遍历时被其他线程修改会抛出 `ConcurrentModificationException`。

### 7.2 线程安全的方案
| 方案 | 原理 | 特点 |
| --- | --- | --- |
| `Collections.synchronizedSet(set)` | 所有方法加 `synchronized` | 简单，但粒度粗、并发性能一般；遍历时需手动同步 |
| `CopyOnWriteArraySet` | 内部基于 `CopyOnWriteArrayList`，写时复制数组 | 读无锁；适合**读多写少**、元素少；写入开销大，有弱一致性 |
| `ConcurrentHashMap.newKeySet()` | 基于 `ConcurrentHashMap` | 高并发首选，无序 |
| `ConcurrentSkipListSet` | 基于跳表 | 线程安全且**有序**，对应并发版 `TreeSet` |

```java
Set<String> safeSet = ConcurrentHashMap.newKeySet();
Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());

// synchronizedSet 遍历时需要手动加锁
synchronized (syncSet) {
    for (String s : syncSet) {
        System.out.println(s);
    }
}
```

## 8. 快速失败（fail-fast）与迭代器

### 8.1 ConcurrentModificationException
使用 for-each 遍历集合时修改集合结构，会触发 fail-fast：

```java
Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4));
for (Integer i : set) {
    if (i == 2) {
        set.remove(i); // 抛出 ConcurrentModificationException
    }
}
```

原理：迭代器创建时记录 `expectedModCount = modCount`，每次 `next()` 都会检查两者是否一致，不一致则抛出异常。

### 8.2 正确的删除方式
```java
// 方式一：Iterator.remove()
Iterator<Integer> it = set.iterator();
while (it.hasNext()) {
    if (it.next() == 2) {
        it.remove();
    }
}

// 方式二：removeIf（JDK 8+，推荐）
set.removeIf(i -> i == 2);
```

特别注意：fail-fast 只是“尽力而为”的检测，不能作为并发安全的保证。

## 9. 常见面试题

### 9.1 HashSet、LinkedHashSet、TreeSet 的区别和共同点
- 共同点：都保证元素唯一，都**不是线程安全**的。
- 区别：底层结构、有序性、`null` 支持、判重依据、性能与应用场景不同（见 2.3 对比表）。

### 9.2 HashSet 如何检查重复？
先比较 `hashCode` 定位桶，再用 `==` / `equals()` 确认。`hashCode` 的作用是缩小比较范围，减少 `equals()` 的调用次数。

### 9.3 `==` 与 `equals()` 的区别
- `==`：基本类型比较值，引用类型比较地址。
- `equals()`：`Object` 默认同 `==`；`String`、`Integer` 等重写后比较内容。

### 9.4 HashSet 为什么用 HashMap 实现，而不是直接实现？
复用 `HashMap` 成熟的哈希表、扩容和红黑树优化逻辑；`Set` 只需要 key 的唯一性，因此用一个固定的 value 占位。

### 9.5 HashSet 与 HashMap 的区别
| 对比项 | HashSet | HashMap |
| --- | --- | --- |
| 实现接口 | `Set` | `Map` |
| 存储内容 | 对象（作为 key） | 键值对 |
| 添加方法 | `add()` | `put()` |
| 计算 hash | 使用成员对象 | 使用 key |
| 速度 | 略慢（多一层封装） | 略快 |

### 9.6 Comparable 与 Comparator 的区别
| 对比项 | Comparable | Comparator |
| --- | --- | --- |
| 所在包 | `java.lang` | `java.util` |
| 方法 | `compareTo(T o)` | `compare(T o1, T o2)` |
| 定义位置 | 类内部（侵入式） | 类外部/匿名类/Lambda |
| 灵活性 | 只能定义一种默认排序 | 可定义多种排序规则 |

### 9.7 如何对 List 去重？
```java
// 无需保持顺序
List<T> r1 = new ArrayList<>(new HashSet<>(list));
// 保持顺序
List<T> r2 = new ArrayList<>(new LinkedHashSet<>(list));
// Stream
List<T> r3 = list.stream().distinct().collect(Collectors.toList());
```

### 9.8 为什么判空使用 `isEmpty()` 而不是 `size() == 0`？
可读性更好；并且部分集合（如 `ConcurrentHashMap`）计算 `size()` 的成本较高，而 `isEmpty()` 通常是 O(1)。

### 9.9 集合与数组之间如何转换？
```java
// 集合 -> 数组：推荐传入类型数组
String[] arr = set.toArray(new String[0]);

// 数组 -> 集合
Set<String> s1 = new HashSet<>(Arrays.asList(arr));
Set<String> s2 = Arrays.stream(arr).collect(Collectors.toSet());
```

特别注意：`Arrays.asList()` 返回的是 `Arrays` 内部类 `ArrayList`，长度固定，调用 `add`/`remove` 会抛出 `UnsupportedOperationException`；且对基本类型数组使用时，整个数组会被当作一个元素。

### 9.10 HashSet 能存 null 吗？TreeSet 呢？
`HashSet` 和 `LinkedHashSet` 可以存一个 `null`（`HashMap` 允许 key 为 `null`，hash 为 0）；`TreeSet` 不允许（自然排序下抛 NPE）。

### 9.11 HashSet 的扩容机制
默认初始容量 16、负载因子 0.75，当元素数超过 `容量 × 0.75` 时扩容为原来的 2 倍，并重新分布元素（JDK 8 通过 `hash & oldCap` 判断元素留在原位置还是移动 `oldCap`）。容量始终保持为 2 的幂，以便用位运算 `(n - 1) & hash` 代替取模。

## 10. 小结
| 需求 | 推荐 |
| --- | --- |
| 仅去重，追求性能 | `HashSet` |
| 去重并保持插入顺序 | `LinkedHashSet` |
| 去重并排序、范围查询 | `TreeSet` |
| 枚举类型集合 | `EnumSet` |
| 并发读多写少 | `CopyOnWriteArraySet` |
| 并发高吞吐 | `ConcurrentHashMap.newKeySet()` |
| 并发且有序 | `ConcurrentSkipListSet` |
