package game.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1752427122423642388L;
	/**
	 * 
	 */
	
	public static final int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE {
		Menu,
		Game,
		End,
		Quit,
		Help,
		Back;
	};
	
	public STATE gameState = STATE.Menu;
	
	// public Game is our constructor of the game.
	public Game() {
		
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		
		
		new Window(WIDTH, HEIGHT, "Dodge Duck Dip Dive & Dodge!", this);
		
		r = new Random();
		spawner = new Spawn(handler, hud);
		
		
		if(gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler));
			for(int i = 0; i < 5; i++)
			handler.addObject(new BasicEnemy(r.nextInt((int) WIDTH)-16, r.nextInt((int) HEIGHT)-16, ID.BasicEnemy, handler));
		
		}
		
		//handler.addObject(new BasicEnemy(r.nextInt(WIDTH),r.nextInt(HEIGHT), ID.BasicEnemy));
		//handler.addObject(new BasicEnemy(r.nextInt(WIDTH),r.nextInt(HEIGHT), ID.BasicEnemy));

		

	}
	

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// We need to now build the game loop. This will essentially be the heart beat of the game
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		
		
		
		if(gameState == STATE.Game) {
			if(!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
				
				
				if(HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					handler.clearEnemies();
					gameState = STATE.End;
				}
				
			}
			
		}else if(gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
		}
		
		
		
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect((int) 0, (int) 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(paused) {
			g.drawString("PASUED", 100, 100);
		}
		
		if(gameState == STATE.Game) {
			hud.render(g);
		}
		else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
		}else if(gameState == STATE.Back) {
			gameState = STATE.Menu;
		}
		
		g.dispose();
		bs.show();
		
	}
	
	public static float clamp( float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String args []) {
		new Game();
		
	}

}
