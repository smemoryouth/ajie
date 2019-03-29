package function;

import java.util.ArrayList;
import java.util.Stack;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/2/9 19:30
 */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Solution {
    public static  ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while(listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        while(!stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }

      public static void main(String[] args){
        ListNode node = new ListNode(10);
    node.next = new ListNode(20);
    node.next.next = new ListNode(30);
        System.out.println(printListFromTailToHead(node));
    }
}