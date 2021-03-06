package doItAlgorithm;

import java.util.Scanner;

public class PrimeNumber1 {
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int limit = sc.nextInt();
		
		for(int i = 1 ; i <= limit ; i ++){
			
			for(int j = 2 ; j <= i ; j++){
			
				if(i%j==0){					
					break;		
				}
				
				if(j+1==i){
					System.out.println(i);
				}
			}			
		}
		
	}
	
}
