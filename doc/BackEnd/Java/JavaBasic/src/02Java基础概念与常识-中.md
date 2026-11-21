# 02 Java基础概念与常识-面向对象基础（OOP）

本章主要介绍 Java 面向对象的基本概念，包括面向过程与面向对象、对象与引用、构造方法、封装、继承、多态，以及接口与抽象类。

## 1. 面向对象和面向过程的区别
面向过程（POP）和面向对象（OOP）是两种不同的编程范式，核心区别在于程序如何组织数据和行为。
### 1.1 什么是面向过程（POP）？
面向过程（Procedural-Oriented Programming）主要关注 如何一步一步完成任务。

通常把程序拆成多个函数或方法，按照一定的执行顺序完成业务逻辑。

例如：计算圆的面积和周长。

```java
public class Main {
    public static void main(String[] args) {

        double radius = 3.0;

        // 计算面积
        double area = Math.PI * radius * radius;

        // 计算周长
        double perimeter = 2 * Math.PI * radius;

        System.out.println("面积：" + area);
        System.out.println("周长：" + perimeter);
    }
}
```


特点：
- 重点关注具体的处理步骤。
- 数据与处理逻辑通常分开组织。
- 对于简单任务，代码直观、实现成本低。
### 1.2 什么是面向对象（OOP）？
面向对象（Object-Oriented Programming）主要关注 有哪些对象，以及对象具有哪些属性和行为。

例如，我们将圆抽象为 Circle 类：
```java
public class Circle {

    // 属性：半径
    private double radius;

    // 构造方法
    public Circle(double radius) {
        this.radius = radius;
    }

    // 行为：计算面积
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // 行为：计算周长
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public static void main(String[] args) {

        // 创建 Circle 对象
        Circle circle = new Circle(3.0);

        // 通过对象调用方法
        System.out.println(circle.getArea());
        System.out.println(circle.getPerimeter());
    }
}
```


这里：
- Circle：类（Class），定义圆的数据和行为。
- radius：属性（Field），表示圆的半径。
- getArea()：方法（Method），表示计算面积的行为。
- circle：引用变量（Reference Variable），指向创建的对象。
- new Circle(3.0)：创建对象实例。
### 1.3 POP 与 OOP 对比
| 对比项 | 面向过程（POP） | 面向对象（OOP） |
| --- | --- | --- |
| 核心思想 | 关注处理步骤 | 关注对象及其职责 |
| 组织方式 | 函数、方法、过程 | 类、对象、接口 |
| 数据与行为 | 通常分开组织 | 可以封装在同一对象中 |
| 代码复用 | 函数复用、模块复用 | 组合、继承、多态等 |
| 可维护性 | 取决于模块设计 | 良好设计下通常容易维护 |
| 适用场景 | 简单任务、算法处理 | 复杂业务系统、领域建模 |
| 性能 | 不一定更快 | 不一定更慢 |

###  1.4 重点
1. OOP 的三个核心特征是 封装、继承、多态。
2. POP 不代表性能更好，OOP 也不代表一定更容易维护。
3. Java 是以面向对象为主要设计思想的语言，但也可以采用面向过程的方式组织部分代码。
4. 实际 Spring Boot 开发经常将 OOP、过程式代码与函数式编程结合使用。

## 2. 对象实例与对象引用有什么区别？
### 2.1 什么是对象实例？
使用 new 关键字通常可以创建 Java 对象实例。
```java
Person person = new Person();
```

将其拆开理解：
```java
Person person;       // 声明引用变量
person = new Person(); // 创建对象并将引用赋值给 person
```

- Person：类，定义对象的结构和行为。
- person：引用变量。
- new Person()：创建一个 Person 对象实例，并调用构造方法。
### 2.2 一个对象可以被多个引用指向
```java
Person p1 = new Person();

Person p2 = p1;
Person p3 = p1;
```

p1、p2、p3 是三个引用变量，但都指向同一个 Person 对象。
因此：
```java
System.out.println(p1 == p2); // true
System.out.println(p2 == p3); // true
```

### 2.3 引用也可以不指向任何对象
```java
Person p1 = null;
```

