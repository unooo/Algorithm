package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Stack_2504 {
	public static int N = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = new String(br.readLine());

		Stack<Integer> stack = new Stack<Integer>();

		for (int i = 0; i < st.length(); i++) {
			if (st.charAt(i) == '(') {
				stack.push(-1);
			} else if (st.charAt(i) == ')') {
				if (stack.isEmpty()) {
					System.out.println(0);
					return;
				} else {
					if (stack.isEmpty()) {
						System.out.println(0);
						return;
					}
					int tp = stack.pop();
					if (tp == -1) {
						stack.push(2);
					} else if (tp == -2) {
						System.out.println(0);
						return;
					} else {

						while (true) {
							if (stack.isEmpty()) {
								stack.clear();
								System.out.println(0);
								return;
							}
							int tp2 = stack.pop();
							if (tp2 == -1) {
								break;
							} else {
								tp += tp2;
							}

						}
						stack.push(tp * 2);
					}
				}
			} else if (st.charAt(i) == '[') {
				stack.push(-2);
			} else if (st.charAt(i) == ']') {
				if (stack.isEmpty()) {
					System.out.println(0);
					return;
				} else {
					if (stack.isEmpty()) {
						System.out.println(0);
						return;
					}
					int tp = stack.pop();
					if (tp == -2) {
						stack.push(3);
					} else if (tp == -1) {
						System.out.println(0);
						return;
					} else {

						while (true) {
							if (stack.isEmpty()) {
								System.out.println(0);
								return;
							}
							int tp2 = stack.pop();
							if (tp2 == -2) {
								break;
							} else {
								tp += tp2;
							}

						}
						stack.push(tp * 3);
					}
				}
			}
		}
		
		 {

			int num = 0;
			int len = stack.size();
			for (int i = 0; i < len; i++){
				int tp = stack.pop();
				if(tp<0){
					System.out.println(0);
				return;	
				}
				num += tp;
			}
			if (num < 0)
				num = 0;
			System.out.println(num);
		}
	}
}
