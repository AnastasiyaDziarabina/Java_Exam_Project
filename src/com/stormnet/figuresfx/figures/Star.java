package com.stormnet.figuresfx.figures;

import com.stormnet.figuresfx.drawUtils.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Star extends Figure implements Drawable {

    private double[] x;
    private double[] y;
    private double height;
    private double numRays;
    private double startAngleR;
    private double outerRadius;
    private double innerRadius;
    private double koordX;
    private double koordY;

    public Star(double cx, double cy, double lineWidth, Color color) {
        super(FIGURE_TYPE_STAR, cx, cy, lineWidth, color);
    }

    public Star(double cx, double cy, double lineWidth, Color color, double height, double innerRadius, double numRays) {

        this(cx, cy, lineWidth, color);
        this.height = height;
        this.koordX = cx;
        this.koordY = cy;
        this.innerRadius = innerRadius;
        this.outerRadius = innerRadius * 2.63;
        this.numRays = numRays < 5 ? 10 : numRays;
        this.startAngleR = Math.toRadians(-18);

        double deltaAngleR = Math.PI / numRays;
        double[] x = new double[(int) numRays * 2];
        double[] y = new double[(int) numRays * 2];

        for (int i = 0; i < numRays * 2; i++) {
            double angleR = startAngleR + i * deltaAngleR;
            double ca = Math.cos(angleR);
            double sa = Math.sin(angleR);
            double relX = ca;
            double relY = sa;
            if ((i & 1) == 0) {
                relX *= outerRadius;
                relY *= outerRadius;
            } else {
                relX *= innerRadius;
                relY *= innerRadius;
            }
            x[i] = koordX + relX;
            y[i] = koordY + relY;
        }

        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Star{");
        sb.append(", cx=").append(cx);
        sb.append(", cy=").append(cy);
        sb.append(", lineWidth=").append(lineWidth);
        sb.append(", color=").append(color);
        sb.append(", height=").append(height);
        sb.append(", innerRadius=").append(innerRadius);
        sb.append(", numRays=").append(numRays);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(cx, cy, lineWidth, color, height, innerRadius, numRays);
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setStroke(color);
        gc.strokePolygon(x, y, x.length);
    }
}