此时：
- p1 是一个引用变量。
- null 表示没有引用任何对象。
- 对 p1 直接调用实例方法会抛出 NullPointerException。

### 2.4 重点
- 一个引用变量在某个时刻最多指向一个对象，也可以为 null。
- 一个对象可以同时被多个引用指向。
- Java 对象和数组通常分配在 JVM 堆中，但 JIT 可能进行逃逸分析等优化。
- 引用值可以位于局部变量、对象字段、静态字段或数组元素中，不能简单理解为“引用永远在栈中”。

## 3. 对象相等与引用相等的区别
这是 Java 基础中非常重要的知识点，也是后面理解 equals() 和 hashCode() 的基础。
### 3.1 `==` 与 `equals()` 的区别
| 比较方式 | 作用 |
| --- | --- |
| `==` | 基本类型比较值；引用类型比较是否指向同一个对象 |
| `equals()` | 比较对象是否逻辑相等，具体规则由类的实现决定 |

注意：

Object 默认的 equals() 实现与引用相等具有相同的判断效果。

只有类重写了 equals()，才可能使用属性、内容等规则判断逻辑相等。
### 3.2 String 示例
```java
public class Main {
    public static void main(String[] args) {

        String str1 = "hello";
        String str2 = new String("hello");
        String str3 = "hello";

        System.out.println(str1 == str2);// false
        System.out.println(str1 == str3);// true

        System.out.println(str1.equals(str2));// true
        System.out.println(str1.equals(str3));// true
    }
}
```

输出：
```text
false
true
true
true
```

### 3.3 为什么得到这样的结果？

- str1 和 str3 引用字符串常量池中的同一个对象。
- str2 使用 new String() 创建了不同的对象。
- 三个字符串的内容都是 "hello"。

因此：
```java
str1 == str2       // false：不同对象
str1 == str3       // true：同一个对象
str1.equals(str2)  // true：内容相等
```

> 面试重点：使用 equals() 比较业务对象时，必须关注该类是否正确重写了这个方法。

<span style="color: red;">对于需要放入 HashMap 或 HashSet 的自定义对象，通常还需要一起正确重写 hashCode()。</span>

## 4. 构造方法（Constructor）
构造方法用于在创建对象时执行初始化逻辑。
### 4.1 没有声明构造方法，可以创建对象吗？
可以。
```java
public class Person {

    private String name;
    private int age;
}
```

对于这样的普通类，编译器会自动提供一个默认的无参构造方法。

从效果上可以理解为：
```java
public class Person {

    private String name;
    private int age;

    public Person() {
        super();
    }
}
```

不过，编译器生成的默认构造方法，其访问权限取决于类的声明情况，不一定都是 public。

### 4.2 自己声明构造方法之后会发生什么？
```java
public class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }
}
```

此时：
```java
Person p1 = new Person("Tom"); // 正确

Person p2 = new Person();      // 编译错误
```

原因：

一旦类显式声明了构造方法，编译器就不会再自动生成默认无参构造方法。

如果确实需要两种创建方式，就要手动重载：
```java
public class Person {

    private String name;

    // 无参构造
    public Person() {
        this("Unknown");
    }

    // 有参构造
    public Person(String name) {
        this.name = name;
    }
}
```

这里 this("Unknown") 表示调用本类的另一个构造方法。

注意：并不是所有类都必须提供无参构造方法。 如果业务要求对象创建时必须指定名称，就可以只提供有参构造方法。
### 4.3 构造方法的特点
| 特点 | 说明 |
| --- | --- |
| 方法名称 | 必须与类名相同 |
| 返回类型 | 没有返回类型，也不能写 `void` |
| 执行时机 | 创建实例时执行 |
| 重载（Overload） | 支持 |
| 重写（Override） | 不支持 |
| 继承 | 构造方法不被子类继承 |
| 主要作用 | 初始化对象 |

