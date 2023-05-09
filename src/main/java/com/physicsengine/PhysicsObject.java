package com.physicsengine;

public class PhysicsObject {
    private double x, y; // position
    private double vx, vy; // velocity

    public PhysicsObject(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVy() {
        return vy;
    }

    public double getVx() {
        return vx;
    }

    public void setY(double d) {
        this.y = d;
    }

    public void setVy(double d) {
        this.vy = d;
    }

    public void setVx(double d) {
        this.vx = d;
    }

}
