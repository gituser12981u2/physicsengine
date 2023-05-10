package com.physicsengine;

import java.util.List;

public class PhysicsWorld {
    private static final double gravity = 9.81; // m/s^2
    private static final double restitution = 1;
    private static final double scale = 40.0; // pixles per meter

    private static final double windowHeight = 600.0;

    private List<PhysicsObject> objects;

    private PhysicsObject block;
    private PhysicsObject circle;

    public PhysicsWorld() {
        block = new PhysicsObject(10, 4, 0, 0, 10);
        circle = new PhysicsObject(5, 2, 0, 0, 5);
        // objects = new ArrayList<>();
    }

    public PhysicsObject getBlock() {
        return block;
    }

    public PhysicsObject getCircle() {
        return circle;
    }

    public void update(double dt) {
        updateObject(block, dt);
        updateObject(circle, dt);
    }

    // physics simulation
    public void updateObject(PhysicsObject object, double dt) {
        // RK4
        double k1y = dt * object.getVy();
        double k1vy = dt * accelerationY(object.getY(), object.getVy(), object.getMass());

        double k2y = dt * (object.getVy() + 0.5 * k1vy);
        double k2vy = dt * accelerationY(object.getY() + 0.5 * k1y, object.getVy() + 0.5 * k1vy, object.getMass());

        double k3y = dt * (object.getVy() + 0.5 * k2vy);
        double k3vy = dt * accelerationY(object.getY() + 0.5 * k2y, object.getVy() + 0.5 * k2vy, object.getMass());

        double k4y = dt * (object.getVy() + k3vy);
        double k4vy = dt * accelerationY(object.getY() + k3y, object.getVy() + k3vy, object.getMass());

        double newY = object.getY() + (k1y + 2 * k2y + 2 * k3y + k4y) / 6.0;
        double newVy = object.getVy() + (k1vy + 2 * k2vy + 2 * k3vy + k4vy) / 6.0;

        boolean hasCollided = false;

        double frameRate = 60.0;

        // // update velocity
        // object.setVy(newVy);

        // collision handling
        if (object.getVy() > 0) {
            double tCollision = (windowHeight / scale - object.getY()) / object.getVy();
            if (tCollision < dt) {
                System.out.println("collided");
                System.out.println("Pre-collision: " + object.getVy() / scale * frameRate);

                // adjust the position and velocity at collision
                object.setY(object.getY() + object.getVy() * tCollision);
                double vyCollision = object.getVy()
                        + accelerationY(object.getY(), object.getVy(), object.getMass()) * tCollision;

                object.setVy(-restitution * vyCollision);

                hasCollided = true;
                System.out.println("Post-collision: " + object.getVy() / scale * frameRate);

                // update the pos for the rest of the fram
                double tRest = dt - tCollision;
                double ayRest = accelerationY(object.getY(), object.getVy(), object.getMass());
                object.setY(object.getY() + object.getVy() * tRest);
                object.setVy(object.getVy() + ayRest * tRest);
            }
            // } else {
            // object.setY(newY);
            // object.setVy(newVy);
        }

        if (!hasCollided) {
            object.setY(newY);
            object.setVy(newVy);

            double vx_m_s = object.getVx() / scale * frameRate;
            double vy_m_s = object.getVy() / scale * frameRate;

            System.out.println(vx_m_s + ", " + vy_m_s);
        }

    }

    // Returns the vertical acceleration of the block, given its vertical velocity.
    private double accelerationY(double y, double vy, double mass) {
        double p = 1.225; // air density
        double Cd = 1.0; // drag coefficient
        double A = 0.01; // cross-sectional area

        double Fy = 0.5 * p * Math.pow(vy, 2) * Cd * A;

        // correct for direction of drag
        if (vy < 0)
            Fy = -Fy;

        double ay = gravity - Fy / mass;
        return ay;
    }

}
