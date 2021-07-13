import java.util.*;

class LuxurySort {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		ArrayList<Integer> evens = new ArrayList<>();
		ArrayList<Integer> factors5 = new ArrayList<>();
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (arr[i]%2 == 0) {
				evens.add(arr[i]);
			}
			else if (arr[i]%5 == 0) {
				factors5.add(arr[i]);
			}
			else {
				list.add(arr[i]);
			}
		}

		Collections.sort(evens, Collections.reverseOrder());
		Collections.sort(factors5);

		evens.addAll(factors5);
		evens.addAll(list);

		for(int i = 0; i < n; i++) {
			System.out.print(evens.get(i) + " ");
		}
	}
}