例如，构造方法重载：
```java
public class Student {

    private String name;
    private int age;

    public Student() {
        this("Unknown", 0);
    }

    public Student(String name) {
        this(name, 0);
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

三个构造方法名称相同，但参数列表不同，属于 <span style="color: blue;">Overload（重载）。</span>

## 5. 面向对象三大特征
Java 面向对象的三大特征是：
| 特征 | 英文 | 核心含义 |
| --- | --- | --- |
| 封装 | Encapsulation | 隐藏内部实现 |
| 继承 | Inheritance | 复用和扩展行为 |
| 多态 | Polymorphism | 统一接口，不同实现 |

### 5.1 封装（Encapsulation）
封装就是将对象内部的状态和实现细节隐藏起来，通过受控的公开方法与外界交互。
完整代码及运行示例：[点击查看 Student 封装示例](../../../../../BackEnd/Java/JavaBasic/01Encapsulation.java)。

例如：
```java
public class Student {

    // private 隐藏对象状态，避免外部直接修改字段
    private String name;
    private int age;

    // 通过 public 方法向外提供受控的读取入口
    public String getName() {
        return name;
    }

    // 通过公开方法集中控制字段修改
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        // 先校验输入，避免对象进入非法状态
        if (age < 0) {
            throw new IllegalArgumentException(
                "年龄不能为负数"
            );
        }

        // 校验通过后再更新对象状态
        this.age = age;
    }
}
```

使用：

```java
Student student = new Student();

student.setName("Tom");
student.setAge(20);

System.out.println(student.getAge()); // 20
```

这里：
- private：禁止外部直接访问字段。
- public：向外提供可访问的方法。
- setAge()：控制字段修改，并校验数据是否合法。
封装的真正目的不只是把属性改成 private，而是保护对象状态，维持业务规则。

因此，并不是每个字段都必须提供 setter。

例如银行账户的余额，不应允许任意设置：
```java
public class BankAccount {

    private int balance;

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "存款金额必须大于0"
            );
        }

        balance += amount;
    }
}
```

相比 setBalance()，deposit() 更能表达业务含义，并保护数据完整性。

### 5.2 继承（Inheritance）

继承是通过现有类定义新类的机制。

Java 使用 extends 表示类继承。
```java
class Animal {

    protected String name;

    public void eat() {
        System.out.println(name + "正在吃东西");
    }
}

class Dog extends Animal {

    public void bark() {
        System.out.println(name + "正在汪汪叫");
    }
}
```

使用：
```java
Dog dog = new Dog();

dog.name = "小黑";

dog.eat();  // 小黑正在吃东西
dog.bark(); // 小黑正在汪汪叫
```

继承的三个重要特征：
1. 子类可以复用父类可继承的成员与行为。
2. 子类可以增加自己的属性和方法。
3. 子类可以重写符合条件的父类实例方法，改变行为。
需要注意：
- Java 类只支持单继承，即一个类最多只有一个直接父类。
- 父类的 private 成员不能被子类直接访问，也不作为子类可继承的成员；但父类中的私有实例状态依然可以存在于子类对象中。
- 构造方法不会被继承，创建子类对象时仍需要执行父类构造过程。
- 继承应当表达合理的 is-a（是一种） 关系，不应只为了复用几行代码就滥用继承。

### 5.3 多态（Polymorphism）

多态是指：同一种方法调用，可以根据对象的实际类型表现出不同的行为。

这里最值得记住的是：

父类引用可以指向子类对象。
示例：`Animal`、`Dog`、`Cat`
```java
class Animal {
    public void speak() {
        System.out.println("动物发出声音");
    }
}

class Dog extends Animal {
    // 重写父类 Animal 的 speak()，提供 Dog 的具体行为
    @Override
    public void speak() {
        System.out.println("汪汪");
    }
    // Dog 新增的方法，父类中没有对应定义，因此不是重写
    public void fetch() {
        System.out.println("捡球");
    }
}

class Cat extends Animal {
    @Override
    public void speak() {
        System.out.println("喵喵");
    }
}

public class Main {

    public static void main(String[] args) {

        Animal a1 = new Dog();
        Animal a2 = new Cat();

        a1.speak();
        a2.speak();
    }
}
```

输出：
```text
汪汪
喵喵
```

虽然两个变量都声明为 Animal 类型，但执行结果不同。

原因：

Java 对可重写的实例方法采用 动态分派（Dynamic Dispatch），根据对象实际的运行时类型决定执行哪个实现。

多态的一个重要限制
```java
Animal animal = new Dog();

