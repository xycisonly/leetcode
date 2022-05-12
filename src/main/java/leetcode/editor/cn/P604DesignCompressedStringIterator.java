//设计并实现一个迭代压缩字符串的数据结构。给定的压缩字符串的形式是，每个字母后面紧跟一个正整数，表示该字母在原始未压缩字符串中出现的次数。 
//
// 设计一个数据结构，它支持如下两种操作： next 和 hasNext。 
//
// 
// next() - 如果原始字符串中仍有未压缩字符，则返回下一个字符，否则返回空格。 
// hasNext() - 如果原始字符串中存在未压缩的的字母，则返回true，否则返回false。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：
//["StringIterator", "next", "next", "next", "next", "next", "next", "hasNext", 
//"next", "hasNext"]
//[["L1e2t1C1o1d1e1"], [], [], [], [], [], [], [], [], []]
//输出：
//[null, "L", "e", "e", "t", "C", "o", true, "d", true]
//
//解释：
//StringIterator stringIterator = new StringIterator("L1e2t1C1o1d1e1");
//stringIterator.next(); // 返回 "L"
//stringIterator.next(); // 返回 "e"
//stringIterator.next(); // 返回 "e"
//stringIterator.next(); // 返回 "t"
//stringIterator.next(); // 返回 "C"
//stringIterator.next(); // 返回 "o"
//stringIterator.hasNext(); // 返回 True
//stringIterator.next(); // 返回 "d"
//stringIterator.hasNext(); // 返回 True 
//
// 
//
// 提示： 
//
// 
// 1 <= compressedString.length <= 1000 
// compressedString 由小写字母、大写字母和数字组成。 
// 在 compressedString 中，单个字符的重复次数在 [1,10 ^9] 范围内。 
// next 和 hasNext 的操作数最多为 100 。 
// 
// Related Topics 设计 数组 哈希表 字符串 迭代器 👍 45 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P604DesignCompressedStringIterator {
    public static void main(String[] args) {

        StringIterator stringIterator = new P604DesignCompressedStringIterator().new StringIterator("");

//         Solution solution = new P604DesignCompressedStringIterator().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class StringIterator {
        private final String source;
        private int index;
        private int number;
        private int numberLength;
        private int numberMax;

        public StringIterator(String compressedString) {
            source = compressedString;
            index = -1;
            number = 1;
            numberMax = 1;
            numberLength = 0;
            if (null == compressedString || compressedString.length() < 2) {
                return;
            }
            update();
        }

        public char next() {
            if (index == -1) {
                return ' ';
            }
            char result = source.charAt(index);
            update();
            return result;
        }

        private void update() {
            if (number < numberMax) {
                number++;
                return;
            }
            index = numberLength + index + 1;
            if (index == source.length()) {
                index = -1;
                return;
            }
            numberMax = 0;
            int numberIndex = index + 1;
            while (numberIndex < source.length()) {
                char newNumber = source.charAt(numberIndex);
                boolean digit = Character.isDigit(newNumber);
                if (!digit) break;
                numberMax = numberMax * 10 + newNumber - '0';
                numberIndex++;
            }
            numberLength = numberIndex - index - 1;
            number = 1;

        }

        public boolean hasNext() {
            return index != -1;
        }
    }

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
//leetcode submit region end(Prohibit modification and deletion)

}