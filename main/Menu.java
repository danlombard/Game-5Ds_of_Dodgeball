package game.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import game.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private HUD hud;
	private Handler handler;
	private Random r = new Random();
	
	
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
		
	}
	
	
	// When the mouse is pressed we are storing what the x position and y position is of the click.
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		// PLAY BUTTON
		if(mouseOver(mx, my, 400, 200, 200, 64)) {
			game.gameState = STATE.Game;
			hud.resetScore();
			handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));
			for(int i = 0; i < 5; i++)
			handler.addObject(new BasicEnemy(r.nextInt((int) Game.WIDTH)-16, r.nextInt((int) Game.HEIGHT)-16, ID.BasicEnemy, handler));
		}
		
		// QUIT BUTTON
		if(mouseOver(mx, my, 400, 400, 200, 64)) {
			game.gameState = STATE.Quit;
			System.exit(1);
		}
		if(game.gameState == STATE.End) {
			if(mouseOver(mx, my, 400, 500, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		
		// HELP
		if(mouseOver(mx, my, 400, 300, 200, 64)) {
			game.gameState = STATE.Help;
			
		}
		if(game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 400, 600, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		// TRY AGAIN BUTTON
		
		}
			
		}
		
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	// This method is checking to see if the position of the mouse is within the boarders of our box.
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("aerial", 1, 50);
			Font fnt2 = new Font("aerial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Menu", 425, 150);
			
			g.setFont(fnt2);
			g.drawRect(400, 200, 200, 64);
			g.drawString("Play", 465, 240);
			
			g.setFont(fnt2);
			g.drawRect(400, 300, 200, 64);
			g.drawString("Help", 465, 340);
			
			g.setFont(fnt2);
			g.drawRect(400, 400, 200, 64);
			g.drawString("Quit", 465, 440);
			
		}else if(game.gameState == STATE.Help) {
			Font fnt = new Font("aerial", 1, 50);
			Font fnt3 = new Font("aerial", 1, 15);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Help", 425, 150);
			g.setFont(fnt3);
			g.drawString("In case it wasn't obvious enough, if you can dodge a wrench you can dodge a ball. " + hud.getScore()  , 200, 250);
			
			g.setFont(fnt);
			g.drawRect(400, 600, 200, 64);
			g.setColor(Color.WHITE);
			g.drawString("Back", 400, 640);
		}else if(game.gameState == STATE.End) {
			Font fnt = new Font("aerial", 1, 50);
			Font fnt2 = new Font("aerial", 1, 30);
			Font fnt3 = new Font("aerial", 1, 15);
			
			g.setFont(fnt);
			g.setColor(Color.RED);
			g.drawString("GAME OVER", 360, 150);
			g.setFont(fnt3);
			g.drawString("You lost with a score of " + hud.getScore() + "."  , 385, 250);
			
			g.setFont(fnt2);
			g.drawRect(400, 500, 200, 64);
			g.setColor(Color.WHITE);
			g.drawString("Try again", 430, 540);
		}
		
	}
}
