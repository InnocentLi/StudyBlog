package BackEnd.Java.JavaBasic;

class Student {

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

    public static void main(String[] args) {
        Student student = new Student();

        student.setName("Tom");
        student.setAge(20);

        System.out.println(student.getAge()); // 20
    }
}