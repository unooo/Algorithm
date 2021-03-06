package topcoder;

public class CorporationSalary {
	
	public static long[] dp ;
	
	public static void main(String[] args){
		//String[] relations = new String[]{"NNYN","NNYN","NNNN","NYYN"};
		//String[] relations = new String[]{"NNNNNN","YNYNNY","YNNNNY","NNNNNN","YNYNNN","YNNYNN"};
		//String[] relations = new String[]{"NNNN","NNNN","NNNN","NNNN"};
		String[] relations = new String[]{"NYNNYN","NNNNNN","NNNNNN","NNYNNN","NNNNNN","NNNYYN"};
		dp = new long[relations.length];
		
		System.out.println(totalSalary(relations));
	}
	
	public static long totalSalary(String[] relations){
		long ret = 0;
		for(int i = 0 ; i < relations.length ; i ++){
			ret+=dfs(i,relations);
		}  
		
		return ret;
	}
	
	public static long dfs(int nowIndex, String[] relations){
		if(dp[nowIndex]!=0){
			
			return dp[nowIndex];
		}		
				
		for(int i = 0 ; i <relations[nowIndex].length(); i ++ ){
			if(relations[nowIndex].charAt(i)=='Y'){
				dp[nowIndex]+=dfs(i,relations);
			}
		}
		
		if(dp[nowIndex]==0){
			
			dp[nowIndex] = 1;
		}		
		
		return dp[nowIndex] ;
		
	}

}
