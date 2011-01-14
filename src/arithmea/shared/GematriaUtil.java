package arithmea.shared;

public class GematriaUtil {

	public static Integer getChaldean(String id) {
		char[] chars = id.toCharArray();
		int result = 0;
		for(int i = 0; i < chars.length; i++) {
			char c = chars[i];
			result += getChaldeanValue(c);
		}
		return result;
	}

	private static int getChaldeanValue(char c) {
		if (c == 'A' || c == 'I' || c == 'J' || c == 'Q' || c == 'Y') {
			return 1;
		}
		if (c == 'B' || c == 'K' || c == 'R') {
			return 2;
		}
		if (c == 'C' || c == 'G' || c == 'L' || c == 'S') {
			return 3;
		}
		if (c == 'D' || c == 'M' || c == 'T') {
			return 4;
		}
		if (c == 'E' || c == 'H' || c == 'N' || c == 'X') {
			return 5;
		}
		if (c == 'U' || c == 'V' || c == 'W') {
			return 6;
		}
		if (c == 'O' || c == 'Z') {
			return 7;
		}
		if (c == 'F' || c == 'P') {
			return 8;
		}
		return 0;
	}

	public static Integer getPythagorean(String id) {
		char[] chars = id.toCharArray();
		int result = 0;
		for(int i = 0; i < chars.length; i++) {
			char c = chars[i];
			result += getPythagorean(c);
		}
		return result;
	}

	private static int getPythagorean(char c) {
		if (c == 'A' || c == 'J' || c == 'S') {
			return 1;
		}
		if (c == 'B' || c == 'K' || c == 'T') {
			return 2;
		}
		if (c == 'C' || c == 'L' || c == 'U') {
			return 3;
		}
		if (c == 'D' || c == 'M' || c == 'V') {
			return 4;
		}
		if (c == 'E' || c == 'N' || c == 'W') {
			return 5;
		}
		if (c == 'F' || c == 'O' || c == 'X') {
			return 6;
		}
		if (c == 'G' || c == 'P' || c == 'Y') {
			return 7;
		}
		if (c == 'H' || c == 'Q' || c == 'Z') {
			return 8;
		}
		if (c == 'I' || c == 'R') {
			return 9;
		}
		return 0;
	}

	public static Integer getIa(String id) {
		char[] chars = id.toCharArray();
		int result = 0;
		for(int i = 0; i < chars.length; i++) {
			char c = chars[i];
			result += getIa(c);
		}
		return result;
	}

	private static int getIa(char c) {
		if (c == 'A') { return 1; }
		if (c == 'B') { return 2; }
		if (c == 'C') { return 3; }
		if (c == 'D') { return 4; }
		if (c == 'E') { return 5; }
		if (c == 'F') { return 6; }
		if (c == 'G') { return 7; }
		if (c == 'H') { return 8; }
		if (c == 'I') { return 9; }
		if (c == 'J') { return 10; }
		if (c == 'K') { return 11; }
		if (c == 'L') { return 12; }
		if (c == 'M') { return 13; }
		if (c == 'N') { return 14; }
		if (c == 'O') { return 15; }
		if (c == 'P') { return 16; }
		if (c == 'Q') { return 17; }
		if (c == 'R') { return 18; }
		if (c == 'S') { return 19; }
		if (c == 'T') { return 20; }
		if (c == 'U') { return 21; }
		if (c == 'V') { return 22; }
		if (c == 'W') { return 23; }
		if (c == 'X') { return 24; }
		if (c == 'Y') { return 25; }
		if (c == 'Z') { return 26; }
		return 0;
	}
		
	public static Integer getNaeq(String id) {
		char[] chars = id.toCharArray();
		int result = 0;
		for(int i = 0; i < chars.length; i++) {
			char c = chars[i];
			result += getNaeq(c);
		}
		return result;
	}

	private static int getNaeq(char c) {
		if (c == 'A') { return 1; }
		if (c == 'B') { return 20; }
		if (c == 'C') { return 13; }
		if (c == 'D') { return 6; }
		if (c == 'E') { return 25; }
		if (c == 'F') { return 18; }
		if (c == 'G') { return 11; }
		if (c == 'H') { return 4; }
		if (c == 'I') { return 23; }
		if (c == 'J') { return 16; }
		if (c == 'K') { return 9; }
		if (c == 'L') { return 2; }
		if (c == 'M') { return 21; }
		if (c == 'N') { return 14; }
		if (c == 'O') { return 19; }
		if (c == 'P') { return 26; }
		if (c == 'Q') { return 17; }
		if (c == 'R') { return 12; }
		if (c == 'S') { return 5; }
		if (c == 'T') { return 24; }
		if (c == 'U') { return 17; }
		if (c == 'V') { return 10; }
		if (c == 'W') { return 3; }
		if (c == 'X') { return 22; }
		if (c == 'Y') { return 15; }
		if (c == 'Z') { return 8; }
		return 0;
	}

	public static Integer getTq(String id) {
		char[] chars = id.toCharArray();
		int result = 0;
		for(int i = 0; i < chars.length; i++) {
			char c = chars[i];
			result += getTq(c);
		}
		return result;
	}
	
	private static int getTq(char c) {
		if (c == 'A') { return 5; }
		if (c == 'B') { return 20; }
		if (c == 'C') { return 2; }
		if (c == 'D') { return 23; }
		if (c == 'E') { return 13; }
		if (c == 'F') { return 12; }
		if (c == 'G') { return 11; }
		if (c == 'H') { return 3; }
		if (c == 'I') { return 26; }
		if (c == 'J') { return 7; }
		if (c == 'K') { return 17; }
		if (c == 'L') { return 1; }
		if (c == 'M') { return 21; }
		if (c == 'N') { return 24; }
		if (c == 'O') { return 10; }
		if (c == 'P') { return 4; }
		if (c == 'Q') { return 16; }
		if (c == 'R') { return 14; }
		if (c == 'S') { return 15; }
		if (c == 'T') { return 9; }
		if (c == 'U') { return 25; }
		if (c == 'V') { return 22; }
		if (c == 'W') { return 8; }
		if (c == 'X') { return 6; }
		if (c == 'Y') { return 18; }
		if (c == 'Z') { return 19; }
		return 0;
	}

}
