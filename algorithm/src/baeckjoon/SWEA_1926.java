package baeckjoon;

public class SWEA_1926 {

	
	public static void main(String[] args){
		
		int N=100;
		
		
		for(int i = 1 ; i <= N ; i ++){
		
			int temp=i;
			int digit=10;
			int clap=0;
			while(true){
				int checkNum=temp%digit;
				if(checkNum!=0&&checkNum%3==0){
					clap++;
				
				}
				temp=temp/digit;
				if(temp==0)
					break;
			}
			
			if(clap==0){
				System.out.print(i);
			}else{
				for(int k = 0 ; k < clap ; k++)
					System.out.print("-");
			}
			System.out.print(" ");
		   
		}
		
	}
}
