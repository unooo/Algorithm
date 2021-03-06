package doItAlgorithm;

import java.util.Scanner;

public class PrimeNumber2 {

	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);
		int limit  = sc.nextInt();
		int primeNumAry[] = new int[limit];
		int counter = 0 ;
		primeNumAry[counter]=2;
		counter++;
		for(int i = 3 ; i < limit ; i+=2){ // Â¦¼ö´Â Á¦¿Ü
			for(int k = 0 ;  k <counter ; k ++){
				if(i%primeNumAry[k]==0){
					break;
				}
				if(k+1==counter){
					System.out.println(i);
					primeNumAry[counter++]=i;	
				}
			}
		}
	}
}
