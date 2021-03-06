package Programmers;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class Programmers_스택큐_프린터 {
	
	public static void main(String[] args){
		int location=2;
		int priorities[] = new int[]{2,1,3,2};
		solution(priorities, location);
	}

	public static  int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Node> que = new LinkedList();
        TreeMap<Integer, Integer> map = new TreeMap(Collections.reverseOrder());
        for(int i = 0 ; i < priorities.length ; i ++){
        	que.add(new Node(priorities[i],i));
        	Integer val = map.get(priorities[i]);
        	if(val==null){
        		map.put(priorities[i], 1);
        	}else{
        		map.put(priorities[i], val+1);
        	}
        }
        int step=0;
        for(Integer pri : map.keySet()){
        	int val = map.get(pri);
        	while(true){
        		step++;
        		Node node = que.poll();
            	if(node.pri!=pri){
            		que.add(node);step--;
            		continue;
            	}
            	
            	if(node.idx==location){
            		return step; 
            	}
            	val--;
            	if(val==0)
            		break;

        	}
        	
        }
        
      
        return answer;
    }
	
	static class Node {
		int pri, idx;

		public Node(int pri, int idx) {
			super();
			this.pri = pri;
			this.idx = idx;
		}
	}
}
