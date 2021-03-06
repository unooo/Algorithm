package doItAlgorithm;

public class BinSearch {

	public static void main(String args[]) {

		int[] a = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 25, 30 };//14
		System.out.println(binSearch(a, a.length,1));
	}

	static int binSearch(int[] a, int n, int key) { // ��ڼ��� n �� �迭 a ���� key �� ����
													// ��Ҹ� �����˻��մϴ�.

		int pl = 0;
		int pr = n-1;
		int pivot = (pl + pr) / 2;
		while (true) {

			if (a[pivot] == key)
				return pivot;
			if (pl == pr)
				break;
			if (a[pivot] > key) {
				pr = pivot - 1;
			} else if (a[pivot] < key) {
				pl = pivot + 1;
			}
			pivot = (pl + pr) / 2;



		}
		return -1; // ����
	}

}
