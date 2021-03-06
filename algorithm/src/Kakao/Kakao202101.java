package Kakao;

//45Ка
public class Kakao202101 {

	public static void main(String[] args) {
		String new_id = new String("~!@#$%^&*()=+[{]}:?,<>/");
		System.out.println(solution(new_id));
	}

	public static String solution(String new_id) {
		String answer = "";

		new_id = new_id.toLowerCase().replaceAll("[^a-z0-9-_.]", "").replaceAll("[.]{2,}", "\\.")
				.replaceAll("^\\.", "").replaceAll("\\.$", "");
		if (new_id.length() == 0)
			new_id = "a";
		if (new_id.length() >= 16)
			new_id = new_id.substring(0, 15).replaceAll("\\.$", "");

		while (new_id.length() <= 2) {
			new_id += new_id.charAt(new_id.length() - 1);
		}
		return new_id;
	}
}
