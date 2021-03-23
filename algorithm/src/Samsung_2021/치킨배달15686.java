package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 치킨배달15686 {

	static int N,M,map[][];
	static LinkedList<Node> houseList = new LinkedList<Node>();
	static LinkedList<Node> storeList = new LinkedList<Node>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		for(int i  = 0 ; i < N ; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++){
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1)
					houseList.add(new Node(i,j));
				if(map[i][j]==2)
					storeList.add(new Node(i,j));
			}
		}
		path=new int[M];
		dfs(0, storeList.size(), M, 0);
		System.out.println(ret);
	}

	public static int shortestPath(int r1,int c1, int r2, int c2){
		int ret = 0 ;
		
		ret= Math.abs(r1-r2)+Math.abs(c1-c2);
		
		return ret;
	}
	
	public static int solve(){
		int total=0;
		
		for(Node house:houseList){
			int temp=Integer.MAX_VALUE;
			for(int i= 0 ; i < M ; i ++){
				Node store=storeList.get(path[i]);
				temp=Math.min(temp, shortestPath(house.r, house.c, store.r, store.c));
			}
			total+=temp;
		}
		return total;
	}
	
	public static int path[];
	static int ret = Integer.MAX_VALUE;
	public static void dfs(int next, int n , int r , int idx){
		if(next>=r){
			
			//todo
			ret=Math.min(ret, solve());			
			return;
		}
		for(int i = idx ; i < n ; i++){
			path[next]=i;
			dfs(next+1,n,r,i+1);
		}
	}
	static class Node{
		int r,c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
}
