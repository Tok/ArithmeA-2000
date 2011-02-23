package arithmea.shared.data;

public enum Highlight {
	eleven(11, "master-number", "red"), 
	thirteen(13, "fibonacci-number", "green"),
	twentyOne(21, "fibonacci-number", "green"),
	twentyTwo(22, "master-number", "red"), 
	twentySeven(27, "3*3*3", "yellow"),
	thirtyThree(33, "master-number", "red"),
	thirtyFour(34, "fibonacci-number", "green"),
	fourtyFour(44, "master-number", "red"),
	fiftyFive(55, "master-number", "red"),
	sixtyFour(64, "4*4*4", "yellow"),
	sixtySix(66, "master-number", "red"),
	seventyTwo(72, "72", "purple"),
	seventySeven(77, "master-number", "red"),
	eightyEight(88, "master-number", "red"),
	eightyNine(89, "fibonacci-number", "green"),
	nintyThree(93, "93", "purple"),
	nintyNine(99, "master-number", "red"),
	oneHundred(100, "100", "blue"),
	oneHundredAndEleven(111, "super-master-number", "orange"),
	twoHundredAndTwentyFive(125, "5*5*5", "yellow"),
	oneHundredAndFourtyFour(144, "fibonacci-number", "green"),
	oneHundredAndFiftySix(156, "77+((7+7)/7)+77", "purple"),
	twoHundred(200, "200", "blue"),
	twoHundredAndSixteen(216, "6*6*6", "yellow"),
	twoHundredAndTwentyTwo(222, "super-master-number", "orange"),
	twoHundredAndThirtyThree(233, "fibonacci-number", "green"),
	threeHundred(300, "300", "blue"),
	threeHundredAndTwentyTwo(322, "spring equinox", "purple"),
	threeHundredAndThirtyThree(333, "super-master-number", "orange"),
	threeHundredAndFourtyThree(343, "7*7*7", "yellow"),
	threeHundredAndSeventySeven(377, "fibonacci-number", "green"),
	fourHundred(400, "400", "blue"),
	fourHundredAndEighteen(418, "number of the great work", "purple"),
	fourHundredAndNineteen(419, "bicycle day", "purple"),
	fourTwenty(420, "4:20", "purple"),
	fourHundredAndFourtyFour(444, "super-master-number", "orange"),
	fiveHundred(500, "500", "blue"),
	fiveHundredAndTwelve(512, "8*8*8", "yellow"),
	fiveHundredAndFiftyFive(555, "super-master-number", "orange"),
	sixHundred(600, "600", "blue"),
	sixHundredAndTen(610, "fibonacci-number", "green"),
	sixHundredAndSixtySix(666, "super-master-number", "orange"),
	sixHundredAndNintySix(696, "number of the word of flight", "purple"),
	sevenHundred(700, "700", "blue"),
	sevenHundredAndTwentyNine(729, "9*9*9", "yellow"),
	sevenHundredAndSeventySeven(777, "super-master-number", "orange"),
	eightHundred(800, "800", "blue"),
	eightHundredAndEightyEight(888, "super-master-number", "orange"),
	nineHundred(900, "900", "blue"),
	nineHundredAndEightySeven(987, "fibonacci-number", "green"),
	nineHundredAndNintyNine(999, "super-master-number", "orange"),
	oneThousand(1000, "10*10*10", "yellow"),
	oneThousandOneHundredAndEleven(1111, "hyper-master-number", "orange"),
	oneThreeThreeOne(1331, "11*11*11", "yellow"),
	oneFiveNineSeven(1597, "fibonacci-number", "green"),
	oneSevenTwoEight(1728, "12*12*12", "yellow"),
	twoThousand(2000, "2000", "blue"),
	twoOneNineSeven(2197, "13*13*13", "yellow"),
	twoTwoTwoTwo(2222, "hyper-master-number", "orange"),
	twoFiveEightFour(2584, "fibonacci-number", "green"),
	twoSevenFourFour(2744, "14*14*14", "yellow"),
	threeThousand(3000, "3000", "blue"),
	threeThreeeThreeThreee(3333, "hyper-master-number", "orange"),
	threeThreeSevenFive(3375, "15*15*15", "yellow"),
	fourThousand(4000, "4000", "blue"),
	fourZeroNineSix(4096, "16*16*16", "yellow"),
	fourOneEightOne(4181, "fibonacci-number", "green"),
	fourFourFourFour(4444, "hyper-master-number", "orange"),
	fourNineOneThree(4913, "17*17*17", "yellow"),
	fiveThousand(5000, "5000", "blue"),
	fiveFiveFiveFive(5555, "hyper-master-number", "orange"),
	fiveEightThreeTwo(5832, "18*18*18", "yellow"),
	sixThousand(6000, "6000", "blue"),
	sixSixSixSix(6666, "hyper-master-number", "orange"),
	sixSevenSixFive(6765, "fibonacci-number", "green"),
	sixEightFiveNine(6859, "19*19*19", "yellow"),
	sevenThousand(7000, "7000", "blue"),
	sevenSevenSevenSeven(7777, "hyper-master-number", "orange"),
	EightThousand(8000, "20*20*20", "yellow"),
	eightEightEightEight(8888, "hyper-master-number", "orange"),
	nineThousand(9000, "9000", "blue"),
	overNineThousand(9001, "IT'S OVER 9000!!", "purple"),
	nineTwoSixOne(9261, "21*21*21", "yellow"),
	nineNineNineNine(9999, "hyper-master-number", "orange");

	private int number;	
	private String numberQuality;
	private String color;
	
	private Highlight(int number, String numberQuality, String color) {
		this.setNumber(number);
		this.setNumberQuality(numberQuality);
		this.setColor(color);
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumberQuality(String numberQuality) {
		this.numberQuality = numberQuality;
	}

	public String getNumberQuality() {
		return numberQuality;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}
}