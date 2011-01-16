package arithmea.client;

import arithmea.shared.Path;
import arithmea.shared.Sephira;
import arithmea.shared.SephirothData;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.widgetideas.graphics.client.Color;
import com.google.gwt.widgetideas.graphics.client.GWTCanvas;

public class HebrewTreeWidget extends Composite {
	private final VerticalPanel panel = new VerticalPanel();
	private final SephirothData sd = new SephirothData();
	private GWTCanvas canvas;
	
	public HebrewTreeWidget(int width, int height) {
		canvas = new GWTCanvas(width, height);
	    drawSephiroth();
	    drawAllPaths();
		panel.add(canvas);
		
		initWidget(panel);
		setStyleName("tree-of-life");
	}

	public GWTCanvas getCanvas() {
		return canvas;
	}
	
	private void drawAllPaths() {
		canvas.setLineWidth(1);
		canvas.setStrokeStyle(Color.WHITE);
		for (Path path : Path.values()) {
			drawPath(path);
		}
	}
	
	private void drawSephiroth() {
		canvas.setLineWidth(1);
//		Color color = new Color("#777777");
		Color color = Color.WHITE;
		canvas.setFillStyle(color);
		canvas.setStrokeStyle(color);
		for (Sephira sephira : Sephira.values()) {
			drawArc(sd.getPosition(sephira).x,  
						sd.getPosition(sephira).y,
						25,   0, 360, false);				
			if (!sephira.isImplicate) {
				canvas.fill();
		 	}
	 	}
	}

	private void drawArc(int x, int y, int r, int startAngle, int endAngle, boolean antiClock) {
		canvas.beginPath();
		final double start = Math.PI * (startAngle -90) / 180;
		final double end = Math.PI * (endAngle -90) / 180;
		canvas.arc(x, y, r, start, end, antiClock);
		canvas.closePath();
		canvas.stroke();
	}
	
	private void drawPath(Path path) {
		drawPath(path.from, path.to);
	}
	
	private void drawPath(Sephira from, Sephira to) {
		drawLine(
			sd.getPosition(from).x, 
			sd.getPosition(from).y, 
			sd.getPosition(to).x, 
			sd.getPosition(to).y);
	}
	
	private void drawLine(int startX, int startY, int endX, int endY) {
		canvas.beginPath();
		canvas.moveTo(startX, startY);
		canvas.lineTo(endX, endY);
		canvas.closePath();
		canvas.stroke();
	}

}
