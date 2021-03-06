package Programmers;
import java.util.HashSet;
import java.util.TreeSet;

public class Programmers_데모테스트 {

	public static void main(String[] args){
		int[][] v = new int[][]{{1, 4}, {3, 4}, {3, 10}};
		solution(v);
	}
	
	public static int[] solution(int[][] v) {
        int[] answer = {};
        
        TreeSet<Integer> posX = new TreeSet<Integer>();
        TreeSet<Integer> posY = new TreeSet<Integer>();
        HashSet<String> pos = new HashSet<String>();
        
        for(int i = 0 ; i < v.length ; i ++){
        	int tempX=v[i][0];
        	int tempY=v[i][1];
        	posX.add(tempX);
        	posY.add(tempY);
        	pos.add(new String(tempX+""+tempY));
        }
        
        for(Integer tempX :posX){
        	for(Integer tempY:posY){
        		String tempStr=new String(tempX+""+tempY);
        		boolean flag = pos.contains(tempStr);
        		if(!flag){
        			answer=new int[]{tempX,tempY};
        		}
        	}
        }


        return answer;
    }
}
