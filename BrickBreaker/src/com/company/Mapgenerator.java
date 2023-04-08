package com.company;

import java.awt.*;

public class Mapgenerator {

        public int map[][];
        public int brickwidth;
        public int brickheight;
        public Mapgenerator(int row, int col){
            map = new int[row][col] ;
            for(int i= 0;i<map.length;i++){
                for(int j= 0;j<map[0].length;j++){
                    map[i][j]= 1;
                }
            }
            brickwidth = 540/col;
            brickheight = 150/row;
        }
        public void draw(Graphics2D g){
            for(int i= 0;i<map.length;i++){
                for(int j= 0;j<map[0].length;j++){
                    if(map[i][j]>0){
                        g.setColor(new Color(189, 182, 176));
                        g.fillRect(j*brickwidth+70,i*brickheight+50,brickwidth,brickheight);
                        g.setStroke(new BasicStroke(7));
                        g.setColor(new Color(0, 0, 0, 255));
                        g.drawRect(j*brickwidth+70,i*brickheight+50,brickwidth,brickheight);
                    }
                }
            }

        }
        public void setBrickValue(int value,int row,int col){
            map[row][col]= value;
        }


    }


