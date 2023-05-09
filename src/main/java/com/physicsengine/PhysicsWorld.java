package com.physicsengine;

public class PhysicsWorld {
    private static final double gravity = 9.81; // m/s^2
    private static final double restitution = 0.2;
    private static final double scale = 40.0; // pixles per meter

    private static final double windowHeight = 600.0;

    private PhysicsObject block;

    public PhysicsWorld() {
        block = new PhysicsObject(10, 4, 0, 0);
    }

    public PhysicsObject getBlock() {
        return block;
    }

    // physics simulation
    public void update(double dt) {
        // calculate for air resistance
        double p = 1.225;
        double Cd = 1.0;
        double A = 0.01;

        // coefficient of static friction
        double mu = 0.1;

        // update velocity
        block.setVy(block.getVy() + gravity * dt);

        double Fy = 0.5 * p * Math.pow(block.getVy(), 2) * Cd * A;

        // correct the direction of the drag force
        if (block.getVy() > 0)
            Fy = -Fy;

        block.setVy(block.getVy() - Fy * dt);

        // // apply static friction (assuming some horizontal motion)
        // double F_friction = mu * block.getMass() * gravity; // the frictional force
        // double ax = -F_friction / block.getMass(); // the acceleration due to
        // friction
        // block.setVx(block.getVx() + ax * dt);

        double newY = block.getY() + block.getVy() * dt;

        double vx_m_s = block.getVx() / scale;
        double vy_m_s = block.getVy() / scale;
        System.out.println(vx_m_s + ", " + vy_m_s);

        if (windowHeight - newY * scale <= 0) {
            System.out.println("checking collision");
            newY = windowHeight / scale;
            block.setVy(-restitution * block.getVy());
        }

        block.setY(newY);
    }

}
