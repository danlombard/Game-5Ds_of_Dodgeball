package game.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GameObject {
	
	private Handler handler;
	private Random r;
	

	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velY = 3;
		velX = 3;
	}
	

	// Create a get bounds rectangle around the player object and the enemy object
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
	
		
		// Bounce of walls factor
		if( y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if( x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		//r = new Random();
		
		// Regroup code for when enemies have passed
		
		
		handler.addObject(new Trail((int) x, (int) y, ID.Trail, handler, 16, 16, 0.05f, Color.red));
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 16, 16);
		
	}
	
	

}
