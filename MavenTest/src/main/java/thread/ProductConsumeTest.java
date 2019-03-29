package thread;

import org.junit.Test;

class Resource {
    String name;
    int index;
    int count = 0;
}

/**
 * 消费者
 */
class Consume implements Runnable {

    private final Resource resource;

    Consume(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (resource) {
                while (resource.count > 0) {
                    System.out.println("消费" + resource.name + resource.index);
                    resource.count--;
                }
                try {
                    resource.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource.notify();
            }
        }
    }
}

/**
 * 生产者
 */
class Product implements Runnable {

    private final Resource resource;

    Product(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (resource) {
                while (resource.count > 10) {
                    try {
                        Thread.currentThread().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    resource.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource.count++;
                resource.notify();
            }
        }
    }
}

/**
 * description：生产者消费者问题
 *
 * @author ajie
 */
public class ProductConsumeTest {

    @Test
    public void productConsumeMain() {
        Resource resource = new Resource();
        Product product = new Product(resource);
        Consume consume = new Consume(resource);
        Thread thread1 = new Thread(product, "生产者");
        Thread thread2 = new Thread(consume, "消费者");
        thread1.start();
        thread2.start();
    }
}
