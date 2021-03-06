package Programmers;
import java.util.LinkedList;

public class Programmers_스택큐_기능개발 {
	public static void main(String[] args){
		int[] progresses={93,30,55};
		int[] speeds={1,30,5};
		solution(progresses, speeds);
		
	}
	public  static int[] solution(int[] progresses, int[] speeds) {
		int[] answer = {};
		LinkedList<Integer> res = new LinkedList<Integer>();
		Loop:
		while (true) {
			int flag = 0;
			for (int i = 0; i < progresses.length; i++) {
				if (progresses[i] == -1 || progresses[i] == 100)
					continue;
				progresses[i] += speeds[i];
				if(progresses[i]>100)
					progresses[i]=100;
			}
			if (progresses[0] >= 100) {
				progresses[0] = -1;
				flag ++;
			}

			for (int i = 1; i < progresses.length; i++) {
				if (progresses[i] == 100 && progresses[i - 1] == -1) {
					flag++;
					progresses[i] = -1;
				}
			}
			if(flag!=0){
				res.add(flag);
			}else{
				for (int i = 1; i < progresses.length; i++) {
					if(progresses[i]!=-1){
						continue Loop;
					}
				}				
				break Loop;
			}
		}
		return res.stream().mapToInt(Integer::intValue).toArray();
	}
}
