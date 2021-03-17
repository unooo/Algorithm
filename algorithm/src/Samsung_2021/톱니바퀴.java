package Samsung_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Åé´Ï¹ÙÄû {

	static int K;
	static int wheel[][] = new int[4][8];
	static int order[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = str.charAt(j) - '0';
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		order = new int[K][2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			order[i][0] = Integer.parseInt(st.nextToken())-1;
			order[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0 ; i < K ; i++){
			
			int pivot=order[i][0];
			int dir =order[i][1];			
			int spinFlag[][]=new int[4][2];
			
			spinFlag[pivot][0]=1;
			spinFlag[pivot][1]=dir;
			int tpDir=dir;			
			for(int j =pivot;j<=2;j++){
				tpDir=tpDir*-1;
				if(wheel[j][2]!=wheel[j+1][6]){
					spinFlag[j+1][0]=1;
					spinFlag[j+1][1]=tpDir;
				}else
					break;
			}			
			tpDir=dir;
			for(int j =pivot;j>=1;j--){
				tpDir=tpDir*-1;
				if(wheel[j][6]!=wheel[j-1][2]){
					spinFlag[j-1][0]=1;
					spinFlag[j-1][1]=tpDir;
				}else
					break;
			}
			
			for(int t = 0 ; t < 4 ; t++){
				if(spinFlag[t][0]==0)
					continue;
				else{
					spin(t,spinFlag[t][1]);
				}
			}
		}
		int sum=0;
		if(wheel[0][0]==1)
			sum+=1;

		if(wheel[1][0]==1)
			sum+=2;

		if(wheel[2][0]==1)
			sum+=4;

		if(wheel[3][0]==1)
			sum+=8;
		System.out.println(sum);
	}
	
	public static void spin(int idx, int dir){
		
		 if(dir==1){
			 int temp=wheel[idx][7];
			 for(int i =7;i>=1;i--){
				 wheel[idx][i]=wheel[idx][i-1];
			 }
			 wheel[idx][0]=temp;
		 }else{
			 int temp=wheel[idx][0];
			 for(int i =0;i<=6;i++){
				 wheel[idx][i]=wheel[idx][i+1];
			 }
			 wheel[idx][7]=temp;
		 }
	}
}
