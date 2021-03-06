package Programmers;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class Programmers_스택큐_주식가격 {

	
	public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<Node> stack = new LinkedBlockingDeque<Node>();
        
        for(int i = 0 ; i < prices.length ; i++){
        	Node node = new Node(i,prices[i]);
        	
        	while(true){
        		Node temp = stack.peek();
            	if(temp==null){
            		stack.push(node);
            		break;
            	}
            	
            	if(temp.price>node.price){
            		stack.pop();
            		answer[temp.idx]=node.idx-temp.idx;
            	}else{
            		stack.push(node);
            		break;
            	}
            	
        	}
        }
        
        stack.stream().forEach((ele)->{
        	answer[ele.idx]=prices.length-1-ele.idx;
        });
        
        return answer;
    }
	
	static class Node{
		int idx, price;

		public Node(int idx, int price) {
			super();
			this.idx = idx;
			this.price = price;
		}
		
	}
	
	public static void main(String[] args){
		int[] prices = new int[]{1,2,3,2,3};
		solution(prices);
	}
}
