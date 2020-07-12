package com.stormnet.figuresfx.controller;

import com.stormnet.figuresfx.drawUtils.Drawer;
import com.stormnet.figuresfx.figures.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.apache.log4j.Logger;

import com.stormnet.figuresfx.customExceptions.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class MainScreenViewController implements Initializable {

    private ArrayList<Figure> figures;
    private Figure lastFigure;
    private Random random;
    private static final Logger logger = Logger.getLogger(MainScreenViewController.class);

    @FXML
    private Canvas canvas;

    @FXML
    private Button buttonUndo;

    @FXML
    public void onUndoTyping() throws noUndoTyping {
        try {
            if (figures.size() > 0) {
                figures.remove(figures.size() - 1);
                repaint();
                throw new noUndoTyping("Unknown figure type!");
            }
        } catch (noUndoTyping e) {
            logger.error("No Figure to cancel!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        logger.info("App is initialized!");
        figures = new ArrayList<>();
        random = new Random(System.currentTimeMillis());
    }

    private void addFigure(Figure figure) {
        figures.add(figure);
    }

    private Figure createFigure(double x, double y) throws noFigureException {

        Figure figure = null;

        switch (random.nextInt(5)) {
            case Figure.FIGURE_TYPE_CIRCLE:
                figure = new Circle(x, y, random.nextInt(10), Color.GREEN, random.nextInt(50));
                logger.info("Circle is drawn");
                break;
            case Figure.FIGURE_TYPE_RECTANGLE:
                figure = new Rectangle(x, y, random.nextInt(10), Color.PINK, random.nextInt(45), random.nextInt(70));
                logger.info("Rectangle is drawn");
                break;
            case Figure.FIGURE_TYPE_TRIANGLE:
                figure = new Triangle(x, y, random.nextInt(10), Color.YELLOW, random.nextInt(60));
                logger.info("Triangle is drawn");
                break;
            case Figure.FIGURE_TYPE_STAR:
                figure = new Star(x, y, random.nextInt(10), Color.RED, random.nextInt(40), random.nextInt(9), random.nextInt(10));
                logger.info("Star is drawn");
                break;
            default:
                throw new noFigureException("Unknown figure type!");
        }
        return figure;
    }

    private void repaint() {
        canvas.getGraphicsContext2D().clearRect(0, 0, 1024, 600);//чистка канвы
        Drawer<Figure> drawer = new Drawer<>(figures);
        drawer.draw(canvas.getGraphicsContext2D());
    }

    @FXML
    private void onMouseClicked(MouseEvent mouseEvent) {
        try {
            addFigure(createFigure(mouseEvent.getX(), mouseEvent.getY()));
            repaint();
        } catch (noFigureException e) {
            repaint();
            logger.error("Figure can not created!");
        }
    }
}