package arithmea.client.widgets.tree;

import arithmea.shared.gematria.LatinLetter;
import arithmea.shared.qabalah.LatinPath;

import com.google.gwt.widgetideas.graphics.client.Color;

public class LatinTreeWidget extends AbstractTreeWidget {
	
	public LatinTreeWidget(int width, int height) {
		super(width, height);
		drawAllPaths();
	}

	public void setWord(String latin) {
		getCanvas().clear();
		getCanvas().setLineWidth(10);
		getCanvas().setStrokeStyle(Color.WHITE);
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
		getCanvas().setLineWidth(1);
		getCanvas().setStrokeStyle(Color.WHITE);
		for (LatinPath path : LatinPath.values()) {
			drawPath(path);
		}
	}
	
	public void drawPath(LatinPath path) {
		drawPath(path.from, path.to);
	}

}
