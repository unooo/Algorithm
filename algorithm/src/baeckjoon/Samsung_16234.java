package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Samsung_16234 {

	public static int N;
	public static int L;
	public static int R;
	public static int graph[][];
	
	public static int dy[]=new int[]{-1,0,1,0};
	public static int dx[]=new int[]{0,1,0,-1}; //ºÏ µ¿ ³² ¼­ 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}

		}

		int num = 0;
		int graphFlag[][];
		while (true) {
			int flag = 0;
			graphFlag = new int[N][N];

			for (int i = 0; i < N; i++) {

				for (int j = 0; j < N; j++) {

					if (graphFlag[i][j] == 0) {

						Queue<Integer> queueY = new LinkedList<Integer>();
						Queue<Integer> queueX = new LinkedList<Integer>();
						queueY.add(i);
						queueX.add(j);
						graphFlag[i][j]=1;
						
						LinkedList<Integer> aryY= new LinkedList<Integer>();
						LinkedList<Integer> aryX= new LinkedList<Integer>();
						aryY.add(i);
						aryX.add(j);
						
						while (!queueY.isEmpty()) {
							int nowY=queueY.poll();
							int nowX=queueX.poll();
							
							
							for(int k = 0 ; k < 4 ; k ++){
								
								int newY=nowY+dy[k];
								int newX=nowX+dx[k];
								if(newY<0||newX<0||newY>=N||newX>=N)
									continue;
								if(graphFlag[newY][newX]==1)
									continue;
								
								int tpNum=Math.abs(graph[nowY][nowX]-graph[newY][newX]);
								if(tpNum>=L&&tpNum<=R){
									queueY.add(newY);
									queueX.add(newX);
									aryY.add(newY);
									aryX.add(newX);
									graphFlag[newY][newX]=1;
									 flag = 1;
								}
										
								
							}

						}
						int sum=0;
						for(int k = 0 ; k < aryY.size() ; k ++){
							sum+=graph[aryY.get(k)][aryX.get(k)];
						
						}
						int people= sum/aryY.size();
						for(int k = 0 ; k < aryY.size() ; k ++){
							graph[aryY.get(k)][aryX.get(k)]=people;
						
						}
						
					}

				}
			}

			if (flag == 0)
				break;
			num++;
		}
		System.out.println(num);

	}

}
