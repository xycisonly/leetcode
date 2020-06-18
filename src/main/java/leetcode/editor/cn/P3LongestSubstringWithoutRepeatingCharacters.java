//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window

package leetcode.editor.cn;

import java.util.*;
  public class P3LongestSubstringWithoutRepeatingCharacters{
      public static void main(String[] args) {
           Solution solution = new P3LongestSubstringWithoutRepeatingCharacters().new Solution();
          System.out.println(solution.lengthOfLongestSubstring("abcbafg"));
      }

        //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        //双指针R
        int index1 = 0,index2 = 0;
        Map<Character,Integer> charMap = new HashMap<>(s.length());
        while (index2 < s.length()) {
            char index2Char = s.charAt(index2);
            Integer bufferIndex = charMap.get(index2Char);
            if (bufferIndex!=null){
                //不清楚重复的，因为index1只会是最大的，以前的数据不会造成影响
                index1 = Math.max(index1,bufferIndex + 1);
            }
            charMap.put(index2Char,index2);
            max =  Math.max(max, ++index2 - index1);
        }
        return Math.max(max,s.length()-index1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

  }