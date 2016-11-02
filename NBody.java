public class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		In file = new In(filename);
		int num_planets = file.readInt();
		double radius = file.readDouble();

		Planet[] planets = new Planet[num_planets];
		for (int i = 0; i < num_planets; i++) {
			planets[i] = getPlanet(file);
		}
		StdDraw.setCanvasSize(1036, 518);
		StdDraw.setScale(-radius, radius);

		
		for (double t = 0; t < T; t += dt) {
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p: planets) {
				p.xNetForce = 0;
				p.yNetForce = 0;
				p.setNetForce(planets);
				p.update(dt);
				p.draw();
			}
			StdDraw.show(10);
		}
		StdOut.printf("%d\n", num_planets);
		StdOut.printf("%.2e\n", radius);
		for (Planet p: planets) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                   p.x, p.y, p.xVelocity, p.yVelocity, p.mass, p.img);
		}		
	}

	public static Planet getPlanet(In file) {
		double x = file.readDouble();
		double y = file.readDouble();
		double xVelocity = file.readDouble();
		double yVelocity = file.readDouble();
		double mass = file.readDouble();
		String img = file.readString();
		return new Planet(x, y, xVelocity, yVelocity, mass, img);
	}
}