package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bfs_11742 {
	
	public static boolean[] visitFlag;
	public static int nodeNum;
	public static int edgeNum;
	public static ArrayList<Integer>[] graph;
	public static int res;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sb = new StringTokenizer(br.readLine());
		
		nodeNum = Integer.parseInt(sb.nextToken());
		edgeNum = Integer.parseInt(sb.nextToken());
		graph = new ArrayList[nodeNum+1];
		visitFlag = new boolean[nodeNum+1];
		for(int i = 0 ; i < nodeNum+1 ; i ++){
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0 ; i < edgeNum ; i++){
			sb = new StringTokenizer(br.readLine());
			int a =  Integer.parseInt(sb.nextToken());
			int b = Integer.parseInt(sb.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		res=0;	
		for(int i = 1 ; i < nodeNum+1; i++){
			if(visitFlag[i]==false){
				//bfs(i);
				dfs(i);
				res++;
			}
		}
		
		System.out.println(res);
		
	}
	
	public static void bfs(int nowNode){
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(nowNode);
		visitFlag[nowNode]=true;
		while(!queue.isEmpty()){
			int now = queue.poll();
				for(int temp: graph[now]){
					if(visitFlag[temp]!=true){
					queue.add(temp);
					visitFlag[temp]=true;
					}
				}
			

		}
	}
	
	public static void dfs(int nowNode){
	
		
		visitFlag[nowNode]=true;		
		
		for(int temp : graph[nowNode]){
			if(visitFlag[temp]==false){
				//visitFlag[temp]=true;
				dfs(temp);
			}
			
		}
		
	}

}
