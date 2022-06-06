//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 104 
// 
// Related Topics 数组 排序 
// 👍 1512 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P56MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new P56MergeIntervals().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public int[][] merge(int[][] intervals) {
            if (Objects.isNull(intervals) || intervals.length < 1) {
                return intervals;
            }
//            Arrays.sort(intervals, new Comparator<int[]>() {
//                public int compare(int[] interval1, int[] interval2) {
//                    return interval1[0] - interval2[0];
//                }
//            });
            Arrays.sort(intervals, (interval1, interval2) -> interval1[0] - interval2[0]);
//            Arrays.sort(intervals, Comparator.comparing(a -> a[0]));
//            Arrays.sort(intervals,Comparator.comparingInt(a->a[0]));
            int resultLength = 1;
            for (int index = 1; index < intervals.length; index++) {
                int[] target = intervals[index];
                int[] merged = intervals[resultLength - 1];
                if (merged[1] < target[0]) {
                    intervals[resultLength] = target;
                    resultLength++;
                    continue;
                }
                merged[1] = Math.max(target[1], merged[1]);
            }
            return Arrays.copyOf(intervals, resultLength);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}