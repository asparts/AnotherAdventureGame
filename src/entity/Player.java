package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	
	GamePanel gamePanel;
	KeyHandler keyHandler;
	
	public Player(GamePanel gamePanel, KeyHandler keyHandler) {
		this.gamePanel = gamePanel;
		this.keyHandler = keyHandler;
		
		this.setDefaultValues();
		this.getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		this.x = 100;
		this.y = 100;
		this.speed = 4;
		direction ="down";
		
	}
	
	public void getPlayerImage(){
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		
		if(keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true) {
		//If above is basically for that we're going to make player walk only if key is being pressed
		if(keyHandler.upPressed == true) {
			direction = "up";
			this.y -= this.speed;
		}
		else if(keyHandler.downPressed == true) {
			direction = "down";
			this.y += this.speed;
		}
		else if(keyHandler.leftPressed == true) {
			direction = "left";
			this.x -= this.speed;
		}
		else if(keyHandler.rightPressed == true) {
			direction = "right";
			this.x += this.speed;
		}
		
		//Down here, we're calling this 60 per second. --> increasing spritecounter --> changing image every 12 frames to make things smoother
		this.spriteCounter++;
		if(this.spriteCounter > 12) {
			if(this.spriteNumber == 1) {
				this.spriteNumber = 2;
			}
			else if(this.spriteNumber == 2) {
				this.spriteNumber = 1;
			}
			spriteCounter = 0;
		}
		}
	}
	public void draw(Graphics2D graphics2D) {
		//graphics2D.setColor(Color.white);
		//graphics2D.fillRect(this.x, this.y, gamePanel.tileSize, gamePanel.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		
		case "up":
			if(this.spriteNumber==1) {
			image = up1;
			}
			if(this.spriteNumber==2) {
			image = up2;
			}
			break;
		case "down":
			if(this.spriteNumber==1) {
				image = down1;
				}
			if(this.spriteNumber==2) {
				image = down2;
				}
			break;
		case "left":
			if(this.spriteNumber==1) {
				image = left1;
				}
			if(this.spriteNumber==2) {
					image = left2;
				}
			break;
		case "right":
			if(this.spriteNumber==1) {
				image = right1;
				}
			if(this.spriteNumber==2) {
					image = right2;
				}
			break;
		
		}
		graphics2D.drawImage(image, x, y, this.gamePanel.tileSize, this.gamePanel.tileSize, null);
		
	}
}
