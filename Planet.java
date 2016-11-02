public class Planet {
	public double x, y, xVelocity, yVelocity, mass, xNetForce, yNetForce, xAccel, yAccel, G=6.67e-11;
	public String img;

	public Planet(double x, double y, double xVelocity, double yVelocity, double mass, String img) {
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.mass = mass;
		this.img = img;
	}

	public double calcDistance(Planet other) {
		double dx = x - other.x;
		double dy = y - other.y;
		return Math.sqrt(dx*dx + dy*dy);
	}

	public double calcPairwiseForce(Planet other) {
		return G * this.mass * other.mass / (calcDistance(other)*calcDistance(other));
	}

	public double calcPairwiseForceX(Planet other) {
		double dx = other.x - this.x;
		return calcPairwiseForce(other)*(dx/calcDistance(other));
	}

	public double calcPairwiseForceY(Planet other) {
		double dy = other.y - this.y;
		return calcPairwiseForce(other)*(dy/calcDistance(other));
	}

	public void setNetForce(Planet[] planets) {
		for (Planet p: planets) {
			if (p != this) {
				xNetForce += calcPairwiseForceX(p);
				yNetForce += calcPairwiseForceY(p);
			}
		}
	}

	public void draw() {
		StdDraw.picture(x, y, "images/"+img);
	}

	public void update(double dt) {
		xAccel = xNetForce/mass;
		yAccel = yNetForce/mass;
		xVelocity += dt * xAccel;
		yVelocity += dt * yAccel;
		x += dt * xVelocity;
		y += dt * yVelocity;
	}
}