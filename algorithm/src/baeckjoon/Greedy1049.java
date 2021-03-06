package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Greedy1049 {

	public static int N; //기타줄 개수
	public static int M; //브랜드개수
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		int brand[][] = new int[M][2];
		
		
		for(int i = 0 ; i < M ; i ++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 2 ; j ++){
				brand[i][j]=Integer.parseInt(st.nextToken());			
			}
		}
		
		int packageNum = N/6;
		int piceNum = N%6;
		int tot=0;
		int packageMoney=Integer.MAX_VALUE;
		for(int i = 0 ; i < M ; i++){
			for(int j = 0 ; j < 2 ; j ++){
				if(j==0){
					packageMoney=Math.min(packageMoney, brand[i][j]);
				}else{
					packageMoney=Math.min(packageMoney, brand[i][j]*6);
				}
			}
		}
		tot+=packageNum*packageMoney;
		
		int pieceMoney=Integer.MAX_VALUE;
		for(int i = 0 ; i < M ; i++){
			for(int j = 0 ; j < 2 ; j ++){
				if(j==0){
					pieceMoney=Math.min(pieceMoney, brand[i][j]);
				}else{
					pieceMoney=Math.min(pieceMoney, brand[i][j]*piceNum);
				}
			}
		}
		
		tot+=pieceMoney;
		
		System.out.println(tot);
		
	}
	
}
