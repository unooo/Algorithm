package LastCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 이차원배열과연산 {

	public static int r, c, k, map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		map = new int[3][3];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int step = 0;
		while (true) {
			int nowR=map.length;
			int nowC=map[0].length;
			if(r<nowR&&c<nowC&&map[r][c]==k){
				System.out.println(step);
				return;
			}
			if(nowR>=nowC){
				//R연산
				calc();
			}else{
				//C연산
				spinLeft();
				calc();
				spinRight();
			}
			
			step++;
			if (step > 100) {
				System.out.println(-1);
				return;
			}
		}

	}
	
	public static void calc(){
		int N = map.length;
		int M = map[0].length;
		LinkedList<Integer>[] master =new LinkedList[N];
		int maxM=0;
		for(int i = 0 ; i <N ; i++ ){
			HashMap<Integer,Integer> hMap = new HashMap<Integer,Integer>();
			for(int j = 0 ; j < M ; j++){
				if(map[i][j]==0)
					continue;
				hMap.merge(map[i][j], 1, (oldV,newV)->oldV+newV);
			}
			LinkedList<Integer> keyList=new LinkedList<>(hMap.keySet());
			Collections.sort(keyList,new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					if(hMap.get(o1).intValue()==hMap.get(o2).intValue()){
						return o1.intValue()-o2.intValue();
					}else{
						return hMap.get(o1).intValue()-hMap.get(o2).intValue();
					}					
				}
			});
			master[i]=new LinkedList<>();
			for(int tp : keyList){
				master[i].add(tp);
				master[i].add(hMap.get(tp));
			}
			maxM=Math.max(maxM, master[i].size());
		}
		
		int newMap[][]=new int[N][maxM];
		for(int i = 0 ; i< N ; i++){
			for(int j = 0 ; j < master[i].size() ; j++){
				newMap[i][j]=master[i].get(j);
			}
		}
		map=newMap;
	}

	public static void spinRight() {
		int N = map.length;
		int M = map[0].length;
		int newMap[][] = new int[M][N];
		int idx = 0;
		for (int j = 0; j < M; j++) {
			for (int i = N - 1; i >= 0; i--) {
				newMap[idx / N][idx % N] = map[i][j];
				idx++;
			}
		}
		map = newMap;
	}

	public static void spinLeft() {
		int N = map.length;
		int M = map[0].length;
		int newMap[][] = new int[M][N];
		int idx = 0;
		for (int j = M - 1; j >= 0; j--) {
			for (int i = 0; i < N; i++) {
				newMap[idx / N][idx % N] = map[i][j];
				idx++;
			}
		}
		map = newMap;
	}
}
