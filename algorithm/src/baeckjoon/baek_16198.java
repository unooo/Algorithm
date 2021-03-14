package baeckjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.print.attribute.standard.MediaSize.Engineering;

public class baek_16198 {
	static int N;
	static LinkedList<Integer> energyList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		energyList = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i< N ; i++){
			energyList.add(Integer.parseInt(st.nextToken()));
		}
		dfs(0,0,N);
		System.out.println(max);
	}
	
	static int max = Integer.MIN_VALUE;
	public static void dfs(int next, int w,int listLen){
		if(next>=N-2){			
			//todo
			max=Math.max(max, w);			
			return;
		}
		
		for(int i = 1 ; i <listLen-1;i++){
			int newW=energyList.get(i-1)*energyList.get(i+1);
			Integer temp=energyList.remove(i);			
			dfs(next+1,w+newW,listLen-1);			
			energyList.add(i, temp);
		}
	}
}
