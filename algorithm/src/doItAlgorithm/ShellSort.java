package doItAlgorithm;

public class ShellSort {
	//static int[] ary = new int[]{10,3,2,6,4,7,9,8};
	static int[] ary = new int[]{7,22,5,11,32,120,68,70};
	public static void main(String[] args){
		
		
		int n = ary.length;
		for ( int h = n/2 ;  h > 0 ; h/=2){
			for(int i = h ; i < n ; i++){
				for(int j = i ; j>=h;j-=h){
					if(ary[j]<ary[j-h])
						swap(ary,j,j-h);
				}
			}			
		}
		print();
	}
	
	
	public static void swap(int[] ary, int a, int b){
		int temp=0;
		temp=ary[a];                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
		ary[a]=ary[b];
		ary[b]=temp;
		
	}
	
	public static void print(){
		for(int temp : ary)
			System.out.print(temp+"  ");
		System.out.println();
		
	}
}
