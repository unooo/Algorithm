package doItAlgorithm;

import java.util.Scanner;

public class CardConvRec {
	
	public static void main(String args[]){
		Scanner sca = new Scanner(System.in); 
		int x =sca.nextInt();
		int r= sca.nextInt();
		char [] d = new char[24];
		for(int i = d.length-1 ; i >=0 ; i --){
			System.out.print( d[i]);
		}
		System.out.println();
		cardConvR(x, r, d);
		
		for(int i = d.length-1 ; i >=0 ; i --){
			System.out.print( d[i]);
		}
	}

	static void cardConvR(int x, int r, char[] d){// ������ x �� r������ ��ȯ�Ͽ� �迭 d�� �Ʒ��ڸ����� �־�ΰ� �ڸ����� ��ȯ�Ѵ�.
		
		int inumer = 0 ;
		int j = 0 ;
		char[] charAry = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g'};
		while(true){
			int k =0;
			k=x%r;
			
			j= x / r;
			d[inumer++]=charAry[k];
			
			if(j<r){
				d[inumer]=charAry[j];
				break;
			}
		}
		
	}
}
