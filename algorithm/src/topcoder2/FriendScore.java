package topcoder2;

public class FriendScore {

	public static void main(String[] args) {
		//String[] friends = { "NYNNN", "YNYNN", "NYNYN", "NNYNY", "NNNYN" };
		//String[] friends = {"NNN","NNN","NNN"};
		String[] friends={"NYY","YNY","YYN"};
		System.out.println(highestScore(friends));
	}

	public static int highestScore(String[] friends) {
		int ret = 0;

		for (int i = 0; i < friends.length; i++) {
			int temp = 0;
			
			boolean flag[] = new boolean[friends.length];
			
			for (int j = 0; j < friends[i].length(); j++) {
				
				if (friends[i].charAt(j) == 'Y' && flag[j] == false) {
					temp++;
					flag[j] = true;
					
					for (int k = 0; k < friends[j].length(); k++) {
						if(k==i)
							continue;
						if (friends[j].charAt(k) == 'Y' && flag[k] == false) {
							temp++;
							flag[k] = true;

						}
					}

				}
			}
			
			
			ret = Math.max(ret, temp);
		}

		return ret;
	}
}
