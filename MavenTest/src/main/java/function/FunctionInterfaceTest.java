package function;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * description：常用的内建函数式接口
 *
 * @author ajie
 * data 2018/8/12 18:30
 */
public class FunctionInterfaceTest {

    /**
     * 功能型函数式接口
     */
    @Test
    public void function() {
        Function<String, Boolean> fun = "**hello" :: startsWith;
        // true
        System.out.println(fun.apply("**"));
    }

    /**
     * 消费性函数式接口
     */
    @Test
    public void consumer() {
        Consumer<String> consumer = System.out :: println;
        // hello
        consumer.accept("hello");
    }

    /**
     * 供给型函数式接口
     */
    @Test
    public void supplier(){
        Supplier<String> su = "HELLO WORLD" :: toLowerCase;
        // hello world
        System.out.println(su.get());
    }

    /**
     * 断言性函数式接口
     */
    @Test
    public void predicate(){
        Predicate<String> pre = "Hello" :: equalsIgnoreCase;
        // true
        System.out.println(pre.test("HELLO"));
    }
}
