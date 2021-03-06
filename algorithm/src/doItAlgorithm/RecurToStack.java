package doItAlgorithm;

import java.util.Scanner;

public class RecurToStack {
	
	
	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("정수를 입력하세요.:");
		int x = sc.nextInt();
		recur2(x);
		
	}
	
	public static void recur1(int n ) {
		
		if(n>0){
			recur1(n-1);
			System.out.println(n);
			recur1(n-2);
		}
	}
	
	public static void recur2(int n){
		
		while(n>0){
			recur2(n-1);
			System.out.println(n);
			n=n-2; 
		}
	}
	
	public static void recur3(int n){
		
	}

}
