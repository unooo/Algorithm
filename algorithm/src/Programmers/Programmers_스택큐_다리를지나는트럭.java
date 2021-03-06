package Programmers;

public class Programmers_스택큐_다리를지나는트럭 {
	
	public static void main(String[] args){
		
		int birdge_length=100;
		int weight=100;
		int[] truck_wieghts= new int[]{10};
		solution(birdge_length, weight, truck_wieghts);
	}

	 public static int solution(int bridge_length, int weight, int[] truck_weights) {
	        int answer = 0;
	        Integer bridge[] = new Integer[bridge_length];
	        
	        int step=1;
	        int passIdx=0;
	        int nowWeight=0;
	        int outNum=0;
	        while(true){
	        	
	        	Integer output= bridge[0];
	        	if(output!=null){
	        		nowWeight-=output;	  
	        		outNum++;
	        	}
	        	if(outNum==truck_weights.length)
	        		break;
	        	for(int i = 0 ; i <bridge_length-1;i++ ){
	        		bridge[i]=bridge[i+1];
	        	}
	        	bridge[bridge_length-1]=null;
	        	if(passIdx<truck_weights.length&&truck_weights[passIdx]+nowWeight<=weight){
	        		nowWeight+=truck_weights[passIdx];
	        		bridge[bridge_length-1]=truck_weights[passIdx];
	        		passIdx++;
	        	}
	        	
	        	step++;
	        }
	        
	        return step;
	    }
}
