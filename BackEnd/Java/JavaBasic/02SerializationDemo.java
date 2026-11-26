package BackEnd.Java.JavaBasic;

import java.io.*;

class SerializationDemo {

    // 需要序列化的类
    static class Person implements Serializable {
        // 构造方法、getter、setter等可以根据需要添加
        private static final long serialVersionUID = 1L;
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{name='" + name
                    + "', age=" + age + "}";
        }
    }

    public static void main(String[] args) throws Exception {

        Person person = new Person("张三", 25);

        // ① 序列化：对象 -> 文件
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                         new FileOutputStream("person.ser"))) {

            oos.writeObject(person);
            System.out.println("序列化完成");
        }

        // ② 反序列化：文件 -> 对象
        Person restored;

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                         new FileInputStream("person.ser"))) {

            restored = (Person) ois.readObject();
        }

        // ③ 验证结果
        System.out.println("原始对象：" + person);
        System.out.println("恢复对象：" + restored);
        System.out.println("是否同一个对象：" + (person == restored));
    }
}
