//两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。 
//
// 给出两个整数 x 和 y，计算它们之间的汉明距离。 
//
// 注意： 
//0 ≤ x, y < 231. 
//
// 示例: 
//
// 
//输入: x = 1, y = 4
//
//输出: 2
//
//解释:
//1   (0 0 0 1)
//4   (0 1 0 0)
//       ↑   ↑
//
//上面的箭头指出了对应二进制位不同的位置。
// 
// Related Topics 位运算 
// 👍 302 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P461HammingDistance {
    public static void main(String[] args) {
        Solution solution = new P461HammingDistance().new Solution();
        System.out.println(Integer.toBinaryString(8));
    }
    //
    class Solution {
        public int hammingDistance(int x, int y) {
            int xor = x ^ y;
            int distance = 0;
            while (xor != 0) {
                distance += 1;
                xor = xor & (xor - 1);
            }
            return distance;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution2 {
        //取余判断
        public int hammingDistance(int x, int y) {
            int i = x ^ y;
            int result = 0;
            while (i>0){
                if (i%2==1){
                    result++;
                }
                i = i >>> 1;
            }
            return result;
        }
    }
    class Solution1 {
        //java内部解决
        public int hammingDistance(int x, int y) {
            return Integer.bitCount(x ^ y);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}