package arithmea.client.widgets.tree;

import arithmea.shared.qabalah.Sephira;
import arithmea.shared.qabalah.SephirothData;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class AbstractTreeWidget extends Composite {
	private final VerticalPanel panel = new VerticalPanel();
	private final SephirothData sd = new SephirothData();
	
	private Canvas canvas;
	
	public AbstractTreeWidget(int width, int height) {
		canvas = Canvas.createIfSupported();
		canvas.setCoordinateSpaceHeight(height * 2);
		canvas.setCoordinateSpaceWidth(width / 4);
		
	    drawSephiroth();
	    panel.add(canvas);
		
		initWidget(panel);
		setStyleName("tree-of-life");
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void drawSephiroth() {
		Context2d ctx = canvas.getContext2d();
		
		ctx.setStrokeStyle(CssColor.make("#FFFFFF"));
//		canvas.setStrokeStyle(CssColor.WHITE);
		for (Sephira sephira : Sephira.values()) {
			CssColor color = sd.getPosition(sephira).color;
			if (!sephira.isImplicate) {
				ctx.setLineWidth(3);
			} else {
				ctx.setLineWidth(1);				
			}
			ctx.setFillStyle(color);
			ctx.setStrokeStyle(CssColor.make("#FFFFFF"));
			
			drawArc(sd.getPosition(sephira).x,  
						sd.getPosition(sephira).y,
						SephirothData.UNIT / 2,   0, 360, false);			
			
			if (!sephira.isImplicate) {
				ctx.fill();
		 	}
	 	}
	}

	private void drawArc(int x, int y, int r, int startAngle, int endAngle, boolean antiClock) {
		canvas.getContext2d().beginPath();
		final double start = Math.PI * (startAngle -90) / 180;
		final double end = Math.PI * (endAngle -90) / 180;
		canvas.getContext2d().arc(x, y, r, start, end, antiClock);
		canvas.getContext2d().closePath();
		canvas.getContext2d().stroke();
	}
	
	public void drawPath(Sephira from, Sephira to) {
		drawLine(
			sd.getPosition(from).x, 
			sd.getPosition(from).y, 
			sd.getPosition(to).x, 
			sd.getPosition(to).y);
	}
	
	private void drawLine(int startX, int startY, int endX, int endY) {
		canvas.getContext2d().beginPath();
		canvas.getContext2d().moveTo(startX, startY);
		canvas.getContext2d().lineTo(endX, endY);
		canvas.getContext2d().closePath();
		canvas.getContext2d().stroke();
	}

}
