package arithmea.shared;

public enum LatinLetter {
	A('A', 1, 1, 1, 1, 5),
	B('B', 2, 2, 2, 20, 20),
	C('C', 3, 3, 3, 13, 2),
	D('D', 4, 4, 4, 6, 23),
	E('E', 5, 5, 5, 25, 13),
	F('F', 8, 6, 6, 18, 12),
	G('G', 3, 7, 7, 11, 11),
	H('H', 5, 8, 8, 4, 3),
	I('I', 1, 9, 9, 23, 26),
	J('J', 1, 1, 10, 16, 7),
	K('K', 2, 2, 11, 9, 17),
	L('L', 3, 3, 12, 2, 1),
	M('M', 4, 4, 13, 21, 21),
	N('N', 5, 5, 14, 14, 24),
	O('O', 7, 6, 15, 19, 10),
	P('P', 8, 7, 16, 26, 4),
	Q('Q', 1, 8, 17, 17, 16),
	R('R', 2, 9, 18, 12, 14),
	S('S', 3, 1, 19, 5, 15),
	T('T', 4, 2, 20, 24, 9),
	U('U', 6, 3, 21, 17, 25),
	V('V', 6, 4, 22, 10, 22),
	W('W', 6, 5, 23, 3, 8),
	X('X', 5, 6, 24, 22, 6),
	Y('Y', 1, 7, 25, 15, 18),
	Z('Z', 7, 8, 26, 8, 19);

	public char character;
	public int chaldeanValue;
	public int pythagoreanValue;	
	public int iaValue;
	public int naeqValue;
	public int tqValue;
	
	private LatinLetter(char character, int chaldeanValue, int pythagoreanValue, int iaValue, int naeqValue, int tqValue) {
		this.character = character;
		this.chaldeanValue = chaldeanValue;
		this.pythagoreanValue = pythagoreanValue;
		this.iaValue = iaValue;
		this.naeqValue = naeqValue;
		this.tqValue = tqValue;
	}
}
