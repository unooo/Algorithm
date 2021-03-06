package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
//결론 1. 비짓플래그와 리무브플래그 두개를 쓰자 
//결론2. 같은것을 지우기위해 리무브플래그를 따로쓰는데 이때 원판돌리기와는 다르개 개수 4개 이상을 요구하기때문에 필수적으로 써야한다. 그냥 비짓플래그 하나에 -1같이 숫자다른걸 넣으려고 꼼수쓰면 오히려 복잡해지고  원래그래프의 값에 지울값을 -1 하면 인터럽션에러 위험이있다
//결론3. 말 그대로 interuption 위험을 조심하자. 같은뿌요를 -1 처리할때 그 뿌요값을 따로 빼놓지않고 그래프에서 계속 꺼내면 -1처리해놔서 인터럽발생가능하다.
//결론4. 이중포문안에서의 와일문(bfs) 의 형태를 생각하자. 원판돌리기와 같음
//결론5. 제일중요! 비짓플래그를 어디서 체크해둘것이냐 -> poll할대 혹은 add할때 딱 두군데에서만 체크하자(둘중하나택1해서하면됨)
public class SamsungEx_11559 {

	public static int[][] graph = new int[12][6];
	public static int stack = 0;
	public static int dy[] = new int[] { 1, -1, 0, 0 };
	public static int dx[] = new int[] { 0, 0, 1, -1 };
	public static int[][] visitFlag = new int[12][6]; 
	public static int[][] removeFlag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st;

		for (int i = 0; i < 12; i++) {
			st = new String(br.readLine());
			for (int j = 0; j < 6; j++) {
				char ch = st.charAt(j);
				switch (ch) {
				case 'R':
					graph[i][j] = 1;
					break;
				case 'G':
				
					
					graph[i][j] = 2;
					break;
				case 'B':
					graph[i][j] = 3;
					break;
				case 'P':
					graph[i][j] = 4;
					break;
				case 'Y':
					graph[i][j] = 5;
					break;
				}
			}
		}

		int ret = 0;
		while (true) {
			ret = bfs();
			if (ret != 4) {
				break;
			}
			renewalGraph();
			stack++;
		}
		System.out.println(stack);
	}
	
	public static void renewalGraph(){ // 이 함수에대한고민
		//1) 아직도 행우선 회전이 익숙지 않음
		//2) 한칸씩 밀리는 부분에대한 엄증성 및 이해
		//2-1)맨아래부터 혹은 맨위에부터 등등 어느순서로처리할것인가
		//2-2)0에대한처리
		for(int i = 0 ; i < 6 ; i ++){
			for(int j = 0 ; j <12 ; j ++){
				
				if(removeFlag[j][i]==1){
				
					for(int k = j ; k >=0 ; k --){
						if(k==0){//이 이프문이 없어서 계속 틀렸음
							graph[k][i]=0;
							continue;
						}
						graph[k][i]=graph[k-1][i];
					}
					
					
				}
				
			}
		}
	}

	public static int[][] makeVisitFlag(int[][] flag) {
		int[][] ret = new int[12][6];

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (flag[i][j] == 0)
					ret[i][j] = 1;
			}
		}
		return ret;
	}

	public static int bfs() {

		Queue<Pair> queue = new LinkedList<Pair>();
		visitFlag = makeVisitFlag(graph);
		int ret = 0;
		int sameNum = 0;
		 removeFlag = new int[12][6];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) { //이중포문안에서 와일문은 정해진 ㅍ패턴이다(삼성 원판돌리기)
				if (visitFlag[i][j] == 0) {
					queue.add(new Pair(i, j));
					//visitFlag[i][j] = 1;
					sameNum=1;
					int value=graph[i][j];
					int [][]removeFlagCpy = new int[12][6];
					for(int a = 0 ; a < 12 ; a ++){
						for(int b = 0 ; b < 6 ; b++){
							removeFlagCpy[a][b]=removeFlag[a][b];
						}
					}
							
					while (!queue.isEmpty()) {
						Pair pair = queue.poll();
						int startY = pair.y;
						int startX = pair.x;
						visitFlag[startY][startX] = 1;// 이 줄의 위치가 중요 //visitFlag와 removeFlag를 분리하자(원래는 visitFlag에 -1로 하려했으나 그렇게하는경우 -1이 1로바귀는등의 귀찮은문제발생)
						int nowY = pair.y;
						int nowX = pair.x;

						int nextY = pair.y;
						int nextX = pair.x;

						for (int k = 0; k < 4; k++) {
							nextY = nowY + dy[k];
							nextX = nowX + dx[k];
							if (nextY < 0 || nextX < 0 || nextY >= 12 || nextX >= 6) {
								continue;
							}

							if (visitFlag[nextY][nextX] == 1)
								continue;

							if (value == graph[nextY][nextX]) {
								queue.add(new Pair(nextY, nextX));
								//visitFlag[startY][startX] = 1;
								//visitFlag[nextY][nextX] = 1; // 이거 뺴먹음 //뺴는게맞음
								
								//graph[startY][startX] = -1; // 만약 4개 미만이라면 다시 원래수로 돌려놔야하는데 그게문제->이문제아니면 비짓플래그와 리무브플래그를 나눌 이유가 없다.
								//graph[nextY][nextX] = -1;
								//visitFlag[startY][startX] = -1; 
								//visitFlag[nextY][nextX] = 1;
								sameNum++;
								removeFlagCpy[startY][startX]=1;
								removeFlagCpy[nextY][nextX]=1;
							}
						}
						

					}
					if(sameNum>=4){
						
						removeFlag=removeFlagCpy;
						ret=4;
					}
				}
			}
		}

		return ret;

	}

	static class Pair {
		int y;
		int x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

}
