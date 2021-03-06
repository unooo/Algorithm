package doItAlgorithm;

public class QueenBB {
	static int[] pos = new int[8];
	static int[] rowFlag = new int[8];
	static int[] crossFlagA= new int[15];// i=j
	static int[] crossFlagB= new int[15];//i-j+7;
	static int counter = 0;
	
	
	static void set(int i){
		
		for(int j = 0 ; j < 8 ;j++){
			if(rowFlag[j]==1 || crossFlagA[j+i]==1 || crossFlagB[i-j+7]==1){
				continue;
			}
			
			pos[i]=j;		
			
			if(i!=7){
				rowFlag[j]=crossFlagA[j+i]=crossFlagB[i-j+7]=1;
				set(i+1);
				rowFlag[j]=crossFlagA[j+i]=crossFlagB[i-j+7]=0;
			}else{
				print();
		
				counter++;
			}	
		}		
	}
	
	public static void main(String[] args){
		set(0);
		System.out.println(counter);
	}
	
	public static void print(){
		for(int i = 0 ; i < 8 ; i++){
			System.out.print(pos[i]);
		}
		System.out.println();
	}
}
