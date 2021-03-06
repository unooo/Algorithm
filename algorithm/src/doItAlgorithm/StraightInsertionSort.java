package doItAlgorithm;


public class StraightInsertionSort {

	public static void main(String[] args){
		
		int[] ary = new int[]{3,2,6,4,7,9,8};
		int n = ary.length;
		int minIndex=0;
		for(int i = 0 ; i < n ; i ++){
			int nowIndex= i;
			for(int j = i-1 ; j >=0 ; j --){
				if(ary[j]>ary[nowIndex]){
					swap(ary,j,nowIndex);
					nowIndex=j;
				}
			}
			for(int temp : ary){
				System.out.print(temp);
				System.out.print(" ");
		
			}	
			System.out.println();
		}
	
		
	}
	
	public static void swap(int[] ary, int a, int b){
		int temp=0;
		temp=ary[a];
		ary[a]=ary[b];
		ary[b]=temp;
		
	}
}
