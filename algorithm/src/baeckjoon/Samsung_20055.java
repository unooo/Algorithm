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

		// 1. ��Ʈ�� ȸ���Ѵ�.
		while (true) {

			Space temp = Belt.pollLast();
			Belt.addFirst(temp);
			Belt.get(N - 1).�κ����� = false;

			// 2.���� ���� ��Ʈ�� �ö� �κ�����, ��Ʈ�� ȸ���ϴ� �������� �� ĭ �̵��� �� �ִٸ� �̵��Ѵ�. ���� �̵��� ��
			// ���ٸ� ������ �ִ´�.
			// �κ��� �̵��ϱ� ���ؼ��� �̵��Ϸ��� ĭ�� �κ��� ������, �� ĭ�� �������� 1 �̻� ���� �־�� �Ѵ�

			for (int i = N - 2; i >= 0; i--) {
				if (Belt.get(i).�κ�����==true&&Belt.get(i + 1).�κ����� == false && Belt.get(i + 1).������ >= 1) {
					Belt.get(i + 1).�κ����� = true;
					Belt.get(i + 1).������--;
					Belt.get(i).�κ����� = false;
				}

			}
			Belt.get(N - 1).�κ����� = false;
			
			if (Belt.getFirst().�κ����� == false && Belt.getFirst().������ >= 1) {
				Belt.getFirst().�κ����� = true;
				Belt.getFirst().������--;
			}

			long cnt = Belt.stream().filter((space) -> {
				if (space.������ <= 0)
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

		int ������;
		boolean �κ�����;

		public Space(int ������, boolean �κ�����) {
			super();
			this.������ = ������;
			this.�κ����� = �κ�����;
		}

	}

}
