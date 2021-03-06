package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Stack_10799 {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = new String(br.readLine());
		
		Stack<Integer> stack = new Stack<Integer>();
		int num=0;
		for(int i = 0 ; i < st.length() ; i ++){
			if(st.charAt(i)=='('){
				stack.push(0);
			}else{
				if(st.charAt(i-1)=='('){//레이저인경우
					stack.pop();
					if(stack.size()!=0)
					stack.push((stack.pop())+1);
				}else{ //레이저아닌 닫는괄호인경우
					int tp=stack.pop();
					if(tp!=0)
						num+=tp+1;
					if(!stack.isEmpty()){
						
						stack.push(stack.pop()+tp);
					}
				}
			}
		}
		System.out.println(num);
	}
}
