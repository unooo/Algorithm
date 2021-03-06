package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class greedy_10610 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int femail = Integer.parseInt(st.nextToken());
		int mail = Integer.parseInt(st.nextToken());
		int intern = Integer.parseInt(st.nextToken());
		
		int newFemail=femail/2;
		
		int team= newFemail>mail?mail:newFemail;
		
		int rem = femail+mail-team*3;
		
		intern-=rem;
		
		if(intern>0){
			team=team-intern/3;
			if(intern%3!=0)
				team--;
		}
		
		System.out.println(team);
		
		
		
	}
}
