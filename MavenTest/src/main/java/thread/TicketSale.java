package thread;

import java.util.Random;

/**
 * description：
 *
 * @author ajie
 * data 2018/10/12 16:38
 */
public class TicketSale implements Runnable{
    //票总量
    private int ticketCount = 100;
    //票编码
    private int ticketNum;

    private Random random = new Random();
    @Override
    public void run() {
        while (ticketCount > 0) {
            synchronized (this) {
                if (ticketCount > 0) {
//                    random.nextInt(2000);
                    ticketCount--;
                    ticketNum++;
                    System.out.println("售票窗口:"+Thread.currentThread().getName()+" 当前票号："+ticketNum);
                }
            }
        }
    }

    public static void main(String[] args) {
        TicketSale ticketSale = new TicketSale();
        for (int i = 1; i <= 6; i++) {
            new Thread(ticketSale, ""+i).start();
        }
    }
}
