package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
//��� 1. �����÷��׿� �������÷��� �ΰ��� ���� 
//���2. �������� ��������� �������÷��׸� ���ξ��µ� �̶� ���ǵ�����ʹ� �ٸ��� ���� 4�� �̻��� �䱸�ϱ⶧���� �ʼ������� ����Ѵ�. �׳� �����÷��� �ϳ��� -1���� ���ڴٸ��� �������� �ļ����� ������ ����������  �����׷����� ���� ���ﰪ�� -1 �ϸ� ���ͷ��ǿ��� �������ִ�
//���3. �� �״�� interuption ������ ��������. �����ѿ並 -1 ó���Ҷ� �� �ѿ䰪�� ���� �������ʰ� �׷������� ��� ������ -1ó���س��� ���ͷ��߻������ϴ�.
//���4. ���������ȿ����� ���Ϲ�(bfs) �� ���¸� ��������. ���ǵ������ ����
//���5. �����߿�! �����÷��׸� ��� üũ�صѰ��̳� -> poll�Ҵ� Ȥ�� add�Ҷ� �� �α��������� üũ����(�����ϳ���1�ؼ��ϸ��)
public class SamsungEx_11559 {

	public static int[][] graph = new int[12][6];
	public static int stack = 0;
	public static int dy[] = new int[] { 1, -1, 0, 0 };
	public static int dx[] = new int[] { 0, 0, 1, -1 };
	public static int[][] visitFlag = new int[12][6]; 
	public static int[][] removeFlag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st;

		for (int i = 0; i < 12; i++) {
			st = new String(br.readLine());
			for (int j = 0; j < 6; j++) {
				char ch = st.charAt(j);
				switch (ch) {
				case 'R':
					graph[i][j] = 1;
					break;
				case 'G':
				
					
					graph[i][j] = 2;
					break;
				case 'B':
					graph[i][j] = 3;
					break;
				case 'P':
					graph[i][j] = 4;
					break;
				case 'Y':
					graph[i][j] = 5;
					break;
				}
			}
		}

		int ret = 0;
		while (true) {
			ret = bfs();
			if (ret != 4) {
				break;
			}
			renewalGraph();
			stack++;
		}
		System.out.println(stack);
	}
	
	public static void renewalGraph(){ // �� �Լ������Ѱ��
		//1) ������ ��켱 ȸ���� �ͼ��� ����
		//2) ��ĭ�� �и��� �κп����� ������ �� ����
		//2-1)�ǾƷ����� Ȥ�� ���������� ��� ���������ó���Ұ��ΰ�
		//2-2)0������ó��
		for(int i = 0 ; i < 6 ; i ++){
			for(int j = 0 ; j <12 ; j ++){
				
				if(removeFlag[j][i]==1){
				
					for(int k = j ; k >=0 ; k --){
						if(k==0){//�� �������� ��� ��� Ʋ����
							graph[k][i]=0;
							continue;
						}
						graph[k][i]=graph[k-1][i];
					}
					
					
				}
				
			}
		}
	}

	public static int[][] makeVisitFlag(int[][] flag) {
		int[][] ret = new int[12][6];

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (flag[i][j] == 0)
					ret[i][j] = 1;
			}
		}
		return ret;
	}

	public static int bfs() {

		Queue<Pair> queue = new LinkedList<Pair>();
		visitFlag = makeVisitFlag(graph);
		int ret = 0;
		int sameNum = 0;
		 removeFlag = new int[12][6];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) { //���������ȿ��� ���Ϲ��� ������ �������̴�(�Ｚ ���ǵ�����)
				if (visitFlag[i][j] == 0) {
					queue.add(new Pair(i, j));
					//visitFlag[i][j] = 1;
					sameNum=1;
					int value=graph[i][j];
					int [][]removeFlagCpy = new int[12][6];
					for(int a = 0 ; a < 12 ; a ++){
						for(int b = 0 ; b < 6 ; b++){
							removeFlagCpy[a][b]=removeFlag[a][b];
						}
					}
							
					while (!queue.isEmpty()) {
						Pair pair = queue.poll();
						int startY = pair.y;
						int startX = pair.x;
						visitFlag[startY][startX] = 1;// �� ���� ��ġ�� �߿� //visitFlag�� removeFlag�� �и�����(������ visitFlag�� -1�� �Ϸ������� �׷����ϴ°�� -1�� 1�ιٱʹµ��� �����������߻�)
						int nowY = pair.y;
						int nowX = pair.x;

						int nextY = pair.y;
						int nextX = pair.x;

						for (int k = 0; k < 4; k++) {
							nextY = nowY + dy[k];
							nextX = nowX + dx[k];
							if (nextY < 0 || nextX < 0 || nextY >= 12 || nextX >= 6) {
								continue;
							}

							if (visitFlag[nextY][nextX] == 1)
								continue;

							if (value == graph[nextY][nextX]) {
								queue.add(new Pair(nextY, nextX));
								//visitFlag[startY][startX] = 1;
								//visitFlag[nextY][nextX] = 1; // �̰� ������ //���°Ը���
								
								//graph[startY][startX] = -1; // ���� 4�� �̸��̶�� �ٽ� �������� ���������ϴµ� �װԹ���->�̹����ƴϸ� �����÷��׿� �������÷��׸� ���� ������ ����.
								//graph[nextY][nextX] = -1;
								//visitFlag[startY][startX] = -1; 
								//visitFlag[nextY][nextX] = 1;
								sameNum++;
								removeFlagCpy[startY][startX]=1;
								removeFlagCpy[nextY][nextX]=1;
							}
						}
						

					}
					if(sameNum>=4){
						
						removeFlag=removeFlagCpy;
						ret=4;
					}
				}
			}
		}

		return ret;

	}

	static class Pair {
		int y;
		int x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

}
