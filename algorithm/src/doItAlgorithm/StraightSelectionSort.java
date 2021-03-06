package doItAlgorithm;

public class StraightSelectionSort {
	
	public static void main(String args[]){
		
		int[] ary = new int[]{3,2,4,6,7,8};
		int n = ary.length;
		for(int i = 0 ; i < n ;i ++){
			int minIndex = i;			
			for(int j = i+1; j< n-1 ; j++){
				if(ary[j]<ary[minIndex]){
					minIndex=j;									
				}
			}
			swap(ary,minIndex,i);
		}
		for(int temp : ary){
			System.out.println(temp);	
	
		}		
	}
	
	public static void swap(int[] ary, int a, int b){
		int temp=0;
		temp=ary[a];
		ary[a]=ary[b];
		ary[b]=temp;
		
	}

}
