package arithmea.shared.gematria;

public enum CyrillicLetter {
    \u0410('\u0410', true, 1, 1, 1), //Ð�
    \u0411('\u0411', false, 0, 0, 0), //Be
    \u0412('\u0412', true, 2, 2, 2), //Ve
    \u0413('\u0413', true, 3, 3, 3), //Ghe	
    \u0414('\u0414', true, 4, 4, 4), //De
    \u0415('\u0415', true, 5, 5, 5), //Ie
    \u0416('\u0416', false, 0, 0, 0), //Zhe
    \u0405('\u0405', true, 6, 6, 6), //Dze
    \u0417('\u0417', true, 7, 7, 7), //Ze
    \u0418('\u0418', true, 8, 8, 8), //I
    \u0419('\u0419', false, 0, 0, 0), //I (short)
    \u0472('\u0472', true, 9, 9, 9), //Fita
    \u0406('\u0406', true, 10, 10, 1), //I
    \u041A('\u041A', true, 11, 20, 2), //Ka
    \u041B('\u041B', true, 12, 30, 3), //El
    \u041C('\u041C', true, 13, 40, 4), //Em
    \u041D('\u041D', true, 14, 50, 5), //En
    \u046E('\u046E', true, 15, 60, 6), //Ksi
    \u041E('\u041E', true, 16, 70, 7), //O
    \u041F('\u041F', true, 17, 80, 8), //Pe 
    \u0427('\u0427', true, 18, 90, 9), //Che
    \u0420('\u0420', true, 19, 100, 1), //Er
    \u0421('\u0421', true, 20, 200, 2), //Es
    \u0422('\u0422', true, 21, 300, 3), //Te
    \u0474('\u0474', true, 22, 400, 4), //Izhitsa
    \u0423('\u0423', false, 0, 0, 0), //U
    \u0424('\u0424', true, 23, 500, 5), //Ef
    \u0425('\u0425', true, 24, 600, 6), //Ha
    \u0470('\u0470', true, 25, 700, 7), //Psi
    \u047E('\u047E', true, 26, 800, 8), //Ot
    \u0426('\u0426', true, 27, 900, 9), //Tse
    \u0428('\u0428', false, 0, 0, 0), //Sha
    \u0429('\u0429', false, 0, 0, 0), //Shcha
    \u042B('\u042B', false, 0, 0, 0), //Yeru
    \u042D('\u042D', false, 0, 0, 0), //E
    \u042E('\u042E', false, 0, 0, 0), //Yu	
    \u042F('\u042F', false, 0, 0, 0); //Ya
	
	public char cyrillic;
	public boolean isAncient;
	public int ordinalValue;
	public int fullValue;
	public int katanValue;

	private CyrillicLetter(char cyrillic, boolean isAncient, int ordinalValue, int fullValue, int katanValue) {
		this.cyrillic = cyrillic;
		this.isAncient = isAncient;
		this.ordinalValue = ordinalValue;
		this.fullValue = fullValue;
		this.katanValue = katanValue;
	}
}
