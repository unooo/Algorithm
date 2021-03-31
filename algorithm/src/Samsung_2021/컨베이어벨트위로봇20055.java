package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 컨베이어벨트위로봇20055 {
	static int N, K;
	static LinkedList<Node> Belt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Belt = new LinkedList<Node>();
		for (int i = 0; i < 2 * N; i++) {

			Belt.add(new Node(Integer.parseInt(st.nextToken()), false));
		}

		int step = 0;

		while (true) {

			Collections.rotate(Belt, 1);
			Belt.get(N - 1).isRobot = false;
			for (int i = N - 2; i >= 0; i--) {
				if (Belt.get(i).isRobot && !Belt.get(i + 1).isRobot && Belt.get(i + 1).toleration > 0) {
					Belt.get(i).isRobot = false;
					Belt.get(i + 1).isRobot = true;
					Belt.get(i + 1).toleration--;
				}
			}
			Belt.get(N - 1).isRobot = false;
			if (!Belt.getFirst().isRobot && Belt.getFirst().toleration > 0) {
				Belt.getFirst().isRobot = true;
				Belt.getFirst().toleration--;
			}

			long cnt = Belt.stream().filter(node -> {
				if (node.toleration == 0)
					return true;
				return false;
			}).count();
			step++;
			if (cnt >= K)
				break;
		}
		System.out.println(step);

	}

	static class Node {
		int toleration;
		boolean isRobot;

		public Node(int toleration, boolean isRobot) {
			super();
			this.toleration = toleration;
			this.isRobot = isRobot;
		}

	}
}
