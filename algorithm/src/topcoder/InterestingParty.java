package topcoder;

import java.util.HashMap;

public class InterestingParty {
	
	public static void main(String[] args){
		/*String[] first = {"fishing","gardening","swimming","fishing"};
		String second[] = {"hunting", "fishing", "fishing","biting"};*/
		/*
		String[] first = {"variety", "diversity","loquacity","courtesy"};
		String second[] = {"talking","speaking","discussion",",meeting"};*/
		
		String[] first = {"snakes","programming","cobra","monty"};
		String second[] = {"python","python","anaconda","python"};
		
		System.out.println(bersInvitation2(first, second));
	}
	
	
	public static int bersInvitation(String[] first, String[] second){
		int res = 0 ;
		int max1[] = new int[first.length];
		int max2[] = new int[first.length];
		int max[] = new int[first.length];
		
		for(int i = 0 ; i < first.length ;  i++){
			
			for(int j = 0 ; j < first.length ; j++){
				if(first[i].equals(first[j])||first[i].equals(second[j]))
					max1[i]++;
				if(second[i].equals(first[j])||second[i].equals(second[j]))
					max2[i]++;
				
			}
			max[i] = Math.max(max1[i], max2[i]);
		}	
		
		for(int i = 0 ; i < max.length ; i ++){
			res= Math.max(res, max[i]);
		}
		
		return res;
	}
	
	public static int bersInvitation2(String[] first, String[] second){
		int res=0;
		HashMap<String,Integer> hm  = new HashMap<String,Integer>();
		for(int i = 0 ; i< first.length ; i ++	){
			if(hm.containsKey(first[i])){
				hm.put(first[i], (hm.get(first[i]))+1);
			}else{
				hm.put(first[i], 1);
			}
			
			if(hm.containsKey(second[i])){
				hm.put(second[i], (hm.get(second[i]))+1);
			}else{
				hm.put(second[i], 1);
			}
		}
		
		for(String key : hm.keySet()){
			System.out.println(key);
			res=Math.max(res, hm.get(key));
		}
		
		
		
		return res;
	}

}
