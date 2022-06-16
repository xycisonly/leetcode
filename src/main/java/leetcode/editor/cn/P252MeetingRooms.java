//给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，请你判
//断一个人是否能够参加这里面的全部会议。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[0,30],[5,10],[15,20]]
//输出：false
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[7,10],[2,4]]
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 0 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti < endi <= 10⁶ 
// 
// Related Topics 数组 排序 👍 124 👎 0

package leetcode.editor.cn;

import java.lang.reflect.Array;
import java.util.*;

public class P252MeetingRooms {
    public static void main(String[] args) {
        Solution solution = new P252MeetingRooms().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canAttendMeetings(int[][] intervals) {
            if (intervals.length < 2) {
                return true;
            }
            Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
            int max = intervals[0][1];
            for (int index = 1; index < intervals.length; index++) {
                if (intervals[index][0] < max) {
                    return false;
                }
                max = intervals[index][1];
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}