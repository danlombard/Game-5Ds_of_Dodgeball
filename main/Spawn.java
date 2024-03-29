package game.tutorial.main;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		
	}
	
	public void tick() {
		scoreKeep++;
		
		if(scoreKeep >= 500) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
				
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 16) , r.nextInt(Game.HEIGHT - 16), ID.FastEnemy, handler));
				
				if(hud.getLevel() == 2) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.SmartEnemy, handler));
				}
				if(hud.getLevel() == 3) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.FastEnemy, handler));
				}
				if(hud.getLevel() == 4) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.FastEnemy, handler));
				}
		}
	}
}
