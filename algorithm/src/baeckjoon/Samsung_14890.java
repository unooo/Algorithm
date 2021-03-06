package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_14890 {

	
	public static int N;
	public static int L;
	public static int[][] graph;
	public static int[][] graph2;
	public static int[][] flag;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		graph=new int[N][N];
		graph2=new int[N][N];
		
		for(int i = 0 ; i < N ; i ++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++){
				graph2[j][i]=graph[i][j]=Integer.parseInt(st.nextToken());
				
			}
		}
		
		
		int sum=0;
		sum+=check(graph);
		sum+=check(graph2);
		System.out.println(sum);
		
				
		
		
	}
	
	
	public static int check(int[][] testGraph){
		int ret = 0;
		for(int i = 0 ;  i < N  ; i ++){
			int nowLevel=testGraph[i][0];
			flag=new int[N][N];
			int success=0;
			for(int j = 1 ; j < N  ; j ++){
				if(nowLevel-testGraph[i][j]==1){ //내리막길이라면
					int tpFlag=0;
					for(int k = 0 ; k < L ; k ++){
						int newY=i;
						int newX=j+k;
						
						if(newX>=N){ //장외
							tpFlag=1;
							break;
						}if(testGraph[i][j]!=testGraph[newY][newX]){
							//평평하지않아 경사설치불가
							tpFlag=1;
							break;
						}
						if(flag[newY][newX]==1){ //이미 써먹은땅
							tpFlag=1;
							break;
						}
						
						flag[newY][newX]=1;
					}
					if(tpFlag==1)
						break;
					
					
				}else if(nowLevel-testGraph[i][j]==-1){ //오르막길이라면
					int tpFlag=0;
					for(int k = 0 ; k < L ; k ++){
						int newY=i;
						int newX=j-k-1;
						if(newX<0){ //장외
							tpFlag=1;
							break;
						}
						if(flag[newY][newX]==1){ //이미 써먹은땅
							tpFlag=1;
							break;
						}
						
						flag[newY][newX]=1;
						
					}
					if(tpFlag==1)
						break;
					
				}else if(nowLevel-testGraph[i][j]==0){//평평하다면
				
				}else{
					break;
				}
				nowLevel=testGraph[i][j];
				
				if(j==N-1)
					success=1;
			}
			if(success==1){
				ret++;
			}
		}
		return ret;
	}
}
