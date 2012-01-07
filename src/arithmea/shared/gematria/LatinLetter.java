package arithmea.shared.gematria;

/**
 * Thanks to Alien696 for the EQ cipher and the corresponding signs.
 */
public enum LatinLetter {
	A('A', 1, 1,  1,  1,  5, 1,   1, "Gemini"),
	B('B', 2, 2,  2, 20, 20, 0, 200, "Earth"),
	C('C', 3, 3,  3, 13,  2, 0,  40, "Ophiuchus"), //formerly Neptune
	D('D', 4, 4,  4,  6, 23, 0,   6, "Taurus"),
	E('E', 5, 5,  5, 25, 13, 2, 700, "Earth"),
	F('F', 8, 6,  6, 18, 12, 0,  90, "Sol"),
	G('G', 3, 7,  7, 11, 11, 7,  20, "Virgo"),
	H('H', 5, 8,  8,  4,  3, 8,   4, "Aquarius"),
	I('I', 1, 9,  9, 23, 26, 3, 400, "Fire"), //formerly Air
	J('J', 1, 1, 10, 16,  7, 0,  70, "Saturn"),
	K('K', 2, 2, 11,  9, 17, 0,   9, "Libra"),
	L('L', 3, 3, 12,  2,  1, 9,   2, "Capricorn"),
	M('M', 4, 4, 13, 21, 21, 0, 300, "Jupiter"),
	N('N', 5, 5, 14, 14, 24, 0,  50, "Mercury"),
	O('O', 7, 6, 15, 19, 10, 4,   7, "Aries"),
	P('P', 8, 7, 16, 26,  4, 0, 800, "Spirit"),
	Q('Q', 1, 8, 17, 17, 16, 0, 100, "Luna"),
	R('R', 2, 9, 18, 12, 14, 0,  20, "Pisces"),
	S('S', 3, 1, 19,  5, 15, 6,   5, "Scorpio"),
	T('T', 4, 2, 20, 24,  9, 0, 600, "Water"),
	U('U', 6, 3, 21, 17, 25, 5,  80, "Mars"),
	V('V', 6, 4, 22, 10, 22, 0,  10, "Cancer"),
	W('W', 6, 5, 23,  3,  8, 0,   3, "Sagittarius"),
	X('X', 5, 6, 24, 22,  6, 0, 500, "Air"), //formerly Fire
	Y('Y', 1, 7, 25, 15, 18, 7,  60, "Venus"),
	Z('Z', 7, 8, 26,  8, 19, 0,   8, "Leo");

	
	public char character;
	public int chaldeanValue;
	public int pythagoreanValue;	
	public int iaValue;
	public int naeqValue;
	public int tqValue;	
	public int germanValue;
	public int eqValue;
	public String eqSign;

	private LatinLetter(char character, int chaldeanValue, int pythagoreanValue, int iaValue, int naeqValue, int tqValue, int germanValue, int eqValue, String eqSign) {
		this.character = character;
		this.chaldeanValue = chaldeanValue;
		this.pythagoreanValue = pythagoreanValue;
		this.iaValue = iaValue;
		this.naeqValue = naeqValue;
		this.tqValue = tqValue;
		this.germanValue = germanValue;
		this.eqValue = eqValue;
		this.eqSign = eqSign;
	}
}
