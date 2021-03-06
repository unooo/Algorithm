package doItAlgorithm;

public class QueenB {
	static int[] pos = new int[8];
	static int[] rowFlag = new int[8];
	static void set(int i){
		
		for(int j = 0 ; j < 8 ;j++){
			pos[i]=j;
			
			if(i!=7){
				set(i+1);
			}else
			print();
	
	
		}
		
	}
	
	public static void main(String[] args){
		set(0);
	}
	
	public static void print(){
		for(int i = 0 ; i < 8 ; i++){
			System.out.print(pos[i]);
		}
		System.out.println();
	}
}
