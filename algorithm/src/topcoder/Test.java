package topcoder;

public class Test {
	
	public static char[] ary = new char[100];
	public static int aryIter = 0 ;
	
	public static void main(String args[]){
		dfs(3,'��');
	}
	
	public static void dfs(int n, char ch){
		if(n<=0){
			
			return;
		}
		ary[aryIter]=ch;
		aryIter++;

		dfs(n-1,'��');
		dfs(n-1,'��');
		dfs(n-1,'��');
		dfs(n-1,'��');
		//����Ʈ
		if(n-1<=0){
			print();
		}

		aryIter--;
		
	}
	
	public static void print(){
		for(int i = 0 ; i <aryIter ; i ++){
			System.out.print(ary[i]);
			
		}
		System.out.println();
	}

}
