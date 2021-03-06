package topcoder2;

public class InterestingParty {

	public static void main(String[] args){
		
		
		String[] first={"fishing","gardening","swimming","fishing"};
		String[] second={"hunting","fishing","fishing","biting"};
		System.out.println(bestInvitation(first, second));
	}
	
	public static int bestInvitation(String[] first, String[] second){
		
		int ret = 0 ;
		for(int i = 0 ; i < first.length ; i ++){
			int temp1 = 0 ;
			int temp2 = 0 ;
			for(int j = 0 ; j <first.length ; j ++){
				if(first[i]==first[j]||first[i]==second[j]){
					temp1++;
				}
				
				if(second[i]==first[j]||second[i]==second[j]){
					temp2++;
				}				
				
			}	
			ret=Math.max(ret, temp1);
			ret=Math.max(ret, temp2);
		}		
		return ret;
	}
}
