package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Samsung_17140 {
	
	//1. compareTo 이해
	//2. 2차원배열을 90도 회전
	//3. 가로 확인 세로확인은 ij교환
	//4. i j 교환 배열이차원 로직 헷갈림
	//5. map 사용시 iterator
	
	public static int R;
	public static int C;
	public static int K;
	public static int [][]graph=new int[3][3];
	public static int nowR;
	public static int nowC;
	public static HashMap<Integer,Integer> flag = new HashMap<Integer,Integer>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken())-1;
		C=Integer.parseInt(st.nextToken())-1;
		K=Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < 3 ; i ++){
			st= new StringTokenizer(br.readLine());
			for(int j = 0 ; j <3 ; j ++){
				graph[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		 nowR=3;
		 nowC=3;
		int num=0;
		while(true){
			if(nowR-1>=R&&nowC-1>=C){
				if(graph[R][C]==K){
					break;
				}
			}
			if(num>100){
				num=-1;
				break;
			}
			
			if(nowR>=nowC){
				
				graph=setting(nowR,nowC);
				graph=spin(graph);
				graph=spin(graph);
				
			}else{
				graph=spin(graph);
				graph=setting(nowR,nowC);
				graph=spin(graph);
			}
			
				
			num++;
		}
		
		
		System.out.println(num);
		
	}
	
	public static int[][] setting(int nowR, int nowC){
		int[][] ret  = new int[nowR][];
		int temp[] ;
		
		for(int i = 0 ; i < nowR ; i ++){
			flag=new HashMap<Integer,Integer>();
			int passFlag=0;
			for(int j = 0 ; j <nowC ; j ++){
				
				int key=graph[i][j];
				if(key==0){
				
					continue;
				}
				Integer value=flag.get(key);
				if(value==null){
					value=1;
				}else{
					value++;
				}
				flag.put(key, value);
			}
			LinkedList<MyObj> list = new LinkedList<MyObj>();
		
			for(Iterator<Integer> iter=flag.keySet().iterator();iter.hasNext();){
				int iterKey=iter.next();
				int iterValue=flag.get(iterKey);
				list.add(new MyObj(iterKey,iterValue));
			}
			Collections.sort(list);
			temp= new int[list.size()*2];
			int size=list.size();
			for(int j = 0 ; j < size; j ++){
				temp[j*2]=list.get(j).key;
				temp[j*2+1]=list.get(j).value;
			}
			ret[i]=temp;
			Samsung_17140.nowC=Math.max(Samsung_17140.nowC, size*2);
		}
		return ret;
	}
	
	static int[][] spin(int[][] mGraph){
		
		int[][] ret = new int[nowC][nowR];
		
		for(int i = 0 ; i < nowR ; i ++){
			for(int j = 0 ; j < mGraph[i].length ; j ++){// 이부분 센스가 중요했음.
				ret[j][i]=mGraph[i][j];
			}
		}
		int temp=nowR;
		nowR=nowC;
		nowC=temp;
		
		return ret;
	}
	
	static class MyObj implements Comparable<MyObj>{
		int key;
		int value;
		MyObj(int key,int value){
			this.key=key;
			this.value=value;
		}
		
		@Override
		public int compareTo(MyObj o) {
			if(this.value>o.value){
				return 1;
			}else if(this.value==o.value){
				if(this.key>o.key){
					return 1;
				}else{
					return-1;
				}
			}
			return -1;
		}
	}
}
