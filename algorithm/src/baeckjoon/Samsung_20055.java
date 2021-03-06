package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Samsung_20055 {

	static int N, K;
	static LinkedList<Space> Belt = new LinkedList<Space>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++)
			Belt.add(new Space(Integer.parseInt(st.nextToken()), false));
		int step = 1;

		// 1. 벨트가 회전한다.
		while (true) {

			Space temp = Belt.pollLast();
			Belt.addFirst(temp);
			Belt.get(N - 1).로봇유무 = false;

			// 2.가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수
			// 없다면 가만히 있는다.
			// 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다

			for (int i = N - 2; i >= 0; i--) {
				if (Belt.get(i).로봇유무==true&&Belt.get(i + 1).로봇유무 == false && Belt.get(i + 1).내구도 >= 1) {
					Belt.get(i + 1).로봇유무 = true;
					Belt.get(i + 1).내구도--;
					Belt.get(i).로봇유무 = false;
				}

			}
			Belt.get(N - 1).로봇유무 = false;
			
			if (Belt.getFirst().로봇유무 == false && Belt.getFirst().내구도 >= 1) {
				Belt.getFirst().로봇유무 = true;
				Belt.getFirst().내구도--;
			}

			long cnt = Belt.stream().filter((space) -> {
				if (space.내구도 <= 0)
					return true;
				else
					return false;
			}).count();
			if (cnt >= K)
				break;
			step++;
		}
		System.out.println(step);
	}

	static class Space {

		int 내구도;
		boolean 로봇유무;

		public Space(int 내구도, boolean 로봇유무) {
			super();
			this.내구도 = 내구도;
			this.로봇유무 = 로봇유무;
		}

	}

}
