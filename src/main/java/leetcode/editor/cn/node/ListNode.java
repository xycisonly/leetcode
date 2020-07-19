package leetcode.editor.cn.node;

import leetcode.editor.cn.P21MergeTwoSortedLists;

public class ListNode{
    public ListNode next;
    public Integer val;
    public ListNode() {}
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    public ListNode(Integer val){
        this.val = val;
    }
}
