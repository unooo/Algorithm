package topcoder;



public class FriendScore {
	
	
	public static void main(String[] args){
		//String[] friends= {"NNN","NNN","NNN"};
		//String[] friends={"NYY","YNY","YYN"};
		//String[] friends={"NYNNN","YNYNN","NYNYN","NNYNY","NNNYN"};
		String[] friends={
				"NNNNYNNNNN",
				"NNNNYNYYNN",
				"NNNYYYNNNN",
				"NNYNNNNNNN",
				"YYYNNNNNNY",
				"NNYNNNNNYN",
				"NYNNNNNYNN",
				"NYNNNNYNNN",
				"NNNNNYNNNN",
				"NNNNYNNNNN"
		};
		 
		System.out.println(highestScore(friends));

	}

	public static int highestScore(String[] friends) {

		int res = 0;

		int strLen = friends.length;

		for (int i = 0; i < strLen; i++) {
			int temp[] = new int[strLen];
			for (int j = 0; j < strLen; j++) {
				if (friends[i].charAt(j) == 'Y') {
					temp[j] = 1;
					for (int k = 0; k < strLen; k++) {
						if (friends[j].charAt(k) == 'Y'&&k!=i) {
							temp[k]=1;
						}
					}
				}
			}
			
			int tempIter = 0 ;
			for(int j = 0 ; j < strLen ; j ++){
				if(temp[j]==1)
					tempIter++;
			}
			res=Math.max(res, tempIter);
		}
		

		return res;
	}

}
