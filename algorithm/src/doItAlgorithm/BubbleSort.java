package doItAlgorithm;

//������ ������ �������� ���ٴ°Ű� Ȯ������ �پ��ٴ°�.

public class BubbleSort {

	
	//static int x[]= new int[]{6,4,3,7,1,9,8};
	static int x[]=new int[]{22,5,32,11,120,68,70};
	public static void main(String args[]){
		
		for(int i=x.length-2;i>=0;i--){
			for(int j = 0 ;j<=i;j++){
				if(x[j]>x[j+1]){
					swap(x,j,j+1);
				}
			}
			print();
		}
		
	}
	
	public static void swap(int[]x , int indexA, int indexB){
		int temp = 0 ;		
		temp = x[indexA];
		x[indexA]=x[indexB];
		x[indexB]=temp;
	}
	
	public static void print(){
		for(int temp : x)
			System.out.print(temp+"  ");
		System.out.println();
		
	}
}
