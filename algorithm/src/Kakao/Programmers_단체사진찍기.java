package Kakao;

import java.util.Arrays;
import java.util.HashMap;

public class Programmers_단체사진찍기 {
	
	public static void main(String[] args){
		int n = 2 ; 
		String[] data = {"N~F=0", "R~T>2"};
		System.out.println(solution(n, data));
	}

	 public static int solution(int n, String[] data) {
	        int answer = 0;
	        inumer=0;
	        flag = new boolean[8];
	        path= new int[8];
	        dfs(0,8,8,data);
	        return inumer;
	 }
	 
	 static int inumer;
	 public static boolean flag[]; // n개중에 r개를 뽑으므로 n개의 플래그배열생성
	 public static int path[];
	 public static void dfs(int next, int n , int r, String[] data) // 조합인경우 int idx를 추가
	 {
	 	if(next>=r){
	 		//todo
	 		
	 		char manAry[] = new char[]{'A','C','F','G','M','N','R','T'};
	 		HashMap<Character,Integer> map = new HashMap<Character,Integer>();
	 		for(int i = 0 ; i < 8 ; i ++){
	 			map.put(manAry[path[i]], i);
	 		}
	 		
	 		if(check(data, manAry, map))
	 			inumer++;
	 		return;
	 	}
	 	for(int i =0 ; i < n ; i ++){
	 		if(flag[i])
	 			continue;
	 		flag[i]=true;
	 		path[next]=i;
	 		dfs(next+1,n,r,data); 
	 		flag[i]=false;
	 	}
	 }
	 
	 public static boolean check(String[] data,char manAry[],HashMap<Character,Integer> map){
		 boolean ret = true;
		 
		 for (int i = 0 ; i < data.length ; i++){
		 
			 char man1 = data[i].charAt(0);
			 char man2 = data[i].charAt(2);
			 char cond=data[i].charAt(3);
			 int num = Integer.parseInt(data[i].charAt(4)+""); 
			 

			 switch(cond){
			 case '=':
				 if((Math.abs(map.get(man1)-map.get(man2))-1)!=num){					 
					 ret=false;
					 return ret;
				 }
				 ;
				 break;
			 case '>':
				 if(!(Math.abs(map.get(man1)-map.get(man2))-1>num))
				 {
					 ret=false;
					 return ret;
				 }
				 ;
				 break;
			 case '<':
				 if(!(Math.abs(map.get(man1)-map.get(man2))-1<num))
				 {
					 ret=false;
					 return ret;
				 }
				 break;
			 }
			 
		 }
		 return ret;
	 }
	 
}
