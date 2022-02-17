package standard;

import java.util.Arrays;
import java.util.Comparator;

//Giving wrong answer
class _FractionalKnapsack {
	final static int n = 3;
	static class Item {
		int index, val, wt;
		Double costPerWt;
		
		public Item(int index, int val, int wt) {
			this.index = index;
			this.val = val;
			this.wt = wt;
			costPerWt = (double) (val/wt);
		}
	}
	public static double getMaxValue(int val[], int wt[], int W) {
		Item iVal[] = new Item[n];
		for(int i = 0; i < n; i++)
			iVal[i] = new Item(i, val[i], wt[i]);
		Arrays.sort(iVal, new Comparator<Item>() {
			public int compare(Item i1, Item i2) {
				return i1.costPerWt.compareTo(i1.costPerWt);
			}
		});
		double totalValue = 0d;
		for(Item i : iVal) {
			int currVal = (int) i.val;
			int currWt = (int) i.wt;
			if(W - currWt >= 0) {
				W -= currWt;
				totalValue += currVal;
			} else {
				double fraction = W/currWt;
				totalValue += currVal*fraction;
				W -= currWt*fraction;
				break;
			}
		}
		return totalValue;
	}
	public static void main(String[] args) {
		int val[] = { 60, 100, 120 };
		int wt[] = { 10, 20, 30 };
		int W = 50;
		System.out.println(getMaxValue(val, wt, W));
	}
}