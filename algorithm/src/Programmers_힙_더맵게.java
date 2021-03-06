import java.util.PriorityQueue;


public class Programmers_Èü_´õ¸Ê°Ô {
	
	public static void main(String[] args){
		int[] scoville = new int[]{1,10,2,3,9,12};
		solution(scoville, 7);
	}
	
	public static int solution(int[] scoville, int K) {
        int answer = 0;        
        PriorityQueue<Integer> que = new PriorityQueue<Integer>();
      
       for(int i = 0 ; i < scoville.length ; i ++)
        	que.add(scoville[i]);

        while(!que.isEmpty()){
        	int first = que.poll();
        	if(first>=K){
        		return answer;
        	}
        	if(que.isEmpty())
        		break;
        	que.add(first+que.poll()*2);
        	answer++;
        }
        return -1;
    }

}
