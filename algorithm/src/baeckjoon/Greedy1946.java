package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
//1931회의실배정과비교

public class Greedy1946 {

	public static int T,N;
	public static int[][] caseGrp;
	
	//public static ArrayList<Pair> pairAry = new ArrayList<Pair>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T=Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < T ; i ++){
			st = new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			caseGrp= new int[N][2];
			for(int j = 0 ; j < N ; j ++){
				st = new StringTokenizer(br.readLine());
				caseGrp[j][0]=Integer.parseInt(st.nextToken());
				caseGrp[j][1]=Integer.parseInt(st.nextToken());
				//pairAry.add(new Pair(caseGrp[j][0],caseGrp[j][1]));
				
			}
			
			//44줄까지를 이중포문이아닌 (n제곱횟수회전) 아래와같이 바꿈
			quickSort(0,N-1,caseGrp,0);
			int num=N;
			int min=caseGrp[0][1];
			for(int j = 1 ; j < N ; j++){
				if(caseGrp[j][1]>min){
					num--;
					
				}else{
					min=caseGrp[j][1];
				}
			}
			System.out.println(num);
			
		}
		
	}
	
	//생각해보면 애초에 이렇게 짜는게 collections.sort한 다음에 처리하는것과다를게없지..
	public static void quickSort(int left, int right, int[][] ary, int k){
		
		int pl=left;
		int pr=right;
		int pv = ary[(left+right)/2][k];
		do{
			while(ary[pl][k]<pv) pl++;
			while(ary[pr][k]>pv) pr--;
			if(pl<=pr){
				int temp=ary[pl][0];
				ary[pl][0]=ary[pr][0];
				ary[pr][0]=temp;
				
				temp=ary[pl][1];
				ary[pl][1]=ary[pr][1];
				ary[pr][1]=temp;
				pl++;
				pr--;
			}				
		
		}while(pl<=pr);
		
		if(left<pr) quickSort(left,pr,ary,k);
		if(pl<right) quickSort(pl,right,ary,k);
	}
	static class Pair{
		int y;
		int x;
		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
