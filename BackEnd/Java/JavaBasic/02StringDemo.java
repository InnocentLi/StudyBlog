/**
 * Java String 综合演示
 * 
 * 核心内容：
 * 1. String、StringBuilder、StringBuffer 的区别
 * 2. String 的不可变性
 * 3. 线程安全性对比
 * 4. 字符串拼接的最佳实践
 * 5. 字符串常量池与 intern()
 * 
 * Link: https://github.com/InnocentLi/StudyBlog/blob/main/doc/BackEnd/Java/JavaBasic/src/02Java基础概念与常识-中.md
 */
package BackEnd.Java.JavaBasic;
class StringDemo {
    
    // ============ 1. String 不可变性演示 ============
    
    public static void demonstrateStringImmutability() {
        System.out.println("--- 1. String 不可变性 ---");
        
        String str1 = "Hello";
        String str2 = str1 + " World";
        
        System.out.println("str1 = " + str1); // Hello
        System.out.println("str2 = " + str2); // Hello World
        System.out.println("str1 == str2: " + (str1 == str2)); // false (不同对象)
        System.out.println("str1.equals(str2): " + (str1.equals(str2))); // false (内容不同)
    }
    
    // ============ 2. StringBuilder 可变性演示 ============
    
    public static void demonstrateStringBuilderMutability() {
        System.out.println("\n--- 2. StringBuilder 可变性 ---");
        
        StringBuilder sb = new StringBuilder("Hello");
        StringBuilder original = sb;
        
        sb.append(" World");
        
        System.out.println("sb = " + sb); // Hello World
        System.out.println("original = " + original); // Hello World
        System.out.println("sb == original: " + (sb == original)); // true (同一对象)
    }
    
    // ============ 3. 线程安全性演示：StringBuilder ============
    
