/*
 * 1. Array Burst Problem
 * https://leetcode.com/discuss/interview-question/625140/Goldman-Sachs-or-OA-2020-or-Array-Burst-Problem-and-Birthday-Party
*/

package leetcode;

import java.util.*;

class ArrayBurstProblem {
	static class Result {
		static class Pair {
			String str;
			int count;

			Pair (String str, int count) {
				this.str = str;
				this.count = count;
			}
		}
		// Using Stack
		public static List<String> getShrunkArray(List<String> inputArray, int burstLength) {
			List<String> outputArray = new ArrayList<>();

			Stack<Pair> stk = new Stack<>();

			int count;
			for (String s : inputArray) {
				if (stk.isEmpty()) {
					stk.push(new Pair(s, 1));
				} else if ((stk.peek().str).equals(s)) {
					count = stk.peek().count + 1;
					stk.push(new Pair(s, count));
					if (count >= burstLength) {
						while (count-- > 0) {
							stk.pop();
						}
					}
				} else {
					stk.push(new Pair(s, 1));
				}
			}
			for (Pair pair : stk) {
				outputArray.add(pair.str);
			}

			return outputArray;
		}

/*
	// By removing elements
	public static List<String> getShrunkArray(List<String> inputArray, int burstLength) {
		int i = 0;
		while (i < inputArray.size()) {
			if (i < 0)
				i = 0;
			int count = 1, j = i+1;
			while (j < inputArray.size() && inputArray.get(j).equals(inputArray.get(i))) {
				count++;
				j++;
			}
			if (count >= burstLength) {
				while (count-- > 0) {
					inputArray.remove(i);
				}
				i-=2;
			}
			i++;
		}
		//System.out.println(inputArray);
		return inputArray;
	}
*/
	}

	public static void main(String[] args) {
		List<String> inputArray = new ArrayList<>();

		inputArray.add("a");
		inputArray.add("b");
		inputArray.add("c");
		inputArray.add("d");
		inputArray.add("e");
		inputArray.add("f");
		inputArray.add("g");
		inputArray.add("f");
		inputArray.add("e");
		inputArray.add("d");
		inputArray.add("c");
		inputArray.add("b");
		inputArray.add("a");
		
		int burstLength = 2;

		System.out.println(new Result().getShrunkArray(inputArray, burstLength));
	}
}