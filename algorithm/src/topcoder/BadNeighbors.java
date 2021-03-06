package topcoder;

public class BadNeighbors {

	public static int ret = 0;
	public static boolean startFlag = false;

	public static boolean beforeFlag = false;
	public static int[] dp ;
	public static void main(String[] args) {
		//int[] donations = new int[] { 10, 3, 2, 5, 7, 8 };
		//int[] donations = new int[]{11,15};
		 int[] donations = new int[]{7,7,7,7,7,7,7,};
		// int[] donations = new int[]{1,2,3,4,5,1,2,3,4,5};
		//maxDonations(donations);
		System.out.println(maxDonations(donations));

	}

	public static int maxDonations(int[] donations) {
		dp = new int[donations.length];
		for(int i = 0 ; i < dp.length ; i++){
			dp[i]=-1;
		}
		return dfs(donations, 0);

	}
	public static int dfs(int[] donations, int nowIndex) {


		

		if (nowIndex >= donations.length) {
			return 0;
		}
		if(dp[nowIndex]!=-1)
			return dp[nowIndex];

		int temp1=0;
		int temp2=0;
		if(beforeFlag==false){
			temp1=dfs(donations, nowIndex + 1);
			beforeFlag=true;
			if (nowIndex == 0) {
				startFlag = true;
			}
			if(startFlag!=true)
			temp2=dfs(donations, nowIndex + 1 )+donations[nowIndex];
			
		}else{
			beforeFlag=false;
			temp1=dfs(donations, nowIndex + 1);
		}
		
		return dp[nowIndex]=Math.max(temp1, temp2);
		
		
	}
/* 
	public static void dfs(int[] donations, int nowIndex, int price) {

		if ((startFlag == true && nowIndex == donations.length)) {
			return;
		}
			ret = Math.max(ret, price);
		

		if (nowIndex >= donations.length) {
			return;
		}

		if(beforeFlag==false){
			dfs(donations, nowIndex + 1, price);
			beforeFlag=true;
			if (nowIndex == 0) {
				startFlag = true;
			}
			dfs(donations, nowIndex + 1, price + donations[nowIndex]);
			
		}else{
			beforeFlag=false;
			dfs(donations, nowIndex + 1, price);
		}
		
		
		
		
	}
	*/
}