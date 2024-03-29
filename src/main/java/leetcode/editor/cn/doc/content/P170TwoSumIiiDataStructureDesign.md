<p>设计一个接收整数流的数据结构，该数据结构支持检查是否存在两数之和等于特定值。</p>

<p>实现 <code>TwoSum</code> 类：</p>

<ul>
	<li><code>TwoSum()</code> 使用空数组初始化 <code>TwoSum</code> 对象</li>
	<li><code>void add(int number)</code> 向数据结构添加一个数 <code>number</code></li>
	<li><code>boolean find(int value)</code> 寻找数据结构中是否存在一对整数，使得两数之和与给定的值相等。如果存在，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["TwoSum", "add", "add", "add", "find", "find"]
[[], [1], [3], [5], [4], [7]]
<strong>输出：</strong>
[null, null, null, null, true, false]

<strong>解释：</strong>
TwoSum twoSum = new TwoSum();
twoSum.add(1);   // [] --&gt; [1]
twoSum.add(3);   // [1] --&gt; [1,3]
twoSum.add(5);   // [1,3] --&gt; [1,3,5]
twoSum.find(4);  // 1 + 3 = 4，返回 true
twoSum.find(7);  // 没有两个整数加起来等于 7 ，返回 false</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-10<sup>5</sup> &lt;= number &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= value &lt;= 2<sup>31</sup> - 1</code></li>
	<li>最多调用 <code>10<sup>4</sup></code> 次 <code>add</code> 和 <code>find</code></li>
</ul>
<div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>哈希表</li><li>双指针</li><li>数据流</li></div></div><br><div><li>👍 72</li><li>👎 0</li></div>