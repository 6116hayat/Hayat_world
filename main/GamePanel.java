package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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

    // FPS
    int FPS = 60;    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //Setting Players Default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    //Contructs of Game Panel
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread () {

        gameThread = new Thread (this);
        gameThread.start();
    }

    @Override
    public void run() {

        // Creating Two Game Loops for the game
        // 1. Sleep Method
        // 2. Delta Method (We Will be using this)

        double drawInterval = 1000000000/FPS ;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime ;

        while(gameThread!= null){

            currentTime = System.nanoTime();
            delta += (currentTime  - lastTime)/drawInterval;

            lastTime = currentTime ;

            if (delta >= 1 ){
                update();
                repaint();
                delta--;
            }

        }
    }

    // Creating the method to update the game everytime 
    public void update(){

        if (keyH.upPressed == true){
            playerY -= playerSpeed;
        }
        else if (keyH.downPressed == true){
            playerY += playerSpeed;
        }
        else if (keyH.leftPressed == true){
            playerX -= playerSpeed;
        }
        else if (keyH.rightPressed == true){
            playerX += playerSpeed;
        }
    }

    // Creating  the method to paint the component again and again
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }
}