package doItAlgorithm;

import java.util.Scanner;

public class PrimeNumber3 {
	
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int limit = sc.nextInt();
		int counter = 0 ;
		int primeNumAry[] = new int[limit];
		primeNumAry[counter]=2;
		counter++;
		primeNumAry[counter]=3;
		counter++;
		for(int i = 4 ;i < limit ; i ++ ){
		
			for(int j = 0 ; j <counter ; j ++){
				
				if(i%primeNumAry[j]==0)
					break;
				if(primeNumAry[j]*primeNumAry[j]>i){
					System.out.println(i);
					primeNumAry[counter++]=i;
					break;
				}
			}
		}
		
	}

}
