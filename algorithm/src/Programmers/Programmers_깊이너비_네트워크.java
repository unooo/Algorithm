package Programmers;
import java.util.Arrays;

public class Programmers_���̳ʺ�_��Ʈ��ũ {

	public static void main(String[] args){
		int[][] computers = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
		solution(3, computers);
	}
	public static int solution(int n, int[][] computers) {
        int answer = 0;
        flag=new int[computers.length];
        int ����=1;
        for(int i = 0 ; i <n ; i ++){
        	if(flag[i]!=0)
        		continue;
        	flag[i]=����;
            dfs(i,computers,����);
        	����++;
        }
        
        answer=Arrays.stream(flag).max().getAsInt();
        
        return answer;
    }
	
	public static int flag[];
	public static void dfs(int idx,int[][]computers,int ����){
		

		
		for(int i = 0 ; i < flag.length ; i ++){
			if(idx==i)
				continue;
			if(computers[idx][i]==1){
				if(flag[i]==0){
					flag[i]=����;
					dfs(i,computers,����);
				}
				
			}
			
		}
		
	}
}
