import java.util.Arrays;

public class Programmers_±íÀÌ³Êºñ¿ì¼±_Å¸°Ù³Ñ¹ö {
	public static void main(String[] args){
		int[] numbers=new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		solution(numbers,10);
	}

	public static int solution(int[] numbers, int target) {
		int answer = 0;
		path = new int[numbers.length];
		dfs(0,numbers,target);
		return res;
	}
	
	public static int[]path;
	public static int res=0;
	public static void dfs(int nowStep,int []numbers, int target){
		if(nowStep>=path.length){
			//todo
			int temp=0;
			for(int i = 0 ; i < path.length ; i ++){
				if(path[i]==0)
					temp+=numbers[i];
				else
					temp+=numbers[i]*-1;							
			}
			if(temp==target)
				res++;
			return;
		}
		
		for(int i = 0; i < 2 ; i ++){
			path[nowStep]=i;
			dfs(nowStep+1,numbers,target);
		}
	}
}
