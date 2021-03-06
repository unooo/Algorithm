package Kakao;

import java.util.ArrayList;

public class Blind2019 {
	public static void main(String[] args) {
		int[][] nodeinfo = { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 },
				{ 2, 2 } };
		solution(nodeinfo);
	}
	static ArrayList<Integer> result= new ArrayList<Integer>();
	static ArrayList<Integer> result2= new ArrayList<Integer>();
	static Node treeAry[] = new Node[10001];
	static int[][] nodeMap = new int[100000][100000];
	public static int[][] solution(int[][] nodeinfo) {
		int[][] answer = {};
		int nodeNum = nodeinfo.length;
		for (int i = 0; i < nodeNum; i++) {
			nodeMap[nodeinfo[i][1]][nodeinfo[i][0]] = i + 1;
		}
		
		for (int i = 1000; i >= 0; i--) {
			for (int j = 0; j < 1001; j++) {
				if (nodeMap[i][j] != 0) {
					int treeAryIdx = 1;				
					
					while(true){
						if (treeAry[treeAryIdx]==null) {
							treeAry[treeAryIdx] = new Node(i, j, nodeMap[i][j]);
							break;
						}
						
						if(treeAry[treeAryIdx].x>j){
							treeAryIdx=treeAryIdx*2; 
						}else{
							treeAryIdx=treeAryIdx*2+1;
						}						
						
					}
					
				}
			}
		}
		prev(1);next(1);
		int size=result.size();
		int[] temp = new int[size];
		for(int i = 0 ; i < size ; i ++){
			temp[i]=result.get(i);
		}
		int[] temp2 = new int[size];
		for(int i = 0 ; i < result2.size() ; i ++){
			temp2[i]=result2.get(i);
		}
		answer=new int[2][];
		answer[0]= temp;
		answer[1]=temp2;

		return answer;
	}

	public static void prev(int nowIndex ){
		if(nowIndex>10000||treeAry[nowIndex]==null)
			return;
		result.add(treeAry[nowIndex].num);
		prev(nowIndex*2);
		prev(nowIndex*2+1);
		
	}
	public static void next(int nowIndex ){
		if(nowIndex>10000||treeAry[nowIndex]==null)
			return;	
		next(nowIndex*2);
		next(nowIndex*2+1);
		result2.add(treeAry[nowIndex].num);
	}
	
	

	static class Node {
		int y, x, num;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		public Node(int y, int x, int num) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
		}

	}

}
