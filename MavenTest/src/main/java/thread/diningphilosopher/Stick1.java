package thread.diningphilosopher;

public class Stick1 {
    //数组表示筷子、true表示在使用 false表示未被使用，默认都没被使用
    private boolean[] isUsed = {false,false,false,false,false};

    /**
     * 哲学家name申请筷子编号（name和（(name+1)%5））
     * @param name
     */
    public synchronized void takeFork(int name) {
        int forkName = name;
        while (isUsed[forkName]|| isUsed[(forkName+1)%5]) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("哲学家:"+name+":拿起筷子"+forkName+"和"+((forkName+1)%5));
        isUsed[forkName]=isUsed[(forkName+1)%5]=true;
    }

    public synchronized void putDownFork(int name) {
        int forkName = name;
        System.out.println("哲学家:"+name+":放下筷子"+forkName+"和"+((forkName+1)%5));
        isUsed[forkName]=isUsed[(forkName+1)%5]=false;
        notifyAll();
    }
}
