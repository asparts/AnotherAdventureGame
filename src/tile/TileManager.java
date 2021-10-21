package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gamePanel;
	Tile[] tile;
	
	int mapTileNumber[][];
	
	public TileManager(GamePanel gamePanel) {
		
		this.gamePanel = gamePanel;
		tile = new Tile[10];
		mapTileNumber = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
		
		
		getTileImage();
		loadMap();
	}
	
	public void getTileImage() {
		
		try {
			tile[0] = new Tile();
			tile[0].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			tile[2] = new Tile();
			tile[2].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap() {
		try {
			
			InputStream inputStream = getClass().getResourceAsStream("/maps/tilemap1.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			
			int col = 0;
			int row = 0;
			
			while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
				
				String line = br.readLine();
				
				while(col < gamePanel.maxScreenCol) {
					
					String numbers[] = line.split(" ");
					
					int number = Integer.parseInt(numbers[col]);
					
					mapTileNumber[col][row] = number;
					col++;
					
				}
				if(col == gamePanel.maxScreenCol) {
					col = 0;
					row++;
				}
				
			}
			br.close();
		}catch(Exception e) {
			
		}
	}
	
	
	public void draw(Graphics2D g2) {

		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
			
			int tileNumber = mapTileNumber[col][row];
			g2.drawImage(tile[tileNumber].tileImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
			col++;
			
			x+= gamePanel.tileSize;
			
			if(col == gamePanel.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gamePanel.tileSize;
			}
			
		}
		
		
		
	}
	
}
