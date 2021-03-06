package baeckjoon;

import java.util.HashMap;

public class Main extends Asd {
	int a;
	Main(){
		
	}
   public static void main(String[] args){
	   //WeakHashMap<Integer, String> map = new WeakHashMap<>();
	   HashMap<Integer, String> map = new HashMap<>();
	   
       Integer key1 = 1000;
       Integer key2 = 2000;

       map.put(key1, "test a");
       map.put(key2, "test b");

       key1 = null;

       System.gc();  //강제 Garbage Collection

       map.entrySet().stream().forEach(el -> System.out.println(el));
	   
	  
   }
   
   public static void set(int[][] grap){
	   grap[0][0]=1;
	  
	   String str="";
	   for(int i = 0 ; i < Character.MAX_VALUE ; i++){
		   str=str.concat(""+i);// str=str+i; 동일한 코드
	   }
	   
	 
   }
   
   public static void spin(int[][] graph) {

		int N = graph.length;
		int[][] temp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = graph[N-j-1][i];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = temp[i][j];
			}
		}
	}
}