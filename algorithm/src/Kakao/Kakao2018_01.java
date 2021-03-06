package Kakao;

//23분컷.(문제까지치면 30분된듯. 너무긴장해서 개같은실수함. 공백은 편하게 진작에 뛰어쓰기했으면좋았음) or가 set인데 오히려 검출로생각함병신임
public class Kakao2018_01 {

	public static void main(String[] args) {
		/*int n=5;
		int arr1 []= new int[]{9, 20, 28, 18, 11};
		int arr2 []= new int[]{30, 1, 21, 17, 28};*/
		int n=6;
		int arr1 []= new int[]{46, 33, 33 ,22, 31, 50};
		int arr2 []= new int[]{27 ,56, 19, 14, 14, 10};
		String[] ret =solution(n,arr1,arr2);
		for(int i = 0 ; i < n ; i ++){
			System.out.println(ret[i]);
		}
	}

	public static String[] solution(int n, int[] arr1, int[] arr2) {
		
		String[] answer = new String[n];
		String[] answer1 = new String[n];
		String[] answer2 = new String[n];
		calc(n,arr1,answer1);
		calc(n,arr2,answer2);
		
		for(int i = 0 ; i < n ; i ++){
			String str = new String();
			for(int j = 0 ; j < n ; j ++){
				if(answer1[i].charAt(j)=='#'||answer2[i].charAt(j)=='#'){
					str+="#";
				}else
					str+=" ";
			}
			answer[i]=str;
		}
		
		return answer;
	}
	
	public static void calc(int n,int[] ary, String[] map){
		
		for(int i = 0 ; i < n ; i ++){
			
			int num=ary[i];
			int bit=0;
			String str = new String();
			
			for(int j = 0 ; j <n ; j ++){
				bit=num&1;
				switch(bit){
				case 1: 
					str="#"+str;
					break;
				case 0:
					str="."+str;
				}
				num=num>>1;
			}
			map[i]=str;			
			
		}
		
		
	}

}
