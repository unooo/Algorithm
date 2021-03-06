package Programmers;
import java.util.Arrays;

public class Programmers_깊이너비_네트워크 {

	public static void main(String[] args){
		int[][] computers = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
		solution(3, computers);
	}
	public static int solution(int n, int[][] computers) {
        int answer = 0;
        flag=new int[computers.length];
        int 단지=1;
        for(int i = 0 ; i <n ; i ++){
        	if(flag[i]!=0)
        		continue;
        	flag[i]=단지;
            dfs(i,computers,단지);
        	단지++;
        }
        
        answer=Arrays.stream(flag).max().getAsInt();
        
        return answer;
    }
	
	public static int flag[];
	public static void dfs(int idx,int[][]computers,int 단지){
		

		
		for(int i = 0 ; i < flag.length ; i ++){
			if(idx==i)
				continue;
			if(computers[idx][i]==1){
				if(flag[i]==0){
					flag[i]=단지;
					dfs(i,computers,단지);
				}
				
			}
			
		}
		
	}
}
