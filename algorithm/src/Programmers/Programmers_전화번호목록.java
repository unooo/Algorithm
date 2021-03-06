package Programmers;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Programmers_전화번호목록 {

	public static void main(String[] args) {
		String[] phone_book = new String[] { "119", "97674223", "1195524421" };
		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) {
		HashSet<String> set = new HashSet<>();
		Arrays.sort(phone_book,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o2.length()-o1.length();
			}
		});
		for (String temp : phone_book) {
			
			boolean flag = set.add(temp);
			if (flag == false) {
				return false;
			}

			int len = temp.length();

			for (int i = 1; i <= len; i++) {
				String subStr = temp.substring(0, i);
				set.add(subStr);
			}

		}
		return true;
	}
}
