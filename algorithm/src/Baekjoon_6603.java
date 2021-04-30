

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baekjoon_6603 {
	static LinkedList<Integer> list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			if(k==0){
				return;
			}
			list = new LinkedList<Integer>();
			path=new int[6];
			for(int i = 0 ; i < k ; i ++){
				list.add(Integer.parseInt(st.nextToken()));
			}
			dfs(0,0,k);
			System.out.println();
		}
	}
	public static int[] path ;
	public static void dfs(int next,int idx, int k){
		if(next>=6){
			//todo;
			for(int i = 0 ; i < 6 ; i ++){
				int num = list.get(path[i]);
				System.out.print(num+" ");
			}
			System.out.println();
			return;
		}
		for(int i =idx ; i <k ; i ++){
			
			path[next]=i;
			dfs(next+1,i+1,k);
		}
	}
}