    public static void demonstrateStringBuilderThreadUnsafe() 
            throws InterruptedException {
        System.out.println("\n--- 3. StringBuilder 线程不安全演示 ---");
        
        StringBuilder sb = new StringBuilder();
        
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                sb.append("A");
            }
        };
        
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("预期长度：20000");
        System.out.println("实际长度：" + sb.length());
        // 实际长度可能小于 20000，因为线程竞争
    }
    
    // ============ 4. 线程安全性演示：StringBuffer ============
    
    public static void demonstrateStringBufferThreadSafe() 
            throws InterruptedException {
        System.out.println("\n--- 4. StringBuffer 线程安全演示 ---");
        
        StringBuffer sb = new StringBuffer();
        
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                sb.append("B");
            }
        };
        
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("预期长度：20000");
        System.out.println("实际长度：" + sb.length()); // 总是 20000
    }
    
    // ============ 5. 不可靠的原子性演示 ============
    
    public static void demonstrateNonAtomicity() throws InterruptedException {
        System.out.println("\n--- 5. StringBuffer 不保证原子性：多个方法组合操作 ---");
        
        StringBuffer buffer = new StringBuffer();
        int maxLength = 10;
        int[] exceededCount = {0};
        
        // 极端例子：在检查和追加之间故意让出 CPU 时间，制造竞争条件
        Runnable aggressiveTask = () -> {
            for (int i = 0; i < 50; i++) {
                if (buffer.length() < maxLength) {
                    // ★ 关键：length() 检查后释放锁
                    // 这里主动让出 CPU，让其他线程有机会也执行检查
                    Thread.yield();
                    
                    // 现在其他线程可能也通过了检查，但还没执行 append()
                    // 然后我们继续执行 append()
                    buffer.append("X");
                    
                    if (buffer.length() > maxLength) {
                        exceededCount[0]++;
                    }
                }
                // 再让出一次 CPU，给其他线程机会
                Thread.yield();
            }
        };
        
        // 使用更多线程
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(aggressiveTask, "T-" + i);
        }
        
        for (Thread t : threads) {
            t.start();
        }
        
        for (Thread t : threads) {
            t.join();
        }
        
        System.out.println("设定阈值: < " + maxLength);
        System.out.println("最终长度: " + buffer.length());
        System.out.println("超过阈值的次数: " + exceededCount[0]);
        System.out.println("预期最多: " + maxLength + " 个字符");
        
        if (buffer.length() > maxLength) {
            System.out.println("✓ 成功演示！结果超过了阈值");
            System.out.println("  原因：多个线程同时通过了 length < " + maxLength + " 的检查");
            System.out.println("  然后都执行了 append()，导致最终长度 " + buffer.length() + " > " + maxLength);
        } else {
            System.out.println("（未超过，可能是线程调度原因，重新运行可能会超过）");
        }
    }
    
    // ============ 6. 字符串拼接性能对比 ============
    
    public static void demonstrateStringConcatenationPerformance() {
        System.out.println("\n--- 6. 字符串拼接性能对比 ---");
        
        int iterations = 10000;
        
        // 方法1：使用 +（不推荐）
        long start1 = System.currentTimeMillis();
        String result1 = "";
        for (int i = 0; i < iterations; i++) {
            result1 += "X";
        }
        long time1 = System.currentTimeMillis() - start1;
        
        // 方法2：使用 StringBuilder（推荐）
        long start2 = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("X");
        }
        String result2 = sb.toString();
        long time2 = System.currentTimeMillis() - start2;
        
        System.out.println("循环 " + iterations + " 次拼接：");
        System.out.println("  使用 +=：" + time1 + " ms");
        System.out.println("  使用 StringBuilder：" + time2 + " ms");
        System.out.println("  性能差异：约 " + (time1 / (double) time2) + " 倍");
    }
    
    // ============ 7. 字符串常量池演示 ============
    
    public static void demonstrateStringPool() {
        System.out.println("\n--- 7. 字符串常量池 ---");
        
        // 字面量字符串进入常量池
        String s1 = "Hello";
        String s2 = "Hello";
        System.out.println("s1 == s2 (字面量)：" + (s1 == s2)); // true
        
        // new 创建新对象，不直接进入常量池
        String s3 = new String("Hello");
        System.out.println("s1 == s3 (new 创建)：" + (s1 == s3)); // false
        System.out.println("s1.equals(s3)：" + (s1.equals(s3))); // true
        
        // intern() 强制进入常量池
        String s4 = new String("Hello").intern();
        System.out.println("s1 == s4 (intern())：" + (s1 == s4)); // true
    }
    
    // ============ 8. String 不可变的防护机制 ============
    
    public static void demonstrateStringImmutabilityProtection() {
        System.out.println("\n--- 8. String 不可变性防护 ---");
        
        // 通过修改原数组，尝试破坏字符串
        char[] chars = {'J', 'a', 'v', 'a'};
        String str = new String(chars);
        
        chars[0] = 'K';
        
        System.out.println("原数组修改后：" + String.valueOf(chars)); // Kava
        System.out.println("String 内容：" + str); // Java（未受影响）
        System.out.println("原因：String 复制了数组数据，而不是直接使用外部数组");
    }
    
    // ============ 9. JDK 8 vs JDK 9+ 字符串存储 ============
    
    public static void demonstrateStringStorage() {
        System.out.println("\n--- 9. JDK 8 vs JDK 9+ 字符串存储 ---");
        
        String english = "Hello"; // 可使用 Latin-1 (1 字节/字符)
        String chinese = "你好"; // 需要 UTF-16 (2 字节/字符)
        
        System.out.println("JDK 8：");
        System.out.println("  'Hello' 占用 10 字节（5个 char * 2字节）");
        System.out.println("  '你好' 占用 4 字节（2个 char * 2字节）");
        
        System.out.println("JDK 9+ (Compact Strings)：");
        System.out.println("  'Hello' 占用 5 字节（5个 Latin-1 字符 * 1字节）");
        System.out.println("  '你好' 占用 4 字节（2个 UTF-16 字符 * 2字节）");
    }
    
    // ============ 10. 最佳实践总结 ============
    
    public static void demonstrateBestPractices() {
        System.out.println("\n--- 10. 最佳实践 ---");
        
        // ✗ 不推荐：循环中使用 +=
        System.out.println("✗ 不推荐 (循环中 +=):");
        String result = "";
        for (int i = 0; i < 5; i++) {
            result += i; // 每次都创建新对象，O(n²) 复杂度
        }
        System.out.println("  结果：" + result);
        
        // ✓ 推荐：循环外创建 StringBuilder
        System.out.println("✓ 推荐 (StringBuilder):");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(i); // 高效，O(n) 复杂度
        }
        System.out.println("  结果：" + sb.toString());
        
        // 应用场景总结
        System.out.println("\n应用场景：");
        System.out.println("- String：普通字符串存储、读取、少量拼接");
        System.out.println("- StringBuilder：单线程循环拼接、动态构建字符串");
        System.out.println("- StringBuffer：多线程共享字符串缓冲（罕见）");
    }
    
    // ============ 主方法 ============
    
    public static void main(String[] args) throws InterruptedException {
        demonstrateStringImmutability();
        demonstrateStringBuilderMutability();
        
        try {
            demonstrateStringBuilderThreadUnsafe();
        } catch (Exception e) {
            System.out.println("线程测试异常（预期）");
        }
        
        demonstrateStringBufferThreadSafe();
        demonstrateNonAtomicity();
        demonstrateStringConcatenationPerformance();
        demonstrateStringPool();
        demonstrateStringImmutabilityProtection();
        demonstrateStringStorage();
        demonstrateBestPractices();
        
        System.out.println("\n=== 总结 ===");
        System.out.println("1. String 不可变：引用内容永不改变");
        System.out.println("2. StringBuilder 高效：单线程优选，O(n) 拼接");
        System.out.println("3. StringBuffer 安全：多线程同步，性能低");
        System.out.println("4. 避免循环 +=：容易形成 O(n²) 复杂度");
        System.out.println("5. 常量池优化：相同字符串复用同一对象");
    }
}

/*
 * 源文档链接：
 * https://github.com/InnocentLi/StudyBlog/blob/main/doc/BackEnd/Java/JavaBasic/src/02Java基础概念与常识-中.md
 * 
 * 对应章节：
 * - 13. Java String 详解
 * - 13.1 String、StringBuffer、StringBuilder 的区别
 * - 13.2 StringBuilder 与 StringBuffer 的线程安全性
 * - 13.3 String 为什么是不可变的
 * - 13.4 字符串拼接使用 + 还是 StringBuilder
 * - 13.5 为什么不推荐在循环中频繁使用 + 拼接
 */
