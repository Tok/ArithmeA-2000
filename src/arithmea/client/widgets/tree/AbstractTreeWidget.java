package arithmea.client.widgets.tree;

import arithmea.shared.qabalah.Sephira;
import arithmea.shared.qabalah.SephirothData;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.widgetideas.graphics.client.Color;
import com.google.gwt.widgetideas.graphics.client.GWTCanvas;

public abstract class AbstractTreeWidget extends Composite {
	private final VerticalPanel panel = new VerticalPanel();
	private final SephirothData sd = new SephirothData();
	
	private GWTCanvas canvas;
	
	public AbstractTreeWidget(int width, int height) {
		canvas = new GWTCanvas(width, height);
	    drawSephiroth();
	    panel.add(canvas);
		
		initWidget(panel);
		setStyleName("tree-of-life");
	}

	public GWTCanvas getCanvas() {
		return canvas;
	}

	public void drawSephiroth() {
		canvas.setStrokeStyle(Color.WHITE);
		for (Sephira sephira : Sephira.values()) {
			Color color = sd.getPosition(sephira).color;
			if (!sephira.isImplicate) {
				canvas.setLineWidth(3);
			} else {
				canvas.setLineWidth(1);				
			}
			canvas.setFillStyle(color);
			canvas.setStrokeStyle(Color.WHITE);
			
			drawArc(sd.getPosition(sephira).x,  
						sd.getPosition(sephira).y,
						SephirothData.UNIT / 2,   0, 360, false);			
			
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
	
	public void drawPath(Sephira from, Sephira to) {
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
