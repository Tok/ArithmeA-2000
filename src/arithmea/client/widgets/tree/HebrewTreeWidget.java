package arithmea.client.widgets.tree;

import arithmea.shared.gematria.HebrewLetter;
import arithmea.shared.qabalah.HebrewPath;
import arithmea.shared.qabalah.SephirothData;

import com.google.gwt.widgetideas.graphics.client.Color;

public class HebrewTreeWidget extends AbstractTreeWidget {

	public HebrewTreeWidget(int width, int height) {
		super(width, height);
		drawAllPaths();
	}

	public void setWord(String hebrew) {
		getCanvas().clear();
		getCanvas().setLineWidth(SephirothData.UNIT / 5);
		getCanvas().setStrokeStyle(Color.WHITE);
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
		getCanvas().setLineWidth(1);
		getCanvas().setStrokeStyle(Color.WHITE);
		for (HebrewPath path : HebrewPath.values()) {
			drawPath(path);
		}
	}
	
	public void drawPath(HebrewPath path) {
		drawPath(path.from, path.to);
	}
	
}
