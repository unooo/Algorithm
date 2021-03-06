package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_2529 {

	public static int k;
	public static int ary[];
	public static long max=Long.MIN_VALUE;
	public static long min = Long.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		k=Integer.parseInt(st.nextToken());
		ary=new int[k]; //부등호 들어감
		int numAry[]= new int[k+1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0 ; i < k  ; i ++){
			
			switch(st.nextToken()){
			case"<":
				ary[i]=0;
				break;
			case ">":
				ary[i]=1;
				break;
				
			}
			
		}
		
		
		dfs(0,numAry);
		String str = new String();
		str+="%0"+(k+1)+"d";
		String str1= String.format(str, max);
		String str2= String.format(str, min);
		System.out.println(str1);
		System.out.println(str2);
		
		
	}
	public static int flag[] = new int[10];
	
	public static void dfs(int now,int[] numAry){
		if(now>=k+1){
			boolean ret = check(numAry);
			if(ret==true){
				String str = new String();
				for(int i = 0 ; i < numAry.length ;  i ++){
					str=str+numAry[i];
				}
				min=Math.min(min, Long.parseLong(str));
				max=Math.max(max,  Long.parseLong(str));
			}
			return;
		}
		
		for(int i = 0 ; i < 10 ; i ++){
			if(flag[i]==0){
			numAry[now]=i;
			flag[i]=1;
			dfs(now+1,numAry);
			flag[i]=0;
			}
		}
	}
	
	public static boolean check(int[] numAry){
		boolean flag= true;
		
		for(int i = 0 ; i <  k ; i ++){
			if(ary[i]==0){ //< 기호인경우
				flag= numAry[i]<numAry[i+1]?true:false;
			}else{
				flag=numAry[i]>numAry[i+1]?true:false;
			}
			
			if(flag==false)
				return false;
		}
		
		return flag;
	}
}
