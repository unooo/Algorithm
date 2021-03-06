package Programmers;
import java.util.Arrays;

public class Programmers_정렬_k번째수 {
	public static void main(String[] args){
		
		
		int[] array=new int[]{1, 5, 2, 6, 3, 7, 4};
		int[][] commands=new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
		solution(array, commands);
	}

	public static int[] solution(int[] array, int[][] commands) {
        int[] answer = {};
        answer= new int[commands.length];
        for(int tun = 0 ; tun < commands.length ; tun ++){
        	int i = commands[tun][0];
        	int j = commands[tun][1];
        	int k = commands[tun][2];
        	answer[tun]=
        			Arrays.stream(array)
        			.limit(j)
        			.skip(i-1)
        			.sorted()
        			.toArray()[k-1];
        }        
        return answer;
    }
}
