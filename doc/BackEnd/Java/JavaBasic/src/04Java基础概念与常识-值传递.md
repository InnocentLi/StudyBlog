# 04 Java基础概念与常识-值传递

Java 中的方法参数传递是非常经典的面试题。理解这个问题，首先需要掌握两个概念：

1. 形参（Parameter）与实参（Argument）
2. 值传递（Pass by Value）与引用传递（Pass by Reference）

最核心的结论是：

**Java 只有值传递。基本类型传递的是数据值的副本，引用类型传递的是引用值的副本，而不是对象本身的副本。**

## 1. 形参与实参
| 对比 | 形参（Parameter） | 实参（Argument） |
| --- | --- | --- |
| 定义 | 方法定义时声明的参数 | 调用方法时传入的参数 |
| 出现位置 | 方法定义中 | 方法调用处 |
| 作用 | 接收调用方传入的值 | 为方法提供实际数据 |
| 是否需要实际值 | 定义时不需要 | 调用时必须能求出确定的值 |

代码示例：
```java
public class Main {
    public static void main(String[] args) {
        String hello = "Hello!";
        sayHello(hello); // hello 是实参
    }

    public static void sayHello(String str) {
        // str 是形参
        System.out.println(str);
    }
}
```

输出：
```text
Hello!
```

解释：
- `hello`：实参，调用方法时传入的变量。
- `str`：形参，方法定义时声明的变量。
- 调用 `sayHello(hello)` 时，Java 将 `hello` 保存的引用值复制给 `str`。

注意：传递的是变量中的值，而不是将两个变量变成同一个变量。

## 2. 值传递与引用传递
| 对比 | 值传递（Pass by Value） | 引用传递（Pass by Reference） |
| --- | --- | --- |
| 传递内容 | 实参值的副本 | 使形参引用调用方的原变量 |
| 形参与实参 | 两个独立变量 | 形参相当于调用方变量的别名 |
| 修改形参本身 | 不影响实参变量 | 可以影响实参变量 |
| Java 是否支持 | ✅ 支持 | ❌ 不支持 |
| C++ 是否支持 | ✅ 支持 | ✅ 支持 |

### 2.1 Java 的两种参数类型

两种类型虽然复制的内容不同，但本质都是值传递。

## 3. 为什么 Java 只有值传递？
### 3.1 案例 1：传递基本数据类型
```java
public class Main {

    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 20;

        swap(num1, num2);

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
```

输出：
```text
a = 20
b = 10
num1 = 10
num2 = 20
```

原因：
- `a` 复制了 `num1` 的值 `10`。
- `b` 复制了 `num2` 的值 `20`。
- `swap()` 只交换了副本，不会改变外部变量。

因此，基本类型作为参数时，方法内部修改形参不会影响实参。

### 3.2 案例 2：传递引用类型（修改对象内容）
```java
public class Main {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        System.out.println(arr[0]);

        change(arr);

        System.out.println(arr[0]);
    }

    public static void change(int[] array) {
        array[0] = 0;
    }
}
```

输出：
```text
1
0
```

原因：
- `arr` 是引用变量，指向一个数组对象。
- 调用 `change(arr)` 时，复制的是 `arr` 保存的引用值。
- `arr` 和 `array` 因此指向同一个数组。
- 通过 `array[0] = 0` 修改数组内容，外部也能看到变化。

所以，引用类型参数可以修改其指向的可变对象的内部状态，但这不代表 Java 使用引用传递。

### 3.3 案例 3：传递引用类型（交换对象引用）
这是证明 Java 只有值传递的关键案例。
```java
public class Main {

    static class Person {
        String name;

        Person(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Person xiaoZhang = new Person("小张");
        Person xiaoLi = new Person("小李");

        swap(xiaoZhang, xiaoLi);

        System.out.println("外部 xiaoZhang: " + xiaoZhang.name);
        System.out.println("外部 xiaoLi: " + xiaoLi.name);
    }

    public static void swap(Person person1, Person person2) {
        Person temp = person1;
        person1 = person2;
        person2 = temp;

        System.out.println("内部 person1: " + person1.name);
        System.out.println("内部 person2: " + person2.name);
    }
}
```

输出：
```text
内部 person1: 小李
内部 person2: 小张
外部 xiaoZhang: 小张
外部 xiaoLi: 小李
```

原因：

调用 `swap()` 时：
- `person1` 复制了 `xiaoZhang` 保存的引用。
- `person2` 复制了 `xiaoLi` 保存的引用。
- 方法内部交换的是这两个引用副本。
- 外部变量 `xiaoZhang`、`xiaoLi` 没有改变。

因此，即使参数是对象，Java 也不能通过交换形参引用来交换调用方的两个变量。

### 3.4 案例 2 和案例 3 的区别
| 操作 | 能否影响外部 | 原因 |
| --- | --- | --- |
| `array[0] = 0` | ✅ 可以 | 修改同一个数组对象的内容 |
| `person1.name = "新名字"` | ✅ 可以 | 修改同一个 `Person` 对象的字段 |
| `person1 = new Person("新对象")` | ❌ 不可以 | 仅改变局部引用变量的指向 |
| `swap(person1, person2)` | ❌ 不可以交换外部引用 | 只交换两个局部引用副本 |

需要特别记住：

修改对象内容 ≠ 修改引用变量本身。

## 4. C++ 的引用传递是什么？
Java 不支持引用传递，但 C++ 可以使用 `&` 声明引用参数。
```cpp
#include <iostream>

void incr(int& num) {
    std::cout << "incr before: " << num << "\n";
    num++;
    std::cout << "incr after: " << num << "\n";
}

int main() {
    int age = 10;
    std::cout << "invoke before: " << age << "\n";
    incr(age);
    std::cout << "invoke after: " << age << "\n";
}
```

输出：
```text
invoke before: 10
incr before: 10
incr after: 11
invoke after: 11
```

这里的 `int& num` 表示 `num` 是调用方变量的引用，相当于它的别名。

因此：
```cpp
num++;
```

可以直接修改外部的 `age`。

如果将 `int& num` 改为 `int num`，就会变成值传递，外部的 `age` 不会变化。

## 5. 为什么 Java 不支持引用传递？
Java 没有提供 C++ `T&` 这样的引用参数机制，这与 Java 强调语言简洁、降低复杂性和限制直接内存操作的设计方向相符。

但要注意：

不能认为 Java 只有值传递，所以方法就不能修改调用方的数据。

Java 方法仍然可以修改共享的可变对象。例如：
```java
public static void change(Person p) {
    p.name = "小王"; // 可以影响外部对象
}
```

因此，值传递并不意味着对象状态天然安全，也不能阻止方法产生副作用。

## 6. 面试总结
面试问题：为什么说 Java 中只有值传递？

可以这样回答：

Java 中的方法参数传递方式只有值传递。

对于基本数据类型，传递的是基本类型值的副本，所以方法内部修改形参不会影响实参。

对于引用数据类型，传递的是引用值的副本，因此形参和实参最初指向同一个对象。

方法内部可以通过形参修改该对象的内部状态，但如果将形参重新赋值为另一个对象，则不会影响外部实参变量。

所以 Java 无论传递基本类型还是引用类型，本质上都是值传递。

最值得记住的三句话：
1. 基本类型：复制数据值，修改形参不影响实参。
2. 引用类型：复制引用值，可以通过形参修改共享对象的内容。
3. 重新赋值：无论基本类型还是引用类型，对形参重新赋值都不会改变调用方的变量。