package leetcode;

import java.util.*;

public class TopKFrequentElements {


    /**
     * <strong>PriorityQueue</strong> - beats 98.81 %
     */
    public int[] topKFrequent(int[] nums, int k) {
        // store min & max element from "nums" array
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();

        // store freq of nums[i]
        int[] freq = new int[max - min + 1];
        for (int i : nums)
            freq[i - min]++;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int j : freq) {
            if (j != 0) {
                if (minHeap.size() < k)
                    minHeap.add(j);
                else if (!minHeap.isEmpty() && j > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.add(j);
                }
            }
        }

        int[] topK = new int[k];
        int index = 0;

        for (int i = 0; i < freq.length; ++i) {
            if (!minHeap.isEmpty() && freq[i] >= minHeap.peek()) {
                topK[index++] = i + min;
                if (index == k)
                    return topK;
            }
        }
        return topK;
    }

    /**
     * <strong>PriorityQueue</strong> - beats 62.68 %
     */
    public int[] topKFrequent1(int[] nums, int k) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

        // 0 -> number/ value
        // 1 -> its' frequency
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        freqMap.forEach((num, freq) -> {
            minHeap.add(new int[]{num, freq});
            if (minHeap.size() > k)
                minHeap.poll();
        });
        // final storing of values into array
        int[] topK = new int[k];
        int i = 0;
        while (!minHeap.isEmpty())
            topK[i++] = minHeap.poll()[0];

        return topK;
    }

    /**
     * <strong>ArrayList</strong> - beats 43.52 %
     */
    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums)        // storing freq of nums
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

        // store nums[i] in array based on their freq
        // freq ∈ [0, nums.length], therefore we need list of "nums.length + 1"
        ArrayList<Integer>[] list = new ArrayList[nums.length + 1];
        freqMap.forEach((num, freq) -> {
            ArrayList<Integer> temp = list[freq];
            if (temp == null)
                temp = new ArrayList<>();
            temp.add(num);
            list[freq] = temp;
        });

        // final storing of values into array
        int[] topK = new int[k];
        int index = 0;

        for (int i = list.length - 1; i >= 0; i--) {
            if (list[i] == null) continue;
            for (int num : list[i]) {       // store nums of freq "i"
                topK[index++] = num;
                if (index == k)     // we need only k values
                    return topK;
            }
        }
        return topK;
    }

    private static void print(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }

    public static void main(String[] args) {
        int[] nums = {1};
        int k = 1;
        print(new TopKFrequentElements().topKFrequent(nums, k));
    }
}
