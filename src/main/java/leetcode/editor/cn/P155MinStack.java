//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。 
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计 
// 👍 609 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P155MinStack {
    public static void main(String[] args) {
        MinStack solution = new P155MinStack().new MinStack();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class MinStack {
        private LinkedList<Integer> master = new LinkedList<>();
        private LinkedList<Integer> helpList = new LinkedList<>();

        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        public void push(int x) {
            master.push(x);
            if (helpList.isEmpty()) {
                helpList.push(x);
            } else {
                Integer peek = helpList.peek();
                helpList.push(peek > x ? x : peek);
            }
        }

        public void pop() {
            master.remove();
            helpList.remove();
        }

        public int top() {
            return master.peek();
        }

        public int getMin() {
            return helpList.peek();
        }
    }
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)

}