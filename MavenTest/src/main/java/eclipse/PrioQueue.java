package eclipse;

/**
 * 优先级队列 ，数字越小优先级越高
 */

 class PrioLink{

       class Entry{
           int data;
           Entry next;
           int prio;
           public Entry(){
               data = -1;
               prio = -1;
               next = null;
           }

           public Entry(int data, int prio){
               this.data = data;
               this.prio = prio;
               next = null;
           }
       }

       public Entry head;
       public PrioLink(){
           head = new Entry();
       }

       public boolean isEmpty(){
           return head.next == null;
       }
       public void insert(int data,int prio){
           Entry entry = new Entry(data,prio);
           Entry cur = head;
           while (cur.next != null){
               if (cur.next.prio > entry.prio){
                   break;
               }
               cur = cur.next;
           }
           entry.next = cur.next;
           cur.next = entry;
       }

       public void pop(){
           if(isEmpty()){
               return;
           }
           Entry entry = head;
           Entry del = entry.next;
           entry.next = del.next;
           del = null;
       }

       public void show (){
           Entry cur = head.next;
           while (cur != null){
               System.out.print(cur.data + " ");
               cur = cur.next;
           }
           System.out.println();
       }

}
public class PrioQueue {
    public static void  main(String[] args){
        PrioLink link = new PrioLink();
        link.insert(10,6);
        link.insert(20,5);
        link.insert(30,4);
        link.show();
        link.pop();
        link.show();
    }
}
