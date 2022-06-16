//给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 
//所需会议室的最小数量 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[0,30],[5,10],[15,20]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[7,10],[2,4]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 10⁴ 
// 0 <= starti < endi <= 10⁶ 
// 
// Related Topics 贪心 数组 双指针 排序 堆（优先队列） 👍 442 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P253MeetingRoomsIi {
    public static void main(String[] args) {
        Solution solution = new P253MeetingRoomsIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 只要新的begin再end之后就不用累加了，否则累加
         * @param intervals
         * @return
         */
        public int minMeetingRooms(int[][] intervals) {
            if (intervals.length < 2) {
                return 1;
            }
            Integer[] beginArray = new Integer[intervals.length];
            Integer[] endArray = new Integer[intervals.length];
            for (int index = 0; index < intervals.length; index++) {
                beginArray[index] = intervals[index][0];
                endArray[index] = intervals[index][1];
            }
            Arrays.sort(beginArray);
            Arrays.sort(endArray);
            int result = 0;
            for (int beginIndex = 0, endIndex = 0; beginIndex < intervals.length; beginIndex++) {
                if (beginArray[beginIndex]>=endArray[endIndex]){
                    endIndex++;
                    continue;
                }
                result++;
            }
            return result;
        }

        public int minMeetingRooms1(int[][] intervals) {
            if (intervals.length < 2) {
                return 1;
            }
            Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
            PriorityQueue<Integer> resultQueue = new PriorityQueue<>();
            resultQueue.offer(intervals[0][1]);
            for (int index = 1; index < intervals.length; index++) {
                int currentMin = intervals[index][0];
                int currentMax = intervals[index][1];
                if (resultQueue.peek() <= currentMin) {
                    resultQueue.poll();
                }
                resultQueue.offer(currentMax);
            }
            return resultQueue.size();
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}