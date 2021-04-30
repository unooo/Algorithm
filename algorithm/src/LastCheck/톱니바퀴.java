package LastCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Åé´Ï¹ÙÄû {
	
	static LinkedList<Integer> map[] = new LinkedList[4];
	static int K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		for(int i = 0 ; i < 4 ; i++){
			String str=br.readLine();
			map[i]=new LinkedList<>();
			for(int j = 0 ; j < str.length() ; j++)
				map[i].add(Integer.parseInt(str.charAt(j)+""));
		}
		st= new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		for(int k=0;k<K;k++){
			st= new StringTokenizer(br.readLine());
			int idx=Integer.parseInt(st.nextToken())-1;
			int dir=Integer.parseInt(st.nextToken());
			LinkedList<Integer> newMap[] = mapCopy();
			spin(newMap,idx,dir);
			//checkRight
			int target=idx+1;
			int targetDir=dir*-1;
			while(true){			
				if(target>=4){
					break;
				}
				if(map[target-1].get(2)!=map[target].get(6)){
					spin(newMap,target, targetDir);
				}else{
					break;
				}
				target++;
				targetDir*=-1;
			}
			//checkLeft
			 target=idx-1;
			 targetDir=dir*-1;
			while(true){			
				if(target<0){
					break;
				}
				if(map[target+1].get(6)!=map[target].get(2)){
					spin(newMap,target, targetDir);
				}else{
					break;
				}
				target--;
				targetDir*=-1;
			}
			map=newMap;
		}
		int sum=0;
		sum+=map[0].get(0)==1?1:0;
		sum+=map[1].get(0)==1?2:0;
		sum+=map[2].get(0)==1?4:0;
		sum+=map[3].get(0)==1?8:0;
		System.out.println(sum);
	}
	
	public static LinkedList<Integer>[] mapCopy(){
		LinkedList<Integer> newMap[] = new LinkedList[4];
		for(int i = 0 ; i < 4 ; i++){
			newMap[i]=new LinkedList<>();
			for(int j = 0 ; j < 8 ; j ++){
				newMap[i].add(map[i].get(j));
			}
		}
		return newMap;
	}
	
	public static void spin(LinkedList<Integer> newMap[],int idx, int dir){
		if(dir==1){
			Collections.rotate(newMap[idx], 1);
		}else{
			Collections.rotate(newMap[idx], 7);
		}
	}

}
