package game.tutorial.main;

import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

public class Player extends GameObject {
	
	Random r = new Random();
	
	Handler handler = new Handler();
	
	private Image img;
	
	

	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		
	
	}
	
	// Create a get bounds rectangle around the player object and the enemy object
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
	
	
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		//velY += 0.5f;
		
		x = Game.clamp(x, 0, Game.WIDTH-32);
		y = Game.clamp(y, 0, Game.HEIGHT-60);
		
		//handler.addObject(new Trail((int) x, (int) y, ID.Trail, handler, 32, 32, 0.1f, Color.white));
		
		
		collision();
		
	}
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) { // tempObject is now BasicEnemy
				//collision code
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
			
		}
	}
	
	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.red);
		g2d.draw(getBounds());
		
		//Image img1 = Toolkit.getDefaultToolkit().getImage("Dodgeball.jpg");
	    //g.drawImage(img1, 100, 100, null);
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, 32, 32);
		
	}
	
	
	
}	
