package topcoder;

import java.util.LinkedList;
import java.util.Queue;

public class minimalMoves2 {
	public static int[] flag = new int[200001];	
	
	public static void main(String[] args){
		for(int i = 0 ; i < flag.length; i++){
			flag[i]=-1;
		}
		
		System.out.println(minmalMoves(3,0,3));
	}
	
	public static int minmalMoves(int A , int B , int K){
		int ret = 0 ; 
		if(A==0)
			return 0;
		if(A+B<K)return -1;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(A);
		flag[A]=1;
		
		
		while(!queue.isEmpty()){
			int nowA = queue.poll();
 			int nowB= A+B-nowA;
			ret++;
			
			for(int i = 0 ; i <= K ; i ++){
				int x= i;
				int y= K-i;
				int iterA=-x+y;
				int iterB=x-y;
				int newA=nowA+iterA;
				int newB=nowB+iterB;
			
				if(nowA-x<0||nowB-y<0)
					continue;
				if(newA==0){
					return flag[nowA];
				}
				
				if(flag[newA]==-1){
					flag[newA]=flag[nowA]+1;
					queue.add(newA);
				}
				
			}
		}
		
		
		
		
		return -1;
	}

}
