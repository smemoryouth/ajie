package design;

interface Behaves {
    /**
     * 行为
     */
    void eat();
}

/**
 * 真实业务
 */
class RealBehaves implements Behaves {
    @Override
    public void eat() {
        System.out.println("eat food!");
    }
}

/**
 * 辅助业务的操作过程
 */
class ProxyBehaves implements Behaves {
    /**
     * 真实的操作业务
     */
    private Behaves sub;

    ProxyBehaves(Behaves sub) {
        this.sub = sub;
    }

    /**
     * 真实业务前期辅助
     */
    private void preparation() {
        System.out.println("make food!");
    }

    /**
     * 真实业务后期辅助
     */
    private void postProcessing() {
        System.out.println("wash up!");
    }

    /**
     * 真实业务实现
     */
    @Override
    public void eat() {
        this.preparation();
        this.sub.eat();
        this.postProcessing();
    }
}

/**
 * 代理中使用简单工厂
 */
class Factory1 {
    static Behaves getInstance() {
        return new ProxyBehaves(new RealBehaves());
    }
}

/**
 * description：代理设计模式
 *
 * @author ajie
 * data 2018/8/4 20:32
 */
public class ProxyPattern {
    public static void main(String[] args) {
        Behaves sub = Factory1.getInstance();
        sub.eat();
    }
}
