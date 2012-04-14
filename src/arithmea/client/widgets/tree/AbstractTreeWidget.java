package arithmea.client.widgets.tree;

import arithmea.shared.qabalah.Sephira;
import arithmea.shared.qabalah.SephirothData;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class AbstractTreeWidget extends Composite {
    private static final int DEGREES_IN_CIRCLE = 360;
    private final VerticalPanel panel = new VerticalPanel();
    private final SephirothData sd = new SephirothData();
    private Canvas canvas;

    public AbstractTreeWidget(final int width, final int height) {
        canvas = Canvas.createIfSupported();
        canvas.setCoordinateSpaceHeight(height * 2);
        canvas.setCoordinateSpaceWidth(width / 4);
        drawSephiroth();
        panel.add(canvas);
        initWidget(panel);
        setStyleName("tree-of-life");
    }

    public final Canvas getCanvas() {
        return canvas;
    }

    public final void drawSephiroth() {
        Context2d ctx = canvas.getContext2d();
        ctx.setStrokeStyle(CssColor.make("#FFFFFF"));
        for (Sephira sephira : Sephira.values()) {
            CssColor color = sd.getPosition(sephira).getColor();
            if (!sephira.isImplicate()) {
                ctx.setLineWidth(3);
            } else {
                ctx.setLineWidth(1);
            }
            ctx.setFillStyle(color);
            ctx.setStrokeStyle(CssColor.make("#FFFFFF"));
            drawArc(sd.getPosition(sephira).getX(), sd.getPosition(sephira).getY(), SephirothData.UNIT / 2, 0, DEGREES_IN_CIRCLE, false);
            if (!sephira.isImplicate()) {
                ctx.fill();
            }
        }
    }

    private void drawArc(final int x, final int y, final int r, final int startAngle, final int endAngle, final boolean antiClock) {
        canvas.getContext2d().beginPath();
        final double start = Math.PI * (startAngle - (DEGREES_IN_CIRCLE / 4)) / (DEGREES_IN_CIRCLE / 2);
        final double end = Math.PI * (endAngle - (DEGREES_IN_CIRCLE / 4)) / (DEGREES_IN_CIRCLE / 2);
        canvas.getContext2d().arc(x, y, r, start, end, antiClock);
        canvas.getContext2d().closePath();
        canvas.getContext2d().stroke();
    }

    public final void drawPath(final Sephira from, final Sephira to) {
        drawLine(sd.getPosition(from).getX(), sd.getPosition(from).getY(), sd.getPosition(to).getX(), sd.getPosition(to).getY());
    }

    private void drawLine(final int startX, final int startY, final int endX, final int endY) {
        canvas.getContext2d().beginPath();
        canvas.getContext2d().moveTo(startX, startY);
        canvas.getContext2d().lineTo(endX, endY);
        canvas.getContext2d().closePath();
        canvas.getContext2d().stroke();
    }

}
