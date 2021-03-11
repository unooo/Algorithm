package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bake_14226 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		Loop:
		for (N = 2; N <= 1000; N++) {
			Queue<Node> que = new LinkedList<Node>();
			int[][] visit = new int[10000][10000];
			que.add(new Node(0, 1, 1));
			visit[0][1] = 1;
			while (!que.isEmpty()) {
				Node node = que.poll();
				for (int i = 0; i < 3; i++) {
					Node newNode = new Node(node.clipBoard, node.screen, node.step);
					switch (i) {
					case 0:
						newNode.clipBoard = newNode.screen;
						break;
					case 1:
						if (newNode.clipBoard == 0)
							continue;

						newNode.screen += newNode.clipBoard;
						// 4System.out.println(newNode.screen);
						if (newNode.screen == N) {
							//System.out.println(newNode.step);
							continue Loop;
						}
						break;
					case 2:
						if (newNode.screen > 0)
							newNode.screen--;
					}
					if (visit[newNode.clipBoard][newNode.screen] != 0)
						continue;
					newNode.step++;
					que.add(newNode);
					visit[newNode.clipBoard][newNode.screen] = 1;
				}

			}
		}
	}

	static class Node {
		int clipBoard, screen, step;

		public Node(int clipBoard, int screen, int step) {
			super();
			this.clipBoard = clipBoard;
			this.screen = screen;
			this.step = step;
		}

	}
}
