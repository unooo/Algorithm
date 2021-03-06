package Kakao;

import java.util.ArrayList;
import java.util.Collections;

public class Kakao2020_5 {
	public static void main(String[] args) {
		int n = 5;
//		int[][] build_frame = { { 1, 0, 0, 1 }, { 1, 1, 1, 1 }, { 2, 1, 0, 1 }, { 2, 2, 1, 1 }, { 5, 0, 0, 1 },
//				{ 5, 1, 0, 1 }, { 4, 2, 1, 1 }, { 3, 2, 1, 1 } };
		 int[][]
		 build_frame={{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
//		 int[][] build_frame={{1,0,0,1},{0,1,1,1},{1,1,1,1},{1,1,0,1}};
		// int [][] build_frame={{}};
//		
//		  int[][] build_frame = { { 1, 0, 0, 1 }, { 1, 1, 1, 1 }, { 2, 1, 0, 1
//		  }, { 2, 2, 1, 1 }, { 5, 0, 0, 1 }, { 5, 1, 0, 1 }, { 4, 2, 1, 1 }, {
//		  3, 2, 1, 1 }, { 5, 1, 0, 0 } };
		 
		// int[][] build_frame = { { 0, 0, 0, 1 }, { 0, 1, 0, 1 }, { 0, 2, 0, 1
		// }, { 0, 3, 1, 1 }, { 1, 0, 0, 1 },
		// { 1, 1, 0, 1 }, { 1, 2, 0, 1 }, { 1, 2, 0, 0 }, { 0, 2, 0, 0 } };

		int[][] ret = solution(n, build_frame);
		System.out.println();
	}

	static int dy[] = new int[] { 1, -1, 0, 0 };
	static int dx[] = new int[] { 0, 0, -1, 1 };
	static final int Gidoong = 0;
	static final int Bo = 1;
	static final int Delete = 0;
	static final int Insert=1;
	static int[][][] board ;
	public static int[][] solution(int n, int[][] build_frame) {
		int[][] answer = {};
		
		 board = new int[n+1][n+1][2];
		for(int i = 0 ; i < build_frame.length ; i++){
			int r = build_frame[i][1];
			int c = build_frame[i][0];
			int type =build_frame[i][2];
			int order =build_frame[i][3];
			BlockInfo blockInfo = new BlockInfo(r,c,type,order);
			if(order==Insert){
				doInsert(blockInfo,n);
			}else{
				doDelete(blockInfo,n);
			}
		}
		ArrayList<BlockInfo> biAry= new ArrayList<BlockInfo>();
		for(int j = 0 ; j < n+1 ; j++){
			for(int i = 0 ; i < n+1 ; i++){
				if(board[i][j][Gidoong]==1){
					biAry.add(new BlockInfo(i,j,Gidoong,-1));
				}
				if(board[i][j][Bo]==1){
					biAry.add(new BlockInfo(i,j,Bo,-1));
				}
			}
		}
		
		answer= new int[biAry.size()][3];
		int idx=0;
		for(BlockInfo temp:biAry){
			answer[idx][0]=temp.j;
			answer[idx][1]=temp.i;
			answer[idx][2]=temp.type;
			idx++;
		}
		

		return answer;
	}
	public static void doInsert(BlockInfo blockInfo,int n){
		boolean chkFlag ;
		if(blockInfo.type==Gidoong){
			chkFlag=checkGidoong(blockInfo);
		}else{
			chkFlag=checkBo(blockInfo,n);
		}
		if(chkFlag){
			if(blockInfo.type==Gidoong){
				board[blockInfo.i][blockInfo.j][Gidoong]=1;
			}else{
				board[blockInfo.i][blockInfo.j][Bo]=1;
			}
		}
	}
	public static void doDelete(BlockInfo blockInfo,int n){

		if(blockInfo.type==Gidoong){
			board[blockInfo.i][blockInfo.j][Gidoong]=0;
		}else{
			board[blockInfo.i][blockInfo.j][Bo]=0;
		}
		
		for(int i = 0 ; i < n+1 ; i ++){
			for(int j = 0 ; j < n+1 ; j ++){
				for(int k = 0 ; k < 2 ; k ++){
					if(board[i][j][k]==1){
						boolean tpChk;
						if(k==Bo)
							tpChk=checkBo(new BlockInfo(i,j,Bo,Insert),n);
						else
							tpChk=checkGidoong(new BlockInfo(i,j,Gidoong,Insert));
						if(tpChk==false){
							if(blockInfo.type==Bo){ //k==bo라 해서 틀림
								board[blockInfo.i][blockInfo.j][Bo]=1;
							}else{
								board[blockInfo.i][blockInfo.j][Gidoong]=1;
							}
							return;
						}
					}
				}
			}
		}
		
		
	}
	
	public static boolean checkGidoong(BlockInfo blockInfo){
		boolean ret= false;
		if(blockInfo.i==0){
			ret = true;
		}
		if(blockInfo.j-1>=0&&board[blockInfo.i][blockInfo.j-1][Bo]==1){
			ret=true;
		}
		if(board[blockInfo.i][blockInfo.j][Bo]==1){
			ret = true;
		}
		if(blockInfo.i-1>=0&&board[blockInfo.i-1][blockInfo.j][Gidoong]==1){
			ret=true;
		}
		
		
		return ret;
	}
	
	public static boolean checkBo(BlockInfo blockInfo,int n){
		boolean ret = false;
		
		if((blockInfo.i-1>=0)&&(board[blockInfo.i-1][blockInfo.j][Gidoong]==1)){
			ret = true;
		}else if((blockInfo.i-1>=0)&&(blockInfo.j+1<=n)&&(board[blockInfo.i-1][blockInfo.j+1][Gidoong]==1)){
			ret = true;
		}else if((blockInfo.j-1>=0)&&(blockInfo.j+1<=n)&&(board[blockInfo.i][blockInfo.j-1][Bo]==1&&board[blockInfo.i][blockInfo.j+1][Bo]==1)){
			ret=true;
		}
		
		return ret;
	}
	
	static class BlockInfo{
		int i, j, type, order;

		public BlockInfo(int i, int j, int type, int order) {
			super();
			this.i = i;
			this.j = j;
			this.type = type;
			this.order = order;
		}
		
	}


}
