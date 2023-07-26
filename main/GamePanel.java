package main;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
    //Screen Settings
    final int originalTileSize = 16; // 16x16 tile size
    final int scale = 3; // the size should be clear

    public final int tileSize = originalTileSize * scale; // 48x48 are the title size increased 
    public  final int maxScreenCol = 16; // Number of columns
    public final int maxScreenRow = 12; // number of rows
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    Thread gameThread;

    //Contructs of Game Panel
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread () {

        gameThread = new Thread (this);
        gameThread.start();
    }

    @Override
    public void run() {
    }
}