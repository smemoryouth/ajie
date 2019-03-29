package collection;

import java.util.LinkedList;

/**
 * description：
 *
 * @author ajie
 * data 2018/6/13
 */
public class LinkedListTest {
    public static void main(String[] args){
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(10);
        linkedList.add(40);
        linkedList.addFirst(100);
        linkedList.addLast(200);

//        System.out.println(linkedList.get(0));
//        System.out.println(linkedList.set(0,20));
//        System.out.println(linkedList.toString());
       /* System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
        System.out.println(linkedList.toString());*/
       /* linkedList.addFirst(100);
        linkedList.addFirst(200);
        linkedList.addFirst(300);
        linkedList.offer(1000);
        linkedList.add(1210);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.remove());
        System.out.println(linkedList.toString());
        System.out.println(linkedList.element());
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
        System.out.println(linkedList.peek());
        System.out.println(linkedList.poll());
        System.out.println(linkedList);
        System.out.println(linkedList.pop());
        System.out.println(linkedList);
        linkedList.push(100000);
        System.out.println(linkedList);
        System.out.println(linkedList.remove());
        System.out.println(linkedList.set(0,20));
        System.out.println(linkedList);*/
       /*Long l = System.currentTimeMillis();
       for(int x = 0; x < 300000; x++){
           linkedList.add(x);
       }
       Long l1 = System.currentTimeMillis();
       System.out.println(l1 - l);*/
//        Iterator<Integer> integer = linkedList.iterator();
//        while(integer.hasNext()){
//            System.out.print(integer.next() + " ");
//        }
//       ListIterator<Integer> listIterator = linkedList.listIterator();
//       while (listIterator.hasPrevious()){
//           System.out.println(listIterator.previous());
//       }
//        System.out.println(listIterator.previous());
        /*listIterator.next();
        listIterator.add(12);
        listIterator.next();
        listIterator.next();
        System.out.println(listIterator.previous());
        System.out.println(listIterator.previous());
        System.out.println(listIterator.next());
        listIterator.remove();
        System.out.println(linkedList);*/

//        while (d.hasPrevious()){
//            System.out.println(d.previous());
//        }
    }
}
