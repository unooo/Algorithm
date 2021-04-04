package Gabia;

public class Solution1 {
	//제출코드
	/*
	public static int solution(int n) {

		int ret = 0;
		int piv=5;
		while(true) {
			ret+=n/piv;
			n=n/piv;
			if(n==0)break;
		}
		return ret;
	}
    
    
     public static int solution(int n) {
	         int ret=0;
	        for(int i = 5 ; i <= n ; i*=5) {
	        	ret+=n/i;
	        }
	        return ret;
	    }
        
        
	
	*/
	public static void main(String[] args) {
		 System.out.println(solution(Integer.MAX_VALUE));
		 System.out.println(solution(Integer.MAX_VALUE));
//		int n = Integer.MAX_VALUE;
//		int countNum = 0;
//
//		System.out.println(solution(n, 0));

	}

	public static int solution(int n) {

		int ret = 0;
		int piv=5;
		while(true) {
			ret+=n/piv;
			n=n/piv;
			if(n==0)break;
		}
		return ret;
	}

	public static int solution(double d, int countNum) {
		if ((d / 5) >= 5) {
			countNum += (d / 5);
			return solution((d / 5), countNum);
		} else {
			countNum += (d / 5);
			return countNum;
		}
	}

}
