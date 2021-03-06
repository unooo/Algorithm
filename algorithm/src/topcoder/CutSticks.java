package topcoder;

import java.util.Arrays;

public class CutSticks {

	public static void main(String[] args) {
		int[] sticks = new int[] { 1000000000, 1000000000 };
		int C = 2;
		int K = 5;
		double ret = maxKth(sticks, C, K);
		System.out.println(ret);
	}

	public static double maxKth(int[] sticks, int C, int K) {
		double ret = 0;
		if (sticks.length >= K) {
			Arrays.sort(sticks);

			return sticks[sticks.length - 1 - (K - 1)];
		}
		int num= 0;
		int newCut=0;
		double max = sticks[sticks.length - 1];
		double min = sticks[0];
		double mid = (max + min) / 2.0;
		
		while (true) {

			num= 0;
			mid = (max + min) / 2.0;
			
			for(int i = 0 ; i < sticks.length ; i ++){
				if(sticks[i]<mid) continue;
				newCut+=sticks[i]/mid-1;
				num+=sticks[i]/mid;
			}
			double newK =0;
			if(newCut>C){
				 newK = num-(newCut-C);
			}else{
				newK=num;
			}
			
			
			if(newK==K){
				ret=newK;
				break;
			}else if(newK>K){
				max=mid;				
			}else if(newK<K){
				min=mid;
			}

		}

		return ret;
	}

}
