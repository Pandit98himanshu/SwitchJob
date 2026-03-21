import java.util.HashMap;

/**
 * 904. Fruit Into Baskets
 * https://leetcode.com/problems/fruit-into-baskets
 */

class Solution {
	/*
	 * TC: O(n)
	 * SC: O(n)	-> storing last-occuring index of all types of fruits
	 */
	public int totalFruit(int[] fruits) {
		int l = 0, numTypes = 0, maxLen = 0;
		int[] fruitType = new int[]{-1, -1};
		HashMap<Integer, Integer> typeIndexMap = new HashMap<>();

		for (int i = 0; i < fruits.length; i++) {
			if (fruits[i] != fruitType[0] && fruits[i] != fruitType[1]) {		// if current fruit is of different type then fruits in 2 baskets
				if (numTypes == 2) {						// if both baskets contains some other kind of fruits
					int minJump = Math.min(typeIndexMap.get(fruitType[0]), typeIndexMap.get(fruitType[1]));
					l = minJump + 1;						// remove fruit from current window which occured most far from current fruit "i"
					int type = typeIndexMap.get(fruitType[0]) == minJump ? 0 : 1;	// get the type of fruit we need to remove from the basket.
					fruitType[type] = fruits[i];
				} else {									// atleast 1 basket is empty
					fruitType[numTypes] = fruits[i];		// put fruit in a basket
					numTypes++;
				}
			}
			typeIndexMap.put(fruits[i], i);					// update the index of current fruit
			maxLen = Math.max(maxLen, i - l + 1);			// keep track of maximum fruits collected
		}
		return maxLen;
	}
}