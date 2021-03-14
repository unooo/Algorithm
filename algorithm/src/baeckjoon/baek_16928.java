package baeckjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek_16928 {

	static int bridge, snake;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		bridge= Integer.parseInt(st.nextToken());
		snake= Integer.parseInt(st.nextToken());
		HashMap<Integer, Integer> snakeMap = new HashMap<>();
		HashMap<Integer, Integer> bridgeMap=new HashMap<>();
		for(int i = 0 ; i < bridge ; i ++){
			st = new StringTokenizer(br.readLine());
			bridgeMap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for(int i = 0 ; i < snake ; i ++){
			st = new StringTokenizer(br.readLine());
			snakeMap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));		
		}
		
		int visit[] = new int[101];
		Queue<Integer> que = new LinkedList<Integer>();
		visit[1]=1;
		que.add(1);
		while(!que.isEmpty()){
			int nowStep=que.poll();
			for(int i = 1 ; i <=6 ; i++){
				int nextStep= nowStep+i;
				if(nextStep>100)
					continue;
				if(visit[nextStep]!=0)
					continue;
				visit[nextStep]=visit[nowStep]+1;
				
				Integer val= bridgeMap.get(nextStep);
				if(val!=null){
					nextStep=val;
					if(visit[nextStep]!=0)
						continue;
					visit[nextStep]=visit[nowStep]+1;
					que.add(nextStep);
					continue;
				}
				
				val=snakeMap.get(nextStep);
				if(val!=null){
					nextStep=val;
					if(visit[nextStep]!=0)
						continue;
					
					visit[nextStep]=visit[nowStep]+1;
					que.add(nextStep);
					continue;
				}

				visit[nextStep]=visit[nowStep]+1;
				que.add(nextStep);
			}
		}
		System.out.println(visit[100]-1);
	}
	
	
}
