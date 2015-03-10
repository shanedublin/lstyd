package com.mygdx.game.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.logic.Renderable;

public abstract class Entity implements Renderable {

	private Vector2 position = new Vector2(0,0);
	private float health = 10;
	private boolean alive = true;
	private Vector2 direction;
	private Vector2 moveDirection;
	private Vector2 moveForceVector;
	private Vector2 velocity;
	private float currentSpeed;
	private float maxSpeed = 2;
	private Rectangle bounds;
	private float width = 32;
	private float height = 32;
	private float friction;
	private float moveForce;
	private boolean canAttack;
	
	public Entity(){
		position = new Vector2(0, 0);
		velocity = new Vector2(0,0);
		alive = true;
		health = 10;
//		currentSpeed = 0;
		maxSpeed = 2;

		friction = .8f;
		moveForce = 1f;
		
		bounds = new Rectangle(position.x - width/2, position.y - height/2, width, height);
	}
	
	public void movePositionX(float x){
		movePosition(x, 0);
	}
	public void movePositionY(float y){
		movePosition(0, y);
	}
	
	public void movePosition(float x,float y){
		this.position.x += x;
		this.position.y += y;
		bounds.x += x;
		bounds.y += y;
	}
	public void movePosition(Vector2 dir){
		movePosition(dir.x,dir.y);
	}
	
	public void physicsMove(){
		
		velocity.x += moveForceVector.x * moveForce;
		velocity.y += moveForceVector.y * moveForce;
		
		velocity.x = MathUtils.clamp(velocity.x, -maxSpeed, maxSpeed);
		velocity.y = MathUtils.clamp(velocity.y, -maxSpeed, maxSpeed);
		
		movePosition(velocity.x,velocity.y);
		applyFriction();
	}
	
	
	public void setMoveForceVector(Vector2 direction){
		this.moveForceVector = direction;
		
	}
	
	
	private void applyFriction(){
		velocity.x *= friction;
		velocity.y *= friction;
	}
	
	public void setPositionX(float x){
		setPosition(x, this.position.y);
	}
	public void setPositionY(float y){
		setPosition(this.position.x, y);
	}
	public void setPosition(float x, float y){
		this.position.x = x;
		this.position.y = y;
		bounds.x = position.x - width/2;
		bounds.y = position.y - height /2;
	}
	public Vector2 getPositon(){
		return position;
	}



	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}



	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		setPosition(position.x, position.y);
	}

	public float getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(float currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Vector2 getMoveDirection() {
		return moveDirection;
	}

	public void setMoveDirection(Vector2 moveDirection) {
		this.moveDirection = moveDirection;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
		this.bounds.width = width;
		
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
		this.bounds.height = height;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public float getFriction() {
		return friction;
	}

	public void setFriction(float friction) {
		this.friction = friction;
	}
	
	public void takeDamage(float damage){
		System.out.println(this.toString() + "took damage");
		health -= damage;
		if(health <= 0 ){
			alive = false;
		}
	}
}
