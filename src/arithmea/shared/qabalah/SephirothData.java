package arithmea.shared.qabalah;


import com.google.gwt.canvas.dom.client.CssColor;

public class SephirothData {
	public final static int UNIT = 30; 
	private final static int FACTOR = ((UNIT * 2) * 86) / 100;	
	public final static int WIDTH = 24 * UNIT;
	public final static int HEIGHT = 5 * UNIT;

	public enum SephiraPosition {
		Kether(Sephira.Kether, CssColor.make("#FFFFFF"), UNIT * 3, UNIT), 
		Chokmah(Sephira.Chokmah, CssColor.make("#AAAAAA"), (UNIT * 3) + (HEIGHT * FACTOR) / HEIGHT, UNIT * 2), 
		Binah(Sephira.Binah, CssColor.make("#000000"), (UNIT * 3) - (HEIGHT * FACTOR) / HEIGHT, UNIT * 2), 
		Daath(Sephira.Daath, CssColor.make("#777777"), UNIT * 3, UNIT * 3), 
		Chesed(Sephira.Chesed, CssColor.make("#3F48CC"), (UNIT * 3) + (HEIGHT * FACTOR) / HEIGHT, UNIT * 4), 
		Geburah(Sephira.Geburah, CssColor.make("#ED1C24"), (UNIT * 3) - (HEIGHT * FACTOR) / HEIGHT, UNIT * 4), 
		Tiphareth(Sephira.Tiphareth, CssColor.make("#FFF200"), UNIT * 3, UNIT * 5), 
		Netzach(Sephira.Netzach, CssColor.make("#22B14C"), (UNIT * 3) + (HEIGHT * FACTOR) / HEIGHT, UNIT * 6), 
		Hod(Sephira.Hod, CssColor.make("#FF7F27"), (UNIT * 3) - (HEIGHT * FACTOR) / HEIGHT, UNIT * 6), 
		Yesod(Sephira.Yesod, CssColor.make("#A349A4"), UNIT * 3, UNIT * 7), 
		Malkuth(Sephira.Malkuth, CssColor.make("#642100"), UNIT * 3, UNIT * 9);

		public Sephira sephira;
		public CssColor color;
		public int x;
		public int y;
		
		private SephiraPosition(Sephira sephira, CssColor color, int x, int y) {
			this.sephira = sephira;
			this.color = color;
			this.x = x;
			this.y = y;
		}
	}

	public SephiraPosition getPosition(Sephira sephira) {
		if (Sephira.Kether.equals(sephira)) { return SephiraPosition.Kether; };
		if (Sephira.Chokmah.equals(sephira)) { return SephiraPosition.Chokmah; };
		if (Sephira.Binah.equals(sephira)) { return SephiraPosition.Binah; };
		if (Sephira.Daath.equals(sephira)) { return SephiraPosition.Daath; };
		if (Sephira.Chesed.equals(sephira)) { return SephiraPosition.Chesed; };
		if (Sephira.Geburah.equals(sephira)) { return SephiraPosition.Geburah; };
		if (Sephira.Tiphareth.equals(sephira)) { return SephiraPosition.Tiphareth; };
		if (Sephira.Netzach.equals(sephira)) { return SephiraPosition.Netzach; };
		if (Sephira.Hod.equals(sephira)) { return SephiraPosition.Hod; };
		if (Sephira.Yesod.equals(sephira)) { return SephiraPosition.Yesod; };
		if (Sephira.Malkuth.equals(sephira)) { return SephiraPosition.Malkuth; };
		assert false;
		return SephiraPosition.Daath;
	}

}
