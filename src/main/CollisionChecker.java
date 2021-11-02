package main;

import entity.Entity;

public class CollisionChecker {

	
	GamePanel gamePanel;
	
	public CollisionChecker(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void CheckTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x + 25;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y +25;
		int entityBotWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height; //TODO: check bottom collision to make moving more smooth.. Now when movin right or left when collidin with bottom, we rly cant move..
		
		
		
		int entityLeftCol = entityLeftWorldX/gamePanel.tileSize;
		int entityRightCol = entityRightWorldX/gamePanel.tileSize;
		int entityTopRow = entityTopWorldY/gamePanel.tileSize;
		int entityBotRow = entityBotWorldY/gamePanel.tileSize;
		
		int tileNumber1, tileNumber2;
		
		switch(entity.direction) {
			
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gamePanel.tileSize;
			tileNumber1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow]; //left side
			tileNumber2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow]; //right side
				if(gamePanel.tileManager.tile[tileNumber1].collision == true || gamePanel.tileManager.tile[tileNumber2].collision == true) {
					entity.collisionOn=true;
				}
			break;
			
		case "down":
			entityBotRow = (entityBotWorldY - entity.speed)/gamePanel.tileSize;
			tileNumber1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBotRow]; //left side
			tileNumber2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityBotRow]; //right side
				if(gamePanel.tileManager.tile[tileNumber1].collision == true || gamePanel.tileManager.tile[tileNumber2].collision == true) {
					entity.collisionOn=true;
				}
			
			break;
		case "left":
			
			entityLeftCol = (entityLeftWorldX - entity.speed)/gamePanel.tileSize;
			tileNumber1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow]; //left side
			tileNumber2 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBotRow]; //right side
				if(gamePanel.tileManager.tile[tileNumber1].collision == true || gamePanel.tileManager.tile[tileNumber2].collision == true) {
					entity.collisionOn=true;
				}
			
			break;
		case "right":
			entityRightCol = (entityRightWorldX - entity.speed)/gamePanel.tileSize;
			tileNumber1 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow]; //left side
			tileNumber2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityBotRow]; //right side
				if(gamePanel.tileManager.tile[tileNumber1].collision == true || gamePanel.tileManager.tile[tileNumber2].collision == true) {
					entity.collisionOn=true;
				}
			break;
		}
		
	}
	
}
