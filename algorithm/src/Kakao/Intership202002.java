package Kakao;

import java.util.LinkedList;
import java.util.ListIterator;

public class Intership202002 {

	static LinkedList<StringBuffer> list = new LinkedList<StringBuffer>();
	static long aws=Long.MIN_VALUE;
	public static void main(String[] args) {
		String expression = "50*6-3*2";
		solution(expression);

		
		System.out.println(aws);
	}

	public static long solution(String expression) {
		long answer = 0;
		int len = expression.length();
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < len; i++) {
			char ch = expression.charAt(i);
			if (ch == '+' || ch == '-' || ch == '*') {
				list.add(temp);
				list.add(new StringBuffer("" + ch));
				temp = new StringBuffer();
			} else if (i == len - 1) {
				temp.append(ch);
				list.add(temp);
			} else {
				temp.append(ch);
			}
		}
		ListIterator<StringBuffer> it = list.listIterator();
		dfs(0);

		return aws;
	}

	static int flag[] = new int[3];
	static int path[] = new int[3];

	public static void dfs(int next) {
		if (next >= 3) {
			long ret=0;
			LinkedList<StringBuffer> list2 = new LinkedList<StringBuffer>();
			copy(list, list2);
	
			for (int i = 0; i < 3; i++) {

				for (int j = 0; j < 3; j++) { // ÇÃ, ¸¶ , °ö
					String op=null;
					if (path[j] == i) {
						switch (j) {
						case 0:// ÇÃ
							op = new String("+");
							break;
						case 1:// ¸¶
							op = new String("-");;
							break;
						case 2:// °ö
							op = new String("*");
							break;
						}
						ListIterator<StringBuffer> li2 =list2.listIterator();
					
						while(li2.hasNext()){
							StringBuffer sb = li2.next();
							if(sb.toString().equals(op)){
								li2.previous();
								long num1=Long.parseLong(li2.previous()+"");
								li2.next();li2.next();
								long num2=Long.parseLong(li2.next()+"");
								
								switch (op.charAt(0)) {
								case '+':// ÇÃ
									ret=num1+num2;
									break;
								case '-':// ¸¶

									ret=num1-num2;
									break;
								case '*':// °ö
									ret=num1*num2;
									break;
								}
								li2.remove();
								StringBuffer sbb=li2.previous();
								li2.remove();
								li2.previous();
								li2.remove();
								li2.add(new StringBuffer(ret+""));
							}
						}
						
					}
					
				}

			
			}
			ret=Math.abs(ret);
			aws=Math.max(aws, ret);
			// todo
			return;
		}
		for (int i = 0; i < 3; i++) {
			if (flag[i] == 1)
				continue;
			flag[i] = 1;
			path[next] = i;
			dfs(next + 1);
			flag[i] = 0;
		}
	}
	public static void copy(LinkedList<StringBuffer> org, LinkedList<StringBuffer> dest){
		for(StringBuffer temp : org){
			StringBuffer tptp= new StringBuffer(temp);
			dest.add(tptp);
		}
	}
}
