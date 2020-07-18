//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
// 注意空字符串可被认为是有效字符串。
//
// 示例 1:
//
// 输入: "()"
//输出: true
//
//
// 示例 2:
//
// 输入: "()[]{}"
//输出: true
//
//
// 示例 3:
//
// 输入: "(]"
//输出: false
//
//
// 示例 4:
//
// 输入: "([)]"
//输出: false
//
//
// 示例 5:
//
// 输入: "{[]}"
//输出: true
// Related Topics 栈 字符串
// 👍 1696 👎 0

package leetcode.editor.cn;

import java.util.*;
  public class P20ValidParentheses{
       public static void main(String[] args) {
           Solution solution = new P20ValidParentheses().new Solution();
           System.out.println(solution.isValid("()"));
      }
        //leetcode submit region begin(Prohibit modification and deletion)
        class Solution {
           private Map<Character,Character> charMap = new HashMap<>(8);
            {
                charMap.put(')','(');
                charMap.put(']','[');
                charMap.put('}','{');
            }
            public boolean isValid(String s) {
                if (null == s){
                    return true;
                }
                Stack<Character> stack = new Stack<>();
                for (int stringIndex = 0;stringIndex<s.length();stringIndex++){
                    Character oneChar = s.charAt(stringIndex);
                    if ( !charMap.containsKey(oneChar)){
                        //放入
                        stack.push(oneChar);
                    }else if (!stack.empty()) {
                        //取出
                        if (!stack.pop().equals(charMap.get(oneChar))){
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
                return stack.empty();
            }
        }
//leetcode submit region end(Prohibit modification and deletion)

  }