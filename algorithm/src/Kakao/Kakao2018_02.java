package Kakao;

import java.util.ArrayList;

public class Kakao2018_02 {
	
	public static void main(String[] args){
		String dartResult = "1D#2S*3S";
		System.out.println(solution(dartResult));
	}

	public static int solution(String dartResult) {
		int answer = 0;
		int idx = -1;
		String num = new String();
		ArrayList<Integer> numAry = new ArrayList<Integer>();
		for (int i = 0; i < dartResult.length(); i++) {
			char ch = dartResult.charAt(i);
			int tp;
			switch (ch) {

			case 'S':
				idx++;
				tp = Integer.parseInt(num);
				num = new String();
				numAry.add(tp);
				;
				break;
			case 'D':
				idx++;
				tp = (int) Math.pow(Integer.parseInt(num),2) ;
				num = new String();
				numAry.add(tp);
				;
				break;
			case 'T':
				idx++;
				tp = (int)Math.pow(Integer.parseInt(num) ,3);
				num = new String();
				numAry.add(tp);
				;
				break;
			case '*':
				numAry.add(idx, numAry.remove(idx) * 2);
				if (idx - 1 < 0)
					continue;
				numAry.add(idx - 1, numAry.remove(idx - 1) * 2);
				;
				break;
			case '#':
				numAry.add(idx, numAry.remove(idx) * -1);
				if (idx - 1 < 0)
					continue;
				//numAry.add(idx - 1, numAry.remove(idx - 1) * -1);
				;
				break;
			default :
				num+=ch;

			}

		}
		for(int i = 0 ; i < numAry.size(); i++	){
			answer+=numAry.get(i);
		}
		
		return answer;
	}
}
