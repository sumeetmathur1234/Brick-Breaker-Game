package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener , ActionListener {
   Random rand = new Random();
   private boolean play = false;
   private int score = 0;
   private int bricks = 40; //number of bricks
   private Timer time;
   private int delay = 2;
   private int playerX = rand.nextInt(550);
   private int ballposX = rand.nextInt(550);
   private int ballposY = 350 + rand.nextInt(20);
   private int ballXdir = -1;
   private int ballYdir = -2;
   private Mapgenerator map;

   public Gameplay(){
       map = new Mapgenerator(4,10);
       addKeyListener(this);
       setFocusable(true);
       setFocusTraversalKeysEnabled(false);
       time = new Timer(delay,this);
       time.start();
   }
public void paint(Graphics g){
       //background
       g.setColor(new Color(0, 0, 0, 255));
       g.fillRect(0,0,692,592);

       //drawing map
      map.draw((Graphics2D) g);
       //border
//       g.setColor(new Color(255, 255, 255));
//       g.fillRect(0,0,4,600);
//       g.fillRect(0,0,700,4);
//       g.fillRect(680,0,4,600);
       //score
       g.setColor(Color.orange);
       g.setFont(new Font("Monospaced",Font.BOLD,25));
       g.drawString("Score:"+score,535,30);
       //player
       g.setColor(new Color(193, 0, 15));
       g.fillRect(playerX,550,120,15);
       //ball
       g.setColor(new Color(46, 2, 143));
       g.fillOval(ballposX,ballposY,20,20);
       //game completed
       if(bricks<=0){
           play = false;
           ballYdir =0;
           ballXdir =0;
           g.setColor(Color.blue);
           g.setFont(new Font("Monospaced",Font.BOLD,25));
           g.drawString("You Won",175,270);

           g.setColor(Color.blue);
           g.setFont(new Font("Monospaced",Font.BOLD,25));
           g.drawString("Score:"+score,175,290);

           g.setColor(Color.blue);
           g.setFont(new Font("Monospaced",Font.BOLD,25));
           g.drawString("Press ENTER for new game",175,310);
       }
       //game over
      if(ballposY>570){
          play = false;
          ballYdir =0;
          ballXdir =0;
          g.setColor(Color.red);
          g.setFont(new Font("Monospaced",Font.BOLD,25));
          g.drawString("Game Over",175,270);
          g.setColor(Color.red);
          g.setFont(new Font("Monospaced",Font.BOLD,25));
          g.drawString("Score:"+score,175,290);
          g.setColor(Color.red);
          g.setFont(new Font("Monospaced",Font.BOLD,25));
          g.drawString("Press ENTER for new game",175,310);
      }
       g.dispose();
    }


        @Override
    public void actionPerformed(ActionEvent e) {
       time.start();
       if(play){
           if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))) ballYdir = -ballYdir;
        A:  for(int i = 0;i<map.map.length;i++){
              for(int j= 0;j<map.map[0].length;j++){
                  if(map.map[i][j]>0){
                      int brickX = j*map.brickwidth +70;
                      int brickY = i*map.brickheight + 50;
                      int brickWidth = map.brickwidth;
                      int brickHeight = map.brickheight;
                      Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                      Rectangle ballrect = new Rectangle(ballposX,ballposY,20,20);
                      Rectangle brickrect = rect;
                      if(ballrect.intersects(brickrect)){
                          map.setBrickValue(0,i,j);
                          bricks--;
                          score += 5;
                          if(ballposX +19 <=brickrect.x || ballposX +1 >=brickrect.x + brickrect.width){
                              ballXdir = -ballXdir;

                          }
                          else{
                              ballYdir = -ballYdir;
                          }
                          break A;
                      }
                  }
              }
          }
           ballposX += ballXdir;
           ballposY += ballYdir;
           if(ballposX < 0){
               ballXdir = -ballXdir;
           }
           if(ballposY<0){
               ballYdir = -ballYdir;
           }
           if(ballposX>670){
               ballXdir = -ballXdir;
           }
       }
       repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_RIGHT){
        if(playerX>= 570){
            playerX = 570;
        }
        else{
            moveRight();
        }
    }
    if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX< 10){
                playerX = 0;
            }
            else{
                moveLeft();
            }
    }
    if(e.getKeyCode() == KeyEvent.VK_ENTER){
        if(!play) {
            if (ballposY > 570 || bricks <= 0) {
                score = 0;
                ballXdir = -1;
                ballYdir = -2;
                ballposY = 350 + rand.nextInt(20);
                ballposX = rand.nextInt(550);
                bricks = 21;
                map = new Mapgenerator(4, 10);
                repaint();
            }
        }
    }
    }
    public void moveRight(){
        play = true;
        playerX += 20;
    }
    public void moveLeft() {
        play = true;
        playerX -= 20;
    }
}

