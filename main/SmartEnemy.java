package game.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class SmartEnemy extends GameObject {
	
	private Handler handler;
	private Random r;
	private GameObject player;
	
	

	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
		}
		velY = 100;
		velX = 100;
	}
	

	// Create a get bounds rectangle around the player object and the enemy object
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
	
		float diffx = x - player.getX();
		float diffy = y - player.getY();
		float distance = (float) Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY())*(y - player.getY()));
		
		velX = (float) ((-1.0/distance) * 2 * diffx);
		velY = (float)((-1.0/distance) * 2 * diffy);
		
		// Bounce of walls factor
		if( y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if( x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		//r = new Random();
		
		// Regroup code for when enemies have passed
		
		
		handler.addObject(new Trail((int) x, (int) y, ID.Trail, handler, 16, 16, 0.05f, Color.GREEN));
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int) x, (int) y, 16, 16);
		
	}
	
	

}

