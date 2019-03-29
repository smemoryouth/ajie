package zhongfu;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * description：可重入的自旋锁
 *
 * @author 阿劼
 * data 2019/1/16 14:28
 */
public class SpinLock implements Lock {

    /**
     * 锁持有线程, null表示锁未被任何线程持有
     */
    private final AtomicReference<Thread> owner = new AtomicReference<Thread>();

    /**
     * owner持有锁次数
     */
    private int holdCount;   //state

    @Override
    public void lock() {
        final AtomicReference<Thread> owner = this.owner;

        final Thread current = Thread.currentThread();
        if (owner.get() == current) { // 当前线程已持有锁, 增加持有计数即可
            ++holdCount;
            return;
        }

        while (!owner.compareAndSet(null, current)) {
        }

        holdCount = 1;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        final AtomicReference<Thread> owner = this.owner;

        final Thread current = Thread.currentThread();
        if (owner.get() == current) {
            ++holdCount;
            return;
        }

        while (!owner.compareAndSet(null, current)) {
            // 响应中断
            if (current.isInterrupted()) {
                current.interrupt(); // 重设中断标志
                throw new InterruptedException();
            }
        }

        holdCount = 1;
    }

    @Override
    public boolean tryLock() {
        boolean locked = owner.compareAndSet(null, Thread.currentThread());
        if (locked) {
            holdCount = 1;
        }
        return locked;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        final AtomicReference<Thread> owner = this.owner;
        final Thread current = Thread.currentThread();
        if (owner.get() == current) {
            ++holdCount;
            return true;
        }

        final long start = System.nanoTime();
        final long timeoutNanos = unit.toNanos(time);
        while (!owner.compareAndSet(null, current)) {
            // 响应中断
            if (current.isInterrupted()) {
                current.interrupt();
                throw new InterruptedException();
            }
            // 判断是否超时
            long elapsed = System.nanoTime() - start;
            if (elapsed >= timeoutNanos) {
                return false;
            }
        }

        holdCount = 1;
        return true;
    }

    @Override
    public void unlock() {
        final AtomicReference<Thread> owner = this.owner;
        final Thread current = Thread.currentThread();

        if (owner.get() != current) {
            throw new IllegalMonitorStateException();
        }
        // 持有多少次, 就必须释放多少次
        if (--holdCount == 0) {
            owner.set(null);
        }
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }
}

