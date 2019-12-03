package model;

import processing.core.PApplet;


/**
 * Creates Asteroid Object the PLayer has to dodge
 * @author 183857
 *
 */
public class Asteroid extends PApplet{
	
	protected float x;
	protected float y;
	protected float durchmesser;
	PApplet w;
	protected float speed;
	private float speedIncrement = (float) 0.05;
	
	private int colorRed = (int) random(31,189);
	private int colorGreen = (int) random(18,110);
	
	
	/**
	 * Ctor for Asteroid
	 * @param x
	 * @param y
	 * @param w
	 */
	public Asteroid(float x, float y,PApplet w) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.durchmesser = random(20,150);
		
	}
	

	/**
	 * Draws Asteroid on the screen
	 */
	public void draw() {
		w.fill(colorRed,colorGreen,0);
		w.ellipse(x, y, durchmesser, durchmesser);
	}
	
	/**
	 * Makes the Asteroid Fall
	 * @param p To get the Score of the Player
	 */
	public void fall(Player p) {
		this.y = this.y + speed;
		speed = speed + speedIncrement;
		if (p.getScore()% 300 == 0) {
			speedIncrement = (float) (speedIncrement +0.01);
			System.out.println(speedIncrement);
		}
	}
	
	/**
	 * Resets the Asteroid, sets random x and y cords, and size
	 */
	public void resetAsteroid() {
		speed = 0;
		this.y = random(-1000,0-height/2);
		this.x = random(durchmesser,w.width-durchmesser);
		this.durchmesser = random(20,150);
	}
	
	/**
	 * Detects if an Asteroid comes in contact with the player
	 * @param p The Asteroid Object
	 * @param a The Player object
	 * @return 
	 */
	public boolean hit(Player p, Asteroid a) {
		if(dist(p.getX(), p.getY(), a.getX(), a.getY()) < p.getDurchmesser()/2 + a.getDurchmesser()/2) {
			p.dead(true);
			return true;
		}
		return false;
	}
	
	/**
	 * Resets the speed of the Asteroid + calls the function resetAsteroid
	 */
	public void reset() {
		speedIncrement = (float) 0.05;
		resetAsteroid();
	}
	
	
	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}


	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}


	/**
	 * @return the durchmesser
	 */
	public float getDurchmesser() {
		return durchmesser;
	}
	

	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}
}
