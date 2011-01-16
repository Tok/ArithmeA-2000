package arithmea.shared;

public enum Path {
	Aleph(HebrewLetter.Aleph, 11, Sephira.Kether, Sephira.Chokmah), 
	Beth(HebrewLetter.Beth, 12, Sephira.Kether, Sephira.Binah), 
	Gimel(HebrewLetter.Gimel, 13, Sephira.Kether, Sephira.Tiphareth),
	Daleth(HebrewLetter.Daleth, 14, Sephira.Chokmah, Sephira.Binah),
	Heh(HebrewLetter.Heh, 15, Sephira.Chokmah, Sephira.Tiphareth),
	Vav(HebrewLetter.Vav, 16, Sephira.Chokmah, Sephira.Chesed),
	Zain(HebrewLetter.Zain, 17, Sephira.Binah, Sephira.Tiphareth),
	Cheth(HebrewLetter.Cheth, 18, Sephira.Binah, Sephira.Geburah),
	Teth(HebrewLetter.Teth, 19, Sephira.Chesed, Sephira.Geburah),
	Yud(HebrewLetter.Yud, 20, Sephira.Chesed, Sephira.Tiphareth),
	Kaph(HebrewLetter.Kaph, 21, Sephira.Chesed, Sephira.Netzach),
	Lamed(HebrewLetter.Lamed, 22, Sephira.Geburah, Sephira.Tiphareth),
	Mem(HebrewLetter.Mem, 23, Sephira.Geburah, Sephira.Hod),
	Nun(HebrewLetter.Nun, 24, Sephira.Tiphareth, Sephira.Netzach),
	Samekh(HebrewLetter.Samekh, 25, Sephira.Tiphareth, Sephira.Yesod),
	Ayin(HebrewLetter.Ayin, 26, Sephira.Tiphareth, Sephira.Hod),
	Peh(HebrewLetter.Peh, 27, Sephira.Netzach, Sephira.Hod),
	Tzaddi(HebrewLetter.Tzaddi, 28, Sephira.Netzach, Sephira.Yesod),	
	Qoph(HebrewLetter.Qoph, 29, Sephira.Netzach, Sephira.Malkuth), 
	Resh(HebrewLetter.Resh, 30, Sephira.Hod, Sephira.Yesod),
	Shin(HebrewLetter.Shin, 31, Sephira.Hod, Sephira.Malkuth),
	Tav(HebrewLetter.Tav, 32, Sephira.Yesod, Sephira.Malkuth);
	
	public HebrewLetter letter;
	public int number;
	public Sephira from;
	public Sephira to;
	
	private Path(HebrewLetter letter, int number, Sephira from, Sephira to) {
		this.letter = letter;
		this.number = number;
		this.from = from;
		this.to = to;
	}
	
//	public Path getPath(HebrewLetter letter) {
//		return this
//	}
	
}
