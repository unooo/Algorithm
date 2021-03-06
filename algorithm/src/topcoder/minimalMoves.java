package topcoder;

public class minimalMoves {

	public static int ret = -1;
	public static int cardNum = 0;
	public static int flag [] = new int[200001
	                                    ];

	public static void main(String[] args) {
		cardNum=200000;
		minimalMoves(100000,100000,578);
	
		
		System.out.println(ret);
	}

	public static int minimalMoves(int A, int B, int K) {
		
		dfs(0,A,B,K);
		return ret;
	}

	public static void dfs(int now, int A, int B, int K) {

		if (A == 0) {
			ret = ret!=-1?Math.min(ret,now):now;
			return;
		}

		if (A < 0 || B < 0 || A > cardNum || B > cardNum)
			return;
		for (int i = 0; i <= K; i++) {
		
			int x = i ;
			int y = K-i;
			if(x>A||y>B)
				continue;
			int minusA = -x+y;
			int minusB = x-y;
		
			int newA = A +minusA;
			int newB = B +minusB;
			if(flag[newA]==0||flag[newA]>now){
				flag[newA]=now;
				dfs(now + 1,newA,newB , K);
			}

		}

	}

}
