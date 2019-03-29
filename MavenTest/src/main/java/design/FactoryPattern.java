package design;

/**
 * 抽象类
 */
abstract class AbstractAnimal {
    /**
     * 抽象方法
     */
    protected abstract void eat();
}

/**
 * 简单的实现类1
 */
class Dog extends AbstractAnimal {
    @Override
    protected void eat() {
        System.out.println("dog!");
    }
}

/**
 * 简单的实现类2
 */
class Cat extends AbstractAnimal {
    @Override
    protected void eat() {
        System.out.println("cat!");
    }
}

/**
 * 泛型工厂
 */
class Factory {
    @SuppressWarnings("unchecked")
    static <T> T getInstance(String className) {
        T obj = null;
        try {
            // newInstance()方法在java9中过期，使用下面的方法代替
            obj = (T) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}

/**
 * description：泛型实现的终极工厂设计模式
 *
 * @author ajie
 * data 2018/8/4 19:24
 */
public class FactoryPattern {
    public static void main(String[] args) {
        // 参数必须是完整的类路径
        Dog dog = Factory.getInstance("design.Dog");
        dog.eat();
        Cat cat = Factory.getInstance("design.Cat");
        cat.eat();
    }
}