animal.speak(); // 正确

animal.fetch(); // 编译错误
```

为什么 fetch() 不能直接调用？

因为：
- animal 的声明类型是 Animal。
- 编译器首先根据声明类型检查能够访问哪些方法。
- Animal 中没有 fetch() 方法。
可以通过类型检查后进行向下转型：

```java
if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
    dog.fetch();
}
```

Java 16+ 还可以使用模式匹配简化：
```java
if (animal instanceof Dog dog) {
    dog.fetch();
}
```

#### 多态四个关键点
| 判断项 | 规则 |
| --- | --- |
| 能调用哪些方法？ | 首先看引用变量的编译时类型 |
| 实际执行哪个重写方法？ | 看运行时对象类型 |
| 子类特有的方法能直接调用吗？ | 不能，通常需要类型转换 |
| `static` 方法能被动态重写吗？ | 不能，静态方法是隐藏而非重写 |

额外注意：并不是所有方法调用都属于动态分派。 static、private 方法以及构造方法不采用普通实例方法重写的动态分派机制。

## 6. 接口与抽象类的共同点和区别
接口（Interface）和抽象类（Abstract Class）都是 Java 抽象设计的重要工具，但两者的定位不同。
### 6.1 什么是抽象类？
使用 abstract 声明抽象类。
```java
abstract class Animal {

    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    // 抽象方法：只有声明
    public abstract void speak();

    // 普通方法：有具体实现
    public void sleep() {
        System.out.println(name + "正在睡觉");
    }
}
```

抽象类可以同时包含：
- 普通成员变量。
- 构造方法。
- 抽象方法。
- 已经实现的普通方法。
子类可以通过 extends 继承：
```java
class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println("汪汪");
    }
}
```

抽象类本身不能直接实例化：
```java
Animal animal = new Animal("Tom"); // 编译错误
```

但可以声明抽象类引用：
```java
Animal animal = new Dog("小黑");

animal.speak(); // 汪汪
```

### 6.2 什么是接口？
接口用于定义一组行为规范，让不同的类可以提供各自的实现。
```java
interface Flyable {

    void fly();
}
```

实现接口：
```java
class Bird implements Flyable {

    @Override
    public void fly() {
        System.out.println("鸟在飞");
    }
}

class Airplane implements Flyable {

    @Override
    public void fly() {
        System.out.println("飞机在飞");
    }
}
```

使用接口实现多态：
```java
Flyable f1 = new Bird();
Flyable f2 = new Airplane();

f1.fly(); // 鸟在飞
f2.fly(); // 飞机在飞
```

此处不需要 Bird 和 Airplane 拥有相同的父类，只需要实现同一个接口即可。
### 6.3 接口与抽象类的区别
| 对比项 | 抽象类（abstract class） | 接口（interface） |
| --- | --- | --- |
| 核心目的 | 抽象共同特征、共享状态与实现 | 定义行为契约 |
| 关键字 | `abstract class` | `interface` |
| 继承方式 | `extends` | `implements` |
| 多继承 | 一个类最多继承一个父类 | 一个类可以实现多个接口 |
| 实例化 | 不能直接实例化 | 不能直接实例化 |
| 构造方法 | 可以有 | 不可以有 |
| 实例字段 | 可以有 | 不可以有 |
| 字段规则 | 可声明普通成员变量 | 字段隐式为 `public static final` |
| 抽象方法 | 支持 | 支持 |
| 普通实例方法 | 支持 | 支持 `default` 方法 |
| 静态方法 | 支持 | Java 8 起支持 |
| 私有方法 | 支持 | Java 9 起支持 |
| 使用场景 | 存在共同状态和基础实现 | 不同类型需要遵守统一行为规范 |

**记忆：**
- 抽象类强调 is-a（是什么） 关系，以及共同状态与实现。
- 接口强调 can-do（能做什么） 的行为能力。
例如：
```java
abstract class Animal {
    // 动物的共同特征
}

