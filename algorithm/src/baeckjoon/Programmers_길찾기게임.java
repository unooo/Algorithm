package baeckjoon;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Programmers_길찾기게임 {

	public static void main(String[] args) {
		int[][] nodeinfo = { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 },
				{ 2, 2 } };
		solution(nodeinfo);
	}

	public static int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][];
		PriorityQueue<Node> que = new PriorityQueue<Node>(new Comparator<Node>() {
			public int compare(Node o1, Node o2) {
				return o2.i - o1.i;
			}
		});
		for (int i = 0; i < nodeinfo.length; i++) {
			que.add(new Node(i + 1, nodeinfo[i][1], nodeinfo[i][0]));
		}
		Node head = que.poll();
		while (!que.isEmpty()) {
			Node node = que.poll();
			Node pivot = head;
			while (true) {
				if (pivot.j < node.j) {
					if (pivot.right == null) {
						pivot.right = node;
						break;
					} else {
						pivot = pivot.right;
					}

				} else {
					if (pivot.left == null) {
						pivot.left = node;
						break;
					} else {
						pivot = pivot.left;
					}

				}
			}
		}
		전위순회(head);
		후위순회(head);
		answer[0] = prev.stream().mapToInt(Integer::intValue).toArray();
		answer[1] = after.stream().mapToInt(Integer::intValue).toArray();

		return answer;
	}

	static LinkedList<Integer> prev = new LinkedList<Integer>();
	static LinkedList<Integer> after = new LinkedList<Integer>();

	static void 전위순회(Node node) {

		if (node == null)
			return;
		prev.add(node.id);
		전위순회(node.left);
		전위순회(node.right);

	}

	static void 후위순회(Node node) {

		if (node == null)
			return;

		후위순회(node.left);
		후위순회(node.right);
		after.add(node.id);
	}

	static class Node {
		int id, i, j;
		Node left;
		Node right;

		public Node(int id, int i, int j) {
			super();
			this.id = id;
			this.i = i;
			this.j = j;
		}

	}
}
