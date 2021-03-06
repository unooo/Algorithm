package topcoder;

public class CrazyBot {
	
	public static int[][] grid = new int[30][30];
	public static int x = 15;
	public static int y = 15;
	public static char step[] = new char[14];
	public static int stepIter = 0 ;
	public static double res = 0;
	
	public static void main(String[] args){
		int n = 14;
		int east =50;
		int west =50;
		int south =0;
		int north=0;
		System.out.println(getProbability(n, east, west, south, north));
		

	}
	
	
	public static double getProbability(int n , int east , int west , int south , int north){
		dfs(n,'n',  east ,  west ,  south ,  north);
		dfs(n,'w',  east ,  west ,  south ,  north);
		dfs(n,'s',  east ,  west ,  south ,  north);
		dfs(n,'e',  east ,  west ,  south ,  north);
		return res;
	}
	
	
	public static void dfs(int n , char position,int east, int west , int south , int north){
		if(n<=0){			//Å½»ö¼º°ø
			
			return;
		}
		
		grid[x][y]=1;
		
		switch(position){
		case 'n' :
			y=y-1;break;
		case 's' :
			y=y+1;break;
		case 'w' :
			x=x-1;break;
		case 'e' :
			x=x+1;break;
		}
		
		if(grid[x][y]==1){
			grid[x][y]=0;
			switch(position){
			case 'n' :
				y=y+1;break;
			case 's' :
				y=y-1;break;
			case 'w' :
				x=x+1;break;
			case 'e' :
				x=x-1;break;
			}
			return;			
		}else{
			grid[x][y]=1;
			step[stepIter]=position;
			stepIter++;
		}		
		
		dfs(n-1,'n',east,west,south,north);		
		dfs(n-1,'s',east,west,south,north);
		dfs(n-1,'w',east,west,south,north);
		dfs(n-1,'e',east,west,south,north);
		if(n-1<=0){
			print(east,west,south,north);
		}
		grid[x][y]=0;
		switch(position){
		case 'n' :
			y=y+1;break;
		case 's' :
			y=y-1;break;
		case 'w' :
			x=x+1;break;
		case 'e' :
			x=x-1;break;
		}
		
		stepIter--;
	}
	
	public static void print(int east, int west , int south , int north){
		double resTemp=1;
		 for(int i = 0 ; i < stepIter ; i++){
			 System.out.print(step[i]);
			 switch(step[i]){
			 case 'n' : 
				 resTemp=resTemp*north/100.0; break;
			 case 's' :
				 resTemp=resTemp*south/100.0; break;
			 case 'w' :
				 resTemp=resTemp*west/100.0; break;
			 case 'e' :
				 resTemp=resTemp*east/100.0; break;
			 }
		 }
		 res+=resTemp;
		 System.out.println();
	}
	
}