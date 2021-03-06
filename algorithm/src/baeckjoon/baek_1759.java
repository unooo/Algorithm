package baeckjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class baek_1759 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r= Integer.parseInt(st.nextToken());
		int n=Integer.parseInt(st.nextToken());
		path=new int[r];
		char[] chAry=new char[n];
		 st = new StringTokenizer(br.readLine());
		 for(int i = 0 ; i < n ; i++){
			 chAry[i]=st.nextToken().charAt(0);
		 }
		dfs(0,0,n,r,chAry);
		for(String tp : set){
			System.out.println(tp);
		}
	}
	


	static int[] path ;
	static TreeSet<String> set = new TreeSet<String>();
	public static void dfs(int next, int idx, int n, int r, char[] chAry){
		
		if(next>=r){
			//todo
			LinkedList<Character> list = new LinkedList<Character>();
			for(int tp : path){
				list.add(chAry[tp]);
			}
			Collections.sort(list);
			StringBuffer sb = new StringBuffer();
			for(char tp : list){
				sb.append(tp);
			}
			
			boolean temp1=sb.toString().matches(".*[aeiou]{1,}.*");			
			boolean temp2=sb.toString().matches(".*[^aeiou]{1,}.*[^aeiou]{1,}.*");
			if(temp1&&temp2){
				set.add(sb.toString());
			}
			return;
		}
		
		for(int i = idx; i< n ; i ++){
			path[next]=i;
			dfs(next+1,i+1,n,r,chAry);
		}
	}
}
 