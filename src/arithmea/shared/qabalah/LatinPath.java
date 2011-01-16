package arithmea.shared.qabalah;


public enum LatinPath {
	A(1, Sephira.Kether, Sephira.Chokmah), 
	B(2, Sephira.Kether, Sephira.Chesed), 
	C(3, Sephira.Kether, Sephira.Tiphareth), 
	D(4, Sephira.Kether, Sephira.Binah), 
	E(5, Sephira.Kether, Sephira.Geburah), 
	F(6, Sephira.Chokmah, Sephira.Binah), 
	G(7, Sephira.Chokmah, Sephira.Geburah), 
	H(8, Sephira.Chokmah, Sephira.Tiphareth), 
	I(9, Sephira.Chokmah, Sephira.Chesed), 
	J(10, Sephira.Binah, Sephira.Chesed), 
	K(11, Sephira.Binah, Sephira.Tiphareth), 
	L(12, Sephira.Binah, Sephira.Geburah), 
	M(13, Sephira.Chesed, Sephira.Geburah), 
	N(14, Sephira.Chesed, Sephira.Tiphareth), 
	O(15, Sephira.Chesed, Sephira.Netzach), 
	P(16, Sephira.Geburah, Sephira.Tiphareth), 
	Q(17, Sephira.Geburah, Sephira.Hod), 
	R(18, Sephira.Tiphareth, Sephira.Netzach), 
	S(19, Sephira.Tiphareth, Sephira.Yesod), 
	T(20, Sephira.Tiphareth, Sephira.Hod), 
	U(21, Sephira.Netzach, Sephira.Hod), 
	V(22, Sephira.Netzach, Sephira.Yesod), 
	W(23, Sephira.Netzach, Sephira.Malkuth), 
	X(24, Sephira.Hod, Sephira.Yesod), 
	Y(25, Sephira.Hod, Sephira.Malkuth), 
	Z(26, Sephira.Yesod, Sephira.Malkuth);

	public int number;
	public Sephira from;
	public Sephira to;
	
	private LatinPath(int number, Sephira from, Sephira to) {
		this.number = number;
		this.from = from;
		this.to = to;
	}
}
