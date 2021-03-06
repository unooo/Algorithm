package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_14891 {

	public static int[][] gear = new int[4][8];
	public static int K;
	public static int[][] order;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			String stt = new String(br.readLine());
			for (int j = 0; j < 8; j++) {
				gear[i][j] = Integer.parseInt(stt.charAt(j)+"");
			}
		}
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		order = new int[K][2];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				order[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		spin();
		
		int ret = 0;
		if(gear[0][0]==1){
			ret+=1;
		}
		if(gear[1][0]==1){
			ret+=2;
		}
		if(gear[2][0]==1){
			ret+=4;
		}
		if(gear[3][0]==1){
			ret+=8;
		}
		
		System.out.println(ret);

	}

	public static void spin() {

		for (int i = 0; i < K; i++) {
			int startGearIndex = order[i][0]-1;

			// 오른쪽으로
			int nowGear = 0;
			int spin[] = new int[4];
			spin[startGearIndex ] = order[i][1];

			for (int j = startGearIndex; j < 3; j++) {
				if (gear[j][2] == gear[j + 1][6]) {

				} else {// 극이 다르면 회전
					switch (spin[j]) {
					case -1:
						spin[j + 1] = 1;
						break;
					case 1:
						spin[j + 1] = -1;
						break;
					}
				}
			}

			// 왼쪽으로
			for (int j = startGearIndex; j > 0; j--) {
				if (gear[j][6] != gear[j - 1][2]) {
					switch (spin[j]) {
					case -1:
						spin[j - 1] = 1;
						break;
					case 1:
						spin[j - 1] = -1;
						break;
					}
				}

			}
			// 배열값에 회전 내용 적용
			for(int j = 0 ; j < 4 ; j ++){
				switch(spin[j]){
				case -1: //반시계 // 후진
					int temp = gear[j][0];
					for(int k = 0 ; k < 7 ; k ++){
						gear[j][k]=gear[j][k+1];
					}
					gear[j][7]=temp;
					break;
				case 1: // 시계 // 전진
					int temp2 = gear[j][7];
					for(int k = 6 ; k >=0 ; k --){
						gear[j][k+1]=gear[j][k];
					}
					gear[j][0]=temp2;
					break;
					
				}
			}

		}
	}
}
