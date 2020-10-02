package com.bad.code2;

/**
 * Трехмерная фигура, Куб.
 */
public class Qube implements Shape3D {

    /**
     * координаты по оси X
     */
    private Double centerX;

    /**
     * координаты по оси Y
     */
    private Double centerY;

    /**
     * координаты по оси Z
     */
    private Double centerZ;

    /**
     * размер ребра
     */
    private Double edgeSize;

    /**
     * Конструктор фигуры
     * @param centerX координаты по оси X
     * @param centerY координаты по оси Y
     * @param centerZ координаты по оси Z
     * @param edgeSize размер ребра
     */
    public Qube(Double centerX, Double centerY, Double centerZ, Double edgeSize) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = centerZ;
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

    @Override
    public Double getZ() {
        return centerZ;
    }

    /**
     * Расчёт объема куба
     * @return объем куба
     */
    @Override
    public Double getVolume() {
        return Math.pow(edgeSize, 3);
    }
}
