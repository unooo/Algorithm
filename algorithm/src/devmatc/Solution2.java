package devmatc;

public class Solution2 {
	public static void main(String[] args) {
		int rows=6;
		int columns=6;
		int[][] queries={{2,2,5,4},{3,3,6,6},{5,1,6,3}};
		solution(rows, columns, queries);
	}
    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int map[][] = new int[rows][columns];
        int idx=1;
        for(int i = 0 ; i < rows ; i ++) {
        	for(int j = 0 ; j < columns ; j++) {
        		map[i][j]=idx;
        		idx++;
        	}
        }
        
        for(int tun = 0 ; tun < queries.length ;tun++) {
        	int r1=queries[tun][0]-1;
        	int c1=queries[tun][1]-1;
        	int r2=queries[tun][2]-1;
        	int c2=queries[tun][3]-1;        	
        	int temp=map[r1][c1];
        	int min=temp;
        	for(int i = r1 ; i <r2;i++) {
        		min=Math.min(min, map[i][c1]);
        		map[i][c1]=map[i+1][c1];
        	}
        	for(int j = c1 ; j <c2 ;j++) {
        		min=Math.min(min, map[r2][j]);
        		map[r2][j]=map[r2][j+1];
        	}
        	for(int i = r2 ; i >r1;i--) {
        		min=Math.min(min, map[i][c2]);
        		map[i][c2]=map[i-1][c2];
        	}
        	for(int j=c2;j>c1;j--) {
        		min=Math.min(min, map[r1][j]);
        		map[r1][j]=map[r1][j-1];
        	}
        	if(c1+1<=c2)
        		map[r1][c1+1]=temp;
        	answer[tun]=min;
        }
        
        
        System.out.println();
        
        
        return answer;
    }

}
