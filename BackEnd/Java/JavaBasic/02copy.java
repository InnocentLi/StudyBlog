package BackEnd.Java.JavaBasic;

class CopyDemo {
    // 可变的地址对象：修改 city 可以观察拷贝后的共享关系
    private static class Address {
        private String city;
        // 创建地址时保存初始城市
        public Address(String city) {
            this.city = city;
        }

        // 获取当前城市
        public String getCity() {
            return city;
        }

        // 修改当前 Address 对象的城市
        public void setCity(String city) {
            this.city = city;
        }
    }

    // 实现 Cloneable，允许通过 Object.clone() 复制 Person
    private static class Person implements Cloneable {
        // String 不可变，可以安全共享；Address 可变，需要关注是否共享
        private String name;
        private Address address;

        // 保存姓名和地址的引用，不会复制传入的 Address
        public Person(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        // 返回 Person 持有的 Address 引用，不会创建新地址
        public Address getAddress() {
            return address;
        }

        // 扩大 clone() 的访问范围，并返回 Person 类型
        @Override
        public Person clone() {
            try {
                // 创建新的 Person，但 name 和 address 的引用仍与原对象相同
                return (Person) super.clone();
            } catch (CloneNotSupportedException e) {
                // 本类实现了 Cloneable，正常情况下不会发生此异常
                throw new AssertionError(e);
            }
        }

        // 同时复制 Person 和可变的 Address
        public Person deepCopy() {
            // 原地址为 null 时保留 null；否则创建独立的 Address
            Address copiedAddress = address == null
                    ? null
                    : new Address(address.getCity());
            // name 和 city 是不可变的 String，可以继续共享
            return new Person(name, copiedAddress);
        }
    }

    // 分别运行三个互不影响的示例
    public static void main(String[] args) {
        referenceCopy();
        shallowCopy();
        deepCopy();
    }

    // 引用拷贝：只复制引用，不创建 Person 或 Address
    private static void referenceCopy() {
        Person p1 = new Person("Tom", new Address("武汉"));
        // p1 和 p2 指向同一个 Person
        Person p2 = p1;

        System.out.println("引用拷贝：");
        // == 比较引用是否指向同一个对象
        System.out.println("同一个 Person：" + (p1 == p2));
        System.out.println("同一个 Address：" + (p1.getAddress() == p2.getAddress()));
        // 修改的是共用的地址，因此两个引用看到的城市都会变化
        p2.getAddress().setCity("东京");
        System.out.println("修改 p2 后，p1 的城市：" + p1.getAddress().getCity());
        System.out.println("修改 p2 后，p2 的城市：" + p2.getAddress().getCity());
    }

    // 浅拷贝：创建新 Person，但仍共享内部 Address
    private static void shallowCopy() {
        Person p1 = new Person("Tom", new Address("武汉"));
        Person p2 = p1.clone();

        System.out.println("\n浅拷贝：");
        // Person 不同，但 Address 相同
        System.out.println("同一个 Person：" + (p1 == p2));
        System.out.println("同一个 Address：" + (p1.getAddress() == p2.getAddress()));
        // p2 修改共享的 Address，p1 看到的城市也会变化
        p2.getAddress().setCity("东京");
        System.out.println("修改 p2 后，p1 的城市：" + p1.getAddress().getCity());
        System.out.println("修改 p2 后，p2 的城市：" + p2.getAddress().getCity());
    }

    // 深拷贝：创建新 Person 和独立的 Address
    private static void deepCopy() {
        Person p1 = new Person("Tom", new Address("武汉"));
        Person p2 = p1.deepCopy();

        System.out.println("\n深拷贝：");
        // Person 和 Address 都是不同的对象
        System.out.println("同一个 Person：" + (p1 == p2));
        System.out.println("同一个 Address：" + (p1.getAddress() == p2.getAddress()));
        // p2 修改自己的 Address，不影响 p1 的地址
        p2.getAddress().setCity("东京");
        System.out.println("修改 p2 后，p1 的城市：" + p1.getAddress().getCity());
        System.out.println("修改 p2 后，p2 的城市：" + p2.getAddress().getCity());
    }
}
