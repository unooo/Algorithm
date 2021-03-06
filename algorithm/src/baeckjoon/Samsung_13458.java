package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_13458 {

	public static long ret = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int A[] = new int[N];
		int flag[] = new int[1000001];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++){
			A[i]=Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < N ; i++){
			int studentNum = A[i];
	
			if(flag[A[i]]!=0){
				ret+=flag[A[i]];
				continue;
			}
	
			
			int temp=0;
			studentNum-=B;
			temp++;
			while(studentNum>0){
				studentNum-=C;
				temp++;
			}
			flag[A[i]] = temp;
			ret+=temp;
		}
		
		
		
		System.out.println(ret);
	}
}
