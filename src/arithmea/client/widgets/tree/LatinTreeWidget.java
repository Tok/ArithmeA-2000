package arithmea.client.widgets.tree;

import arithmea.shared.gematria.LatinLetter;
import arithmea.shared.qabalah.LatinPath;
import arithmea.shared.qabalah.SephirothData;

import com.google.gwt.canvas.dom.client.CssColor;

public class LatinTreeWidget extends AbstractTreeWidget {
	
	public LatinTreeWidget(int width, int height) {
		super(width, height);
		drawAllPaths();
	}

	public void setWord(String latin) {
		getCanvas().getContext2d().clearRect(0, 0, getCanvas().getCoordinateSpaceWidth(), getCanvas().getCoordinateSpaceHeight());
		getCanvas().getContext2d().setLineWidth(SephirothData.UNIT / 5);
		getCanvas().getContext2d().setStrokeStyle(CssColor.make("#FFFFFF"));
		for (LatinLetter letter : LatinLetter.values()) {
			CharSequence c = String.valueOf(letter.character);
			if (latin.contains(c)) {
				drawPath(LatinPath.valueOf(letter.name()));
			}
		}
		
		drawAllPaths();
		drawSephiroth();
	}
	
	private void drawAllPaths() {
		getCanvas().getContext2d().setLineWidth(1);
		getCanvas().getContext2d().setStrokeStyle(CssColor.make("#FFFFFF"));
		for (LatinPath path : LatinPath.values()) {
			drawPath(path);
		}
	}
	
	public void drawPath(LatinPath path) {
		drawPath(path.from, path.to);
	}

}
