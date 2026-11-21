//  继承示例
//  docker run --rm --mount "type=bind,source=${PWD}\BackEnd\Java\JavaBasic,target=/src,readonly" eclipse-temurin:17-jdk sh -c 'javac -encoding UTF-8 -d /tmp /src/02Inheritance.java && java -cp /tmp BackEnd.Java.JavaBasic.InheritanceDemo'
 

package BackEnd.Java.JavaBasic;

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

class InheritanceDemo {
    public static void main(String[] args) {
        Dog dog = new Dog();

        dog.name = "小黑";

        dog.eat();  // 小黑正在吃东西
        dog.bark(); // 小黑正在汪汪叫
    }
}
