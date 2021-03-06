package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Greedy_1202 {

	public static int N;
	public static int K;
	public static int info[][];
	public static int[] bag;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		info = new int[N][2];
		bag=new int[K];
		for(int i = 0 ; i < N ; i ++){
			 st = new StringTokenizer(br.readLine());
			 info[i][0]=Integer.parseInt(st.nextToken());
			 info[i][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(info, new Comparator<int[]>() {
            @Override
            public int compare(int[] arg0, int[] arg1) {
                if(arg0[1] == arg1[1]) {
                    return arg1[0] - arg0[0];
                } else {
                    return arg1[1] - arg0[1];
                }
            }
        });    
		
		for(int i = 0 ; i < K ; i ++){
			 st = new StringTokenizer(br.readLine());
			bag[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(bag); 
		
		Queue<Integer> q = new PriorityQueue<>();
		
		int num=0;
		int fullNum=0;
		for(int i = 0 ; i < N ; i ++){
			
		}
		System.out.println(num);
		
	}
}
