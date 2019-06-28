package com.CK;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ListNode l1_1 = new ListNode(2);
        ListNode l1_2 = new ListNode(4);
        ListNode l1_3 = new ListNode(3);
        l1_2.next = l1_3;
        l1_1.next = l1_2;

        ListNode l2_1 = new ListNode(5);
        ListNode l2_2 = new ListNode(6);
        ListNode l2_3 = new ListNode(4);
        l2_2.next = l2_3;
        l2_1.next = l2_2;

        Solution solution = new Solution();
        solution.addTwoNumbers(l1_1, l2_1);

    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String l1IntString = convertListNodeToString(l1);
        String l2IntString = convertListNodeToString(l2);

        ArrayList<Integer> resultArrayList = new ArrayList<Integer>();
        int iteration = Integer.max(l1IntString.length(), l2IntString.length()) + 1;
        int l1Int, l2Int, carryBit = 0;
        for (int i = 0; i < iteration; i++) {
            try {
                l1Int = Character.getNumericValue(l1IntString.charAt(i));
            } catch (StringIndexOutOfBoundsException e) {
                l1Int = 0;
            }
            try {
                l2Int = Character.getNumericValue(l2IntString.charAt(i));
            } catch (StringIndexOutOfBoundsException e) {
                l2Int = 0;
            }
            if (l1Int + l2Int + carryBit < 10) {
                if (!(l1Int + l2Int + carryBit == 0 && i == iteration - 1)) {
                    resultArrayList.add(l1Int + l2Int + carryBit);
                    carryBit = 0;
                }
            } else {
                resultArrayList.add(Character.getNumericValue(String.valueOf(l1Int + l2Int + carryBit).charAt(1)));
                carryBit = Character.getNumericValue(String.valueOf(l1Int + l2Int + carryBit).charAt(0));
            }
        }

        return convertArrayListToListNode(resultArrayList);
    }

    public String convertListNodeToString(ListNode listNode) {
        String returnString = "";
        while (listNode.next != null) {
            returnString = returnString.concat(String.valueOf(listNode.val));
            listNode = listNode.next;
        }
        returnString = returnString.concat(String.valueOf(listNode.val));
        return returnString;
    }

//    public void printListNode(ListNode listNode) {
//        while (listNode.next != null) {
//            System.out.print(listNode.val + "->");
//            listNode = listNode.next;
//        }
//        System.out.print(listNode.val + ".\n");
//    }
//
//    public ListNode reversedListNode(ListNode head) {
//        if (head == null) {
//            return null;
//        }
//        ListNode current = head;
//        ListNode prevNode = null;
//        ListNode newHead = null;
//
//        while (current != null) {
//            ListNode nextNode = current.next;
//            current.next = prevNode;
//            if (nextNode == null) {
//                newHead = current;
//            }
//            prevNode = current;
//            current = nextNode;
//        }
//
//        return newHead;
//    }

    public ListNode convertArrayListToListNode(ArrayList<Integer> arrayList) {
        if (arrayList.size() == 1) {
            return new ListNode(arrayList.get(0));
        }
        ListNode head = new ListNode(arrayList.get(0));
        ListNode current = head;
        for (int i = 1; i < arrayList.size(); i++) {
            current.next = new ListNode(arrayList.get(i));
            current = current.next;
        }
        return head;
    }
}

class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode p, dummy = new ListNode(0);
        p = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            p.next = new ListNode(carry % 10);
            carry /= 10;
            p = p.next;
        }
        return dummy.next;
    }
}