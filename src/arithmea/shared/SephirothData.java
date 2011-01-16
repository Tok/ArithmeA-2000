package arithmea.shared;

public class SephirothData {

	private final static int UNIT = 50;
	public final static int WIDTH = 6 * UNIT;
	public final static int HEIGHT = 10 * UNIT;

	public enum SephiraPosition {
		Kether(Sephira.Kether, UNIT * 3, UNIT), 
		Chokmah(Sephira.Chokmah, (UNIT * 3) + (HEIGHT * 86) / HEIGHT, UNIT * 2), 
		Binah(Sephira.Binah, (UNIT * 3) - (HEIGHT * 86) / HEIGHT, UNIT * 2), 
		Daath(Sephira.Daath, UNIT * 3, UNIT * 3), 
		Chesed(Sephira.Chesed, (UNIT * 3) + (HEIGHT * 86) / HEIGHT, UNIT * 4), 
		Geburah(Sephira.Geburah, (UNIT * 3) - (HEIGHT * 86) / HEIGHT, UNIT * 4), 
		Tiphareth(Sephira.Tiphareth, UNIT * 3, UNIT * 5), 
		Netzach(Sephira.Netzach, (UNIT * 3) + (HEIGHT * 86) / HEIGHT, UNIT * 6), 
		Hod(Sephira.Hod, (UNIT * 3) - (HEIGHT * 86) / HEIGHT, UNIT * 6), 
		Yesod(Sephira.Yesod, UNIT * 3, UNIT * 7), 
		Malkuth(Sephira.Malkuth, UNIT * 3, UNIT * 9);

		public Sephira sephira;
		public int x;
		public int y;

		private SephiraPosition(Sephira sephira, int x, int y) {
			this.sephira = sephira;
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
