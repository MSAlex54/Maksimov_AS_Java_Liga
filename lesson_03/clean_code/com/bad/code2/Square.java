package com.bad.code2;


/**
 * Двумерная фигура, Квадрат
 */
public class Square implements Shape2D {


    /**
     * координаты по оси X
     */
    private Double centerX;

    /**
     * координаты по оси Y
     */
    private Double centerY;

    /**
     * размер ребра
     */
    private Double edgeSize;


    /**
     * Конструктор фигуры
     *
     * @param centerX  координаты по оси X
     * @param centerY  координаты по оси Y
     * @param edgeSize размер ребра
     */
    public Square(Double centerX, Double centerY, Double edgeSize) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.edgeSize = edgeSize;
    }


    @Override
    public Double getX() {
        return centerX;
    }

    @Override
    public Double getY() {
        return centerY;
    }

    public Double getEdgeSize() {
        return edgeSize;
    }

    /**
     * Вычисление периметра фигуры
     *
     * @return Периметр
     */
    @Override
    public Double getPerimeter() {
        return edgeSize * edgeSize;
    }
}