interface Flyable {
    void fly();
}

class Bird extends Animal implements Flyable {

    @Override
    public void fly() {
        System.out.println("Flying");
    }
}
```

Bird 是一种 Animal，同时具有 Flyable 所定义的飞行能力。

## 7. Java 8、Java 9 接口方法的新特性
Java 接口的方法规则随着 JDK 版本逐步扩展。
| JDK 版本 | 接口支持的主要方法 |
| --- | --- |
| Java 7 及之前 | `public abstract` |
| Java 8 | 新增 `default`、`static` |
| Java 9 | 新增 `private`、`private static` |

### 7.1 Java 8：`default` 方法
default 方法允许接口为某个行为提供默认实现。
```java
interface Animal {

    void eat();

    default void sleep() {
        System.out.println("动物正在睡觉");
    }
}
```

实现类：
```java
class Dog implements Animal {

    @Override
    public void eat() {
        System.out.println("狗正在吃东西");
    }
}
```

调用：
```java
Dog dog = new Dog();

dog.eat();   // 狗正在吃东西
dog.sleep(); // 动物正在睡觉
```

Dog 没有实现 sleep()，但仍然可以使用接口提供的默认行为。

> `default` 的重要意义：有助于在扩展已有接口时减少对现有实现类的修改，但不是所有新增默认方法都绝对兼容，例如多个接口之间可能产生默认方法冲突。
### 7.2 Java 8：`static` 方法
接口允许定义静态方法。
```java
interface MathUtils {

    static int add(int a, int b) {
        return a + b;
    }
}
```

调用方式：
```java
int result = MathUtils.add(10, 20);

System.out.println(result); // 30
```

静态接口方法需要通过接口名调用。
```java
// 正确
MathUtils.add(10, 20);
```

接口中的静态方法不会作为实例方法被实现类继承，也不能作为实例方法进行重写。
### 7.3 Java 9：`private` 方法
Java 9 允许接口拥有私有方法，用来复用接口内部的实现逻辑。
```java
interface MyInterface {

    // 默认方法
    default void defaultMethod() {
        commonMethod();
        instanceCommonMethod();
    }

    // 静态方法
    static void staticMethod() {
        commonMethod();
    }

    // 私有静态方法
    private static void commonMethod() {
        System.out.println("公共处理逻辑");
    }

    // 私有实例方法
    private void instanceCommonMethod() {
        System.out.println("实例内部处理");
    }
}
```

注意：
- private static 方法可以被接口内的静态方法和实例方法调用。
- private 实例方法需要实例上下文，不能直接从 static 方法调用。
- 私有接口方法不对实现类开放，也不能被实现类重写。
## 8. 接口 `default` 方法的冲突问题（补充重点）
这是理解 Java 接口与多态时容易遇到的一个问题。

假设两个接口都定义了相同签名的默认方法：
```java
interface A {

    default void hello() {
        System.out.println("Hello A");
    }
}

interface B {

    default void hello() {
        System.out.println("Hello B");
    }
}
```

如果一个类同时实现两个接口：
```java
class C implements A, B {

}
```

会发生编译错误，因为编译器无法直接选择这两个冲突的默认实现。

解决方法是显式重写：
```java
class C implements A, B {

    @Override
    public void hello() {
        A.super.hello();
    }
}
```

也可以自行提供完整实现：
```java
class C implements A, B {

    @Override
    public void hello() {
        System.out.println("Hello C");
    }
}
```

这说明：

Java 虽然允许一个类实现多个接口，但遇到同签名默认方法冲突时，仍然需要通过明确的规则或显式重写消除歧义。

以上按照原文顺序整理了面向对象的核心概念。理解这一章时，最需要区分的是三个关系：
- 类与对象：类是定义，对象是实例，引用用于访问对象。
- 继承与多态：继承建立类型关系，多态让统一的方法调用执行不同实现。
- 接口与抽象类：接口主要描述行为契约，抽象类则可以同时提供共同状态和基础实现。

这些概念在 Spring Boot 中同样重要，例如 @Service 业务类的封装、接口与实现类的分离，以及 Spring 依赖注入所依赖的多态机制。