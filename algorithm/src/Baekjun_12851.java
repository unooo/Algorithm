

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baekjun_12851 {

	static int N;
	static int K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int visit[][] = new int[100001][2];
		if (N == K) {
			System.out.println(0);
			System.out.println(1);
			return;
		}
		visit[N][0] = 1;
		visit[N][1] = 1;
		Deque<Integer> queue = new LinkedList<Integer>();
		queue.add(N);
		while (!queue.isEmpty()) {
			int size = queue.size();
			boolean flag = false;
			for( int tun = 0 ; tun < size ; tun++){
				int nowNumber = queue.poll();
				
				for (int i = 0; i < 3; i++) {
					int nextNum = 0;
					
					switch (i) {
					case 0:
						nextNum = nowNumber - 1;
						break;
					case 1:
						nextNum = nowNumber + 1;
						break;
					case 2:
						nextNum = nowNumber * 2;
						break;
					}
					if(nextNum<0||nextNum>100000)
						continue;
					if(nextNum==K){
						flag = true;
					}
					if(visit[nextNum][0]==0){
						visit[nextNum][0]=visit[nowNumber][0]+1;
						visit[nextNum][1]=visit[nowNumber][1];
						queue.add(nextNum);
					}else{
						if(visit[nextNum][0]==visit[nowNumber][0]+1){
							visit[nextNum][1]+=visit[nowNumber][1];
						}
					}
				}
				
			}
			if(flag){
				System.out.println(visit[K][0]-1);
				System.out.println(visit[K][1]);
				return;
			}
			
		}
	}
}
