import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;



public class Programmers_«¡∑ª¡Ó4∫Ì∑œ {

	
	
	@Test
	public void mainTest() {
		String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF" };
		int m = 4;
		int n = 5;		
		Assert.assertEquals(solution(m, n, board),14);
		
		ret=0;
		board=new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
		m=6;n=6;
		Assert.assertEquals(15,solution(m, n, board));
	}
	
	
	static char[][] orgMap; 
	static char[][] newMap;
	public static int solution(int m, int n, String[] board) {
		

		orgMap = new char[m][n];
		newMap = null;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				orgMap[i][j] = board[i].charAt(j);
			}
		}

		while (true) {
			newMap = mapCopy( m, n);
			if (!solve( m, n))
				break;

		}
		
		for(int i = 0 ; i < m ; i ++){
			for(int j = 0 ; j < n ; j++){
				if(newMap[i][j]=='0')
					ret++;
			}
		}
		

		return ret;
	}

	static int ret = 0;

	public static boolean solve( int m, int n) {
		boolean ret = false;

		for (int i = 0; i < m - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				Set<Character> set = new HashSet<Character>();
				for (int r = i; r <= i + 1; r++) {
					for (int c = j; c <= j + 1; c++) {
						set.add(orgMap[r][c]);
					}
				}
				if (set.size() == 1&&!set.contains('0')) {
					ret = true;
					for (int r = i; r <= i + 1; r++) {
						for (int c = j; c <= j + 1; c++) {
							newMap[r][c] = '0';
						}
					}
				}

			}
		}

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				if (newMap[i][j] != '0') {
					continue;
				}
				for (int r = i; r > 0; r--) {
					newMap[r][j] = newMap[r - 1][j];
				}
				newMap[0][j] = '0';
			}
		}
		orgMap = newMap;
		return ret;
	}

	public static char[][] mapCopy( int m, int n) {
		char[][] newMap = new char[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				newMap[i][j] = orgMap[i][j];
			}
		}
		return newMap;
	}

}
