package main;


import java.util.ArrayList;

import model.*;
import processing.core.PApplet;

public class Main extends PApplet{
	
	ArrayList<Asteroid> asteroids;
	ArrayList<Bullet> bullets;
	Player player;
	Asteroid test;
	Bullet b;
	private int gamestate = 0;
	private int asteroidCount = 5;
	
	

	public static void main(String[] args) {
		PApplet.main("main.Main");
	}
	
	public void settings() {
		size(700,800);
	}
	public void setup() {
		player = new Player(100,700,50,this);
		asteroids = new ArrayList<Asteroid>();
		bullets = new ArrayList<Bullet>();
		
		for(int i = 0;i < asteroidCount ; i++) {
			asteroids.add(new Asteroid(random(width),random(-800,-200),this));
			
		}
	}


	public void draw() {
		//startscreen state
		if(gamestate == 0) {
			drawStartscreen();
			
			if(keyPressed) {
				gamestate = 1;
			}
				
			
		}
		
		
		//play state
		if(gamestate == 1) {
			
			drawGameBackground();
			
			//Bullets
			if(mousePressed) {
				bullets.add(new Bullet(this));
			}
			
			for(Bullet b: bullets) {
				b.shoot();
				if(b.getY() < 0){

				}
			}
			System.out.println(bullets.size());
			
			//draw player
			player.move();
			player.draw();
			player.scoreUp();		
			
			
			
			// for every Asteroid Object 
			for(Asteroid a:asteroids) {
				a.fall(player);
				a.draw();
				a.hit(player, a);
				if(a.getY()>height+a.getDurchmesser()) {
					a.resetAsteroid();
				}
			}
			
			if (player.getDead() == true) {
				this.gamestate = 2;
			}
		}
		
		// Gameover state
		if(gamestate == 2) {
			drawEndscreen();
			if(keyPressed && key == 'r') {
				player.reset();
				for(Asteroid a:asteroids) {
					a.reset();
				}
				gamestate = 0;
				bullets.clear();
			}
			if(keyPressed && key == 'e') {
				exit();
			}
		}	
	}
	
	
	
	private void drawStartscreen() {
		background(30);
		textSize(30);
		fill(255,255,255);
		textAlign(CENTER);
		text("Press Any Key to Start!",250,250);
		
	}

	public void drawGameBackground() {
		background(0x4e4f57);

		stroke(0x00000000);		
		line(0,696, width, 696);
		line(0,704, width, 704);
		
		stroke(0xffff0000);
		line(0,700, width, 700);
	}
	
	public void drawEndscreen() {
		background(30);
		textSize(30);
		fill(255,255,255);
		textAlign(CENTER);
		text("Your Score Was: " + player.getScore(),300,100);
		text("Press R to restart",300,200);
		text("Press E to Exit",300,300);
	}
	

}
