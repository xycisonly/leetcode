//
//如果一个密码满足下述所有条件，则认为这个密码是强密码：
//
// 
// 由至少 6 个，至多 20 个字符组成。 
// 至少包含 一个小写 字母，一个大写 字母，和 一个数字 。 
// 同一字符 不能 连续出现三次 (比如 "...aaa..." 是不允许的, 但是 "...aa...a..." 如果满足其他条件也可以算是强密码)。 
// 
//
// 给你一个字符串 password ，返回 将 password 修改到满足强密码条件需要的最少修改步数。如果 password 已经是强密码，则返回 0 
//。 
//
// 在一步修改操作中，你可以： 
//
// 
// 插入一个字符到 password ， 
// 从 password 中删除一个字符，或 
// 用另一个字符来替换 password 中的某个字符。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：password = "a"
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：password = "aA1"
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：password = "1337C0d3"
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= password.length <= 50 
// password 由字母、数字、点 '.' 或者感叹号 '!' 
// 
// Related Topics 贪心 字符串 堆（优先队列） 👍 193 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P420StrongPasswordChecker {
    public static void main(String[] args) {
        Solution solution = new P420StrongPasswordChecker().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion

    class Solution {
        public int strongPasswordChecker(String password) {
            int len = password.length();
            boolean upChar = false;
            boolean lowChar = false;
            boolean number = false;
            for (int index = 0; index < len;
                 index++) {
                char c = password.charAt(index);
                if (Character.isDigit(c)) {
                    number = true;
                    continue;
                }
                if (Character.isLowerCase(c)) {
                    lowChar = true;
                    continue;
                }
                if (Character.isUpperCase(c)) {
                    upChar = true;
                }
            }
            int lessChange = (upChar ? 0 : 1) + (lowChar ? 0 : 1) + (number ? 0 : 1);
            if (len < 6) {
                return Math.max(6 - len, lessChange);
            }
            //需要更新的数量
            int changeNumber = 0;
            //上一个char
            char prevChar = ' ';
            //上一个char 连续一样的数量
            int prevCharNumber = 0;
            if (len <= 20) {
                for (int index = 0; index < len; index++) {
                    char c = password.charAt(index);
                    if (c == prevChar) {
                        prevCharNumber++;
                        if (prevCharNumber == 3) {
                            ++changeNumber;
                            prevChar = ' ';
                            prevCharNumber = 0;
                        }
                    } else {
                        prevChar = c;
                        prevCharNumber = 1;
                    }
                }
                return Math.max(changeNumber, lessChange);
            }
            //大于20
            //需要删除的个数
            int deleteNumber = len - 20;
            //连续个数%3 = 2 的字符串数量
            int containMode2 = 0;
            //连续个数%3 = 1 的字符串数量
            int containMode1 = 0;
            //连续个数%3 = 0 的字符串数量
            int containMode0 = 0;
            for (int index = 0; index < len; index++) {
                if (password.charAt(index) == prevChar) {
                    prevCharNumber++;
                } else {
                    //根据上一个连续字符串进行统计
                    if (prevCharNumber > 2) {
                        if (prevCharNumber%3 == 0){
                            containMode0++;
                        }
                        if (prevCharNumber%3 == 1){
                            containMode1++;
                        }
                        if (prevCharNumber%3 == 2){
                            containMode2++;
                        }
                    }
                    changeNumber += prevCharNumber / 3;
                    //更新数据重新计算
                    prevChar = password.charAt(index);
                    prevCharNumber = 1;
                }
            }
            //根据上一个连续字符串进行统计
            if (prevCharNumber > 2) {
                if (prevCharNumber%3 == 0){
                    containMode0++;
                }
                if (prevCharNumber%3 == 1){
                    containMode1++;
                }
                if (prevCharNumber%3 == 2){
                    containMode2++;
                }
            }
            changeNumber += prevCharNumber / 3;
//            System.out.println("containMode2:" + containMode2);
            //汇总
            //消耗deleteNumber 减少 changeNumber 中containMode0的个数。
            // 若deleteNumber能够支持消耗 剩余的changeNumber 是prevCharNumber%3 == 2的情况和prevCharNumber%3 == 1
            int cdnumber = Math.min(deleteNumber, containMode0);
            changeNumber -= cdnumber;
            deleteNumber -= cdnumber;
            //消耗deleteNumber 减少 changeNumber 中containMode1的个数
            // 若deleteNumber能够支持消耗 剩余的changeNumber 是prevCharNumber%3 == 2的情况
            if (deleteNumber>0){
                cdnumber = Math.min(deleteNumber/2, Math.min(changeNumber,containMode1));
                changeNumber -= cdnumber;
                deleteNumber -= cdnumber*2;
            }
            //消耗deleteNumber 减少 changeNumber 剩余数，deleteNumber>2 则 containMode1 containMode0 都被消耗完了
            if (deleteNumber>0){
                cdnumber = Math.min(deleteNumber/3, changeNumber);
                changeNumber -= cdnumber;
//                deleteNumber -= cdnumber*2;
            }
            return Math.max(changeNumber, lessChange) + len - 20;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}