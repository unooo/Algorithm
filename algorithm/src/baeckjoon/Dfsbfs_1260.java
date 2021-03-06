package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Dfsbfs_1260 {

	public static ArrayList<Integer>[] graph ;
	static int startNode;
	static int edgeNum;
	static int nodeNum;
	static boolean visitFlag[];
	public static void main (String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sb = new StringTokenizer(br.readLine());
		
		
		 nodeNum =Integer.parseInt(sb.nextToken());
		 edgeNum = Integer.parseInt(sb.nextToken());
		 startNode = Integer.parseInt(sb.nextToken());
		graph = new ArrayList[nodeNum+1];
		visitFlag = new boolean[nodeNum+1];
		for(int i = 0 ; i < nodeNum+1 ; i ++){
			graph[i]= new ArrayList<Integer>();
		}

		for(int i = 0 ; i < edgeNum ; i ++){
			sb= new StringTokenizer(br.readLine());
			int a =Integer.parseInt(sb.nextToken());
			int b = Integer.parseInt(sb.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for(int i = 0 ; i < nodeNum+1 ; i ++){
			Collections.sort(graph[i]);
		}
		
		dfs(startNode);
		System.out.println();
        visitFlag = new boolean[nodeNum+1];
        bfs();
        br.close();


		
	}
	
	public static void bfs(){
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startNode);
		visitFlag[startNode]=true;
		while(!queue.isEmpty()){
			int now = queue.poll();
		
			//현재상태 처리
			System.out.print(now);
			System.out.print(" ");
			 for(Integer temp: graph[now]){
				 
				 if(visitFlag[temp]==true){					 
					 continue;
				 }
				visitFlag[temp]=true;
				queue.add(temp); 				
			 }
		}
	}
	public static void dfs(int Node){
		
		visitFlag[Node]=true;
		System.out.print(Node);
		System.out.print(" ");
		for(int temp: graph[Node]){
			if(visitFlag[temp]==true){
				continue;
			}
			
			dfs(temp);
		}
		
	}
	
}
