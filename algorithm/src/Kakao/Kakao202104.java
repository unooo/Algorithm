package Kakao;

import java.util.Arrays;

public class Kakao202104 {
	
	public static void main(String[] args){
		int n =6;
		int s=4;
		int a=6;
		int b=2;
		int fares[][]=new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		solution(n, s, a, b, fares);
	} 

	public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        int w[][] = new int[n][n];
        int inf = 100000*201;
        for(int i = 0 ; i < n ; i ++)
        	Arrays.fill(w[i], inf);
        for(int i = 0 ; i < fares.length ; i ++){
        	
        		w[fares[i][0]-1][fares[i][1]-1]=fares[i][2];
        		w[fares[i][1]-1][fares[i][0]-1]=fares[i][2];
        	
        }
        
        for(int i = 0 ; i < n ; i ++)
        	w[i][i]=0;
        
        for(int k=0; k<n; k++)
        {
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    if(w[i][j]>w[i][k]+w[k][j])
                        w[i][j] = w[i][k]+w[k][j];
                }
            }
      
        }
        
        int min = inf;
        
        for(int i = 0 ; i < n ; i ++){
        	int temp=w[s-1][i];
        	temp+=w[i][a-1];
        	temp+=w[i][b-1];
        	min=Math.min(min, temp);
        }
        
        return min;
    }
}
