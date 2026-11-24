/**
 * Object 类核心方法综合示例
 * 
 * 本文件演示 Object 类中的关键方法：
 * - getClass()：获取运行时类型
 * - toString()：对象的字符串表示
 * - clone()：创建对象副本
 * - finalize()：已弃用的对象终结方法
 * - ==  vs  equals()：引用比较 vs 内容比较
 * 
 * Link: doc/BackEnd/Java/JavaBasic/src/02Java基础概念与常识-中.md
 */
package BackEnd.Java.JavaBasic;
class ObjectOthersDemo {
    
    // ============ ① getClass() 示例 ============
    public static void demonstrateGetClass() {
        System.out.println("--- ① getClass() 示例 ---");
        
        String str = "Hello";
        System.out.println(str.getClass());
        // 输出: class java.lang.String
        
        Object obj = new Object();
        System.out.println(obj.getClass());
        // 输出: class java.lang.Object
    }
    
    // ============ ② toString() 示例 ============
    
    // 未重写 toString 的类
    static class PersonWithoutToString {
        private String name = "Tom";
    }
    
    // 重写 toString 的类
    static class Person {
        private String name;
        private int age;
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }
    
    public static void demonstrateToString() {
        System.out.println("--- ② toString() 示例 ---");
        
        // 未重写 toString()
        PersonWithoutToString p1 = new PersonWithoutToString();
        System.out.println("未重写 toString()");
        System.out.println(p1);
        // 输出类似: BackEnd.Java.JavaBasic.ObjectOthersDemo$PersonWithoutToString@18b4aac2
        
        // 重写 toString()
        Person p2 = new Person("Alice", 25);
        System.out.println("重写 toString()");
        System.out.println(p2);
        // 输出: Person{name='Alice', age=25}
    }
    
    // ============ ③ clone() 示例 ============
    
    static class Book implements Cloneable {
        private String title;
        private double price;
        
        public Book(String title, double price) {
            this.title = title;
            this.price = price;
        }
        
        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
        
        @Override
        public String toString() {
            return "Book{title='" + title + "', price=" + price + "}";
        }
    }
    
    public static void demonstrateClone() throws CloneNotSupportedException {
        System.out.println("--- ③ clone() 示例 ---");
        
        Book original = new Book("Java Programming", 59.99);
        Book cloned = (Book) original.clone();
        
        System.out.println("原对象: " + original);
        System.out.println("克隆对象: " + cloned);
        System.out.println("是否同一对象: " + (original == cloned));
        // 输出: false (浅拷贝创建了新对象)
    }
    
    // ============ ④ == vs equals() 示例 ============
    
    public static void demonstrateEqualsAndEquality() {
        System.out.println("--- ④ == vs equals() 示例 ---");
        
        // 基本数据类型
        System.out.println("基本数据类型:");
        int a = 10;
        int b = 10;
        System.out.println("a == b: " + (a == b)); // true
        System.out.println("42 == 42.0: " + (42 == 42.0)); // true (数值类型转换)
        
        // 引用数据类型
        System.out.println("\n引用数据类型 (String 池):");
        String aa = "ab";
        String bb = "ab";
        System.out.println("aa == bb: " + (aa == bb)); // true (同一个常量池对象)
        System.out.println("aa.equals(bb): " + (aa.equals(bb))); // true
        
        System.out.println("\n引用数据类型 (new 创建):");
        String a1 = new String("ab");
        String b1 = new String("ab");
        System.out.println("a1 == b1: " + (a1 == b1)); // false (不同对象)
        System.out.println("a1.equals(b1): " + (a1.equals(b1))); // true (内容相同)
        
        // 自定义对象 (未重写 equals)
        System.out.println("\n自定义对象 (未重写 equals):");
        Person p1 = new Person("Tom", 20);
        Person p2 = new Person("Tom", 20);
        System.out.println("p1 == p2: " + (p1 == p2)); // false
        System.out.println("p1.equals(p2): " + (p1.equals(p2))); // false (继承 Object 的默认实现)
    }
    
    // ============ ⑤ equals() 覆盖示例 ============
    
    static class Student {
        private String id;
        private String name;
        
        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            
            Student student = (Student) o;
            return id.equals(student.id);
        }
        
        @Override
        public String toString() {
            return "Student{id='" + id + "', name='" + name + "'}";
        }
    }
    
    public static void demonstrateCustomEquals() {
        System.out.println("--- ⑤ 自定义 equals() 示例 ---");
        
        Student s1 = new Student("001", "Alice");
        Student s2 = new Student("001", "Alice");
        Student s3 = new Student("002", "Bob");
        
        System.out.println("s1.equals(s2): " + s1.equals(s2)); // true (同一学号)
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // false (不同学号)
    }
    
    // ============ 主方法 ============
    
    public static void main(String[] args) throws CloneNotSupportedException {
        demonstrateGetClass();
        System.out.println();
        
        demonstrateToString();
        System.out.println();
        
        demonstrateClone();
        System.out.println();
        
        demonstrateEqualsAndEquality();
        System.out.println();
        
        demonstrateCustomEquals();
        System.out.println();
        
        System.out.println("=== 总结 ===");
        System.out.println("1. getClass() 返回运行时类型信息，常用于反射");
        System.out.println("2. toString() 用于对象的字符串表示，建议重写便于调试");
        System.out.println("3. clone() 创建浅拷贝，需实现 Cloneable 接口");
        System.out.println("4. finalize() 已弃用，应使用 try-with-resources");
        System.out.println("5. == 比较引用，equals() 比较内容（如果重写）");
    }
}

/*
 * 源文档链接：
 * https://github.com/InnocentLi/StudyBlog/blob/main/doc/BackEnd/Java/JavaBasic/src/02Java基础概念与常识-中.md
 * 
 * 对应章节：
 * - 10. Object 类概述
 * - 10.1 Object 类的常用方法
 * - 10.2 == 和 equals() 的区别
 */
