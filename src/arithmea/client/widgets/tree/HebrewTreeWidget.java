package arithmea.client.widgets.tree;

import arithmea.shared.gematria.HebrewLetter;
import arithmea.shared.qabalah.HebrewPath;
import arithmea.shared.qabalah.SephirothData;

import com.google.gwt.canvas.dom.client.CssColor;

public class HebrewTreeWidget extends AbstractTreeWidget {

	public HebrewTreeWidget(int width, int height) {
		super(width, height);
		drawAllPaths();
	}

	public void setWord(String hebrew) {
		getCanvas().getContext2d().clearRect(0, 0, getCanvas().getCoordinateSpaceWidth(), getCanvas().getCoordinateSpaceHeight());
		getCanvas().getContext2d().setLineWidth(SephirothData.UNIT / 5);
		getCanvas().getContext2d().setStrokeStyle(CssColor.make("#FFFFFF"));
		for (HebrewLetter letter : HebrewLetter.values()) {
			CharSequence c = String.valueOf(letter.hebrew);
			if (hebrew.contains(c)) {
				drawPath(HebrewPath.valueOf(letter.name()));
			}
		}
		
		drawAllPaths();
		drawSephiroth();
	}
	
	private void drawAllPaths() {
		getCanvas().getContext2d().setLineWidth(1);
		getCanvas().getContext2d().setStrokeStyle(CssColor.make("#FFFFFF"));
		for (HebrewPath path : HebrewPath.values()) {
			drawPath(path);
		}
	}
	
	public void drawPath(HebrewPath path) {
		drawPath(path.from, path.to);
	}
	
}
