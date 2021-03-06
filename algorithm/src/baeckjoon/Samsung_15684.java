package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_15684 {

	public static int N;
	public static int H;
	public static int M;
	public static int graph[][];
	public static int ret = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new int[M + 1][N + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());

			graph[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}
		
		int[][] tpGraph = graphCopy(graph);
		dfs(tpGraph,0,1,1);
		if(ret==Integer.MAX_VALUE)
			ret=-1;
		
		System.out.println(ret);
	}

	public static void dfs(int[][] tempGraph, int nowStep,int sI, int sJ) {
		if (nowStep >= 4) {
			return;
		}
		boolean retFlag = checkGraph(tempGraph);
		if (retFlag == true) {
			ret = Math.min(ret, nowStep);
			if (ret == 0)
				return;
		}
		
		for (int i = sI; i < M + 1; i++) {

			for (int j = 0; j < N ; j++) { // J+1�� �ʿ� ����.

				
					
					if(tempGraph[i][j]==1){
						j++;//���ӵ� ���� �Է��� ����
						continue;
					}
					
					if(tempGraph[i][j+1]==1){
						j=j+2;
						continue;
					}
					
					if(nowStep>ret) //�ּҽ��� ������ ������ Ȯ�ε����Ѵ�
						return;//continue ���� �������� ������
					
					
					tempGraph[i][j] = 1;					
					dfs(tempGraph, nowStep + 1,i,j);
					tempGraph[i][j] = 0;	
				
			}
		}
	}

	public static boolean checkGraph(int[][] goalGraph) {
		boolean ret = true;
		int nowY = 1;
		int nowX = 1;

		for (int i = 1; i < N + 1; i++) { // ���μ��������� �� �����ǹ�ȣ�� ����Ǵ��� Ȯ���Ѵ�
			nowX = i;

			for (int j = 1; j < M + 1; j++) {
				nowY=j;
				if (nowX <= N - 1 && goalGraph[nowY][nowX] == 1) {
					nowX++;
				}else if (nowX >= 2 && goalGraph[nowY][nowX - 1] == 1) {
					nowX--;
				}
			}
			
			if(nowX!=i)
				return false;

		}

		return ret;
	}

	public static int[][] graphCopy(int[][] tempGraph) {
		int[][] ret = new int[M + 1][N + 1];
		for (int i = 1; i < M + 1; i++) {

			for (int j = 1; j < N + 1; j++) {
				ret[i][j] = tempGraph[i][j];
			}
		}
	

		return ret;
	}
}
