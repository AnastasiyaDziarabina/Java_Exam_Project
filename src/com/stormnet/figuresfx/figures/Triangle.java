package com.stormnet.figuresfx.figures;

import com.stormnet.figuresfx.drawUtils.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Triangle extends Figure implements Drawable {

    private double height;
    private double x1, x2, x3;
    private double y1, y2, y3;

    public Triangle(double cx, double cy, double lineWidth, Color color) {
        super(FIGURE_TYPE_TRIANGLE, cx, cy, lineWidth, color);
    }

    public Triangle(double cx, double cy, double lineWidth, Color color, double height) {
        this(cx, cy, lineWidth, color);
        this.height = height;

        x1 = cx;
        y1 = cy - height;
        x2 = cx + (4 * height / Math.sqrt(3)) / 2;
        y2 = cy - height / 4;
        x3 = cx - (4 * height / Math.sqrt(3)) / 2;
        y3 = cy - height / 4;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Triangle{");
        sb.append(", cx=").append(cx);
        sb.append(", cy=").append(cy);
        sb.append(", lineWidth=").append(lineWidth);
        sb.append(", color=").append(color);
        sb.append(", height=").append(height);
        sb.append('}');
        return sb.toString();
    }
    @Override
    public int hashCode() {
        return Objects.hash(cx,cy,lineWidth,color,height);
    }

    @Override
    public void draw(GraphicsContext gc) {
        double[] nx = new double[]{x1, x2, x3};
        double[] ny = new double[]{y1, y2, y3};

        gc.setStroke(color);
        gc.strokePolygon(nx, ny, 3);
    }
}