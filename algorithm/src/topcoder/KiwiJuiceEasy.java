package topcoder;

public class KiwiJuiceEasy {
	
	
	public static void main(String args[]){
		/*
		int[] capacities = {20,20};
		int[] bottles = {5,8};
		int[] fromId={0};
		int[] toId={1};
		*/
		/*
		int[] capacities = {10,10};
		int[] bottles = {5,8};
		int[] fromId={0};
		int[] toId={1};
		*/
		/*
		int[] capacities = {30,20,10};
		int[] bottles = {10,5,5};
		int[] fromId={0,1,2};
		int[] toId={1,2,0};
		*/
		/*
		int[] capacities = {14,35,86,58,25,62};
		int[] bottles = {6,34,27,38,9,60};
		int[] fromId={1,2,4,5,3,3,1,0};
		int[] toId={0,1,2,4,2,5,3,1};
		*/
		
		int[] capacities = {700000,800000,900000,1000000};
		int[] bottles = {478478,478478,478478,478478};
		int[] fromId={2,3,2,0,1};
		int[] toId={0,1,1,3,2};
		
		int[] returns = thePouring(capacities, bottles, fromId, toId);
		
		for(int temp:returns){
			System.out.print(temp + " ");
		}
		
	}

	
	public static int[] thePouring(int[] capacities, int[] bottles, int[] fromId, int[] toId){
		//int[] retAry = new int[capacities.length];
		int checkNum = fromId.length;
		
		for(int i = 0 ; i <checkNum ; i ++){
		
			if(bottles[fromId[i]]+bottles[toId[i]]>capacities[toId[i]]){
				bottles[fromId[i]]-=capacities[toId[i]]-bottles[toId[i]];
				bottles[toId[i]]=capacities[toId[i]];
			}else{
				
				bottles[toId[i]]+=bottles[fromId[i]];
				bottles[fromId[i]]=0;
			}	
			
		}		
		
		
		
		return bottles;
	}
}
