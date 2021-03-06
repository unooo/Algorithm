package topcoder;

public class InterestingDigits {
	
	public static void main(String args[]){	
		digits(30);
		
		}

	public static int[] digits(int base){
		char chAry[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		int[] answer = new int[30];
		int iter=0;
		for(int i = 2 ; i <base ; i ++){
			boolean flag = true;
			for(int j =1 ; j < 9999 ; j ++){
				int res[] = new int[1024];
				int digit = changeNumber(j*i, base, res);
				int total=0;
				for(int k = digit-1 ; k >=0 ; k--){
					total+=res[k];
				}
				if(total%i!=0){
					flag=false;
					break;
				}
			}
			if(flag==true){
				 
				answer[iter]=i;
				iter++;
			}else{ //플레그가 폴스인경우
				flag=true;
			}
		}
		for(int i = 0 ; i < iter ; i ++){
			System.out.println(answer[i]);
		}
		
		return answer;
	}
	
	public static int changeNumber(int num, int base,int[] res){	
		
		 int a = num;
		 int b = 0;
		 int i =0;		 
		 while(true){
			 b=a%base;
			 res[i]=b;
			 if(a/base==0){
				 res[i]=a;
				 i++;
				 break;
			 }else{
				 a=a/base;
			 }
			 i++;
			 
		 }
		 
		 return i;
	}
}
