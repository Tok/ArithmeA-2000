package arithmea.shared.qabalah;

public enum Sephira {
	Kether(false, 1), 
	Chokmah(false, 2), 
	Binah(false, 3), 
	Daath(true, 11), 
	Chesed(false, 4), 
	Geburah(false, 5), 
	Tiphareth(false, 6), 
	Netzach(false, 7), 
	Hod(false, 8), 
	Yesod(false, 9), 
	Malkuth(false, 10);
	
	public boolean isImplicate;
	public int number;
	
	private Sephira(boolean isImplicate, int number) {
		this.isImplicate = isImplicate;
		this.number = number;
	}
}