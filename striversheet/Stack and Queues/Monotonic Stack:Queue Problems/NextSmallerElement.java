import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Next Smaller Element
 * https://www.geeksforgeeks.org/problems/immediate-smaller-element1142/1
 */

class Solution {
	static ArrayList<Integer> nextSmallerEle(int[] arr) {
		int n = arr.length;
		ArrayList<Integer> ans = new ArrayList<>();
		Stack<Integer> stk = new Stack<>(); 			// monotonic decreasing stack (top contains lesser value element)

		ans.add(-1);		// no smaller element after last element
		stk.push(arr[n - 1]);
		for (int i = n - 2; i >= 0; i--) {
			while (!stk.isEmpty() && arr[i] <= stk.peek()) // remove greater element from stack until smaller element
				stk.pop();
			ans.add(stk.isEmpty() ? -1 : stk.peek());
			stk.push(arr[i]);
		}
		Collections.reverse(ans);
		return ans;
 	}
}

public class NextSmallerElement {
	public static void main(String[] args) {
		int[] arr = new int[] { 8, 8, 2, 2, 4, 9, 1, 1, 5, 10 };
		System.out.println(Solution.nextSmallerEle(arr));	// [2, 2, 1, 1, 1, 1, -1, -1, -1, -1]
	}
}
