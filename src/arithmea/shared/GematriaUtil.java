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

}
