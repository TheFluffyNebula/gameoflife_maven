package edu.guilford;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class GOLDrawer extends JFrame{
    //int cT = callThis;
    Block theBlock = new Block();
    int generation = 0;
    public GOLDrawer() {
        //0 is userInput, 1 is ex.1, 2 is ex.2, 3 is ex.3
        super("Square");
        setSize(960, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }
    
    public void drawSquare(Graphics g, int x, int y, int size, boolean fill){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(x, y, size,size);
        if(fill){
        g2d.fillRect(x, y,size, size);
        } else{
            g2d.clearRect(x,y,size,size);
        }
    }
    
    public void drawGrid(Graphics g, int x, int y, int gridSize, int n, ArrayList<ArrayList<Block>> grid){
        Graphics2D g2d = (Graphics2D) g;
        int squareSize = gridSize/n;
        //drawSquare(g,50,50,100);
        for (int i=0; i<n; i++){
            for (int j=0; j<n;j++){
                if (grid.get(i).get(j).isAlive()){
                    drawSquare(g,x+i*squareSize,y+j*squareSize,squareSize,true);
                }
                else{
                    drawSquare(g,x+i*squareSize,y+j*squareSize,squareSize,false);
                }
            }
        }
    }
    public void drawUserInput(Graphics g, int x, int y, int gridSize, int n, int generations, double percentAlive){
        ArrayList<ArrayList<Block>> theGrid = theBlock.initGrid(n,percentAlive); //grid setup
        for (int i=0;i<generations;i++){//parameterize generation this later
            theBlock.updateNeighborsAlive(theGrid, n);
            theBlock.updateAlive(theGrid,n);
            drawGrid(g,x,y,gridSize,n,theGrid);
            generation+=1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(CTISGOLPROJECT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(generation);
    }
    public void drawEx1(Graphics g, int x, int y, int gridSize, int n){
        ArrayList<ArrayList<Block>> theCheckerboardGrid = theBlock.initCheckerboardGrid(n); //grid setup
        for (int i=0;i<100;i++){
            theBlock.updateNeighborsAlive(theCheckerboardGrid, n);
            theBlock.updateAlive(theCheckerboardGrid,n);
            drawGrid(g,x,y,gridSize,n,theCheckerboardGrid);
            generation+=1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(CTISGOLPROJECT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(generation);
    }
    public void drawEx2(Graphics g, int x, int y, int gridSize, int n){
    ArrayList<ArrayList<Block>> theEx2Grid = theBlock.inittheEx2Grid(n);
    for (int i=0;i<100;i++){
            theBlock.updateNeighborsAlive(theEx2Grid, n);
            theBlock.updateAlive(theEx2Grid,n);
            drawGrid(g,x,y,gridSize,n,theEx2Grid);
            generation+=1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(CTISGOLPROJECT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(generation);
    }
    public void drawEx3(Graphics g, int x, int y, int gridSize, int n)throws Exception{
    ArrayList<ArrayList<Block>> theEx3Grid = theBlock.inittheEx3Grid(n);
    for (int i=0;i<100;i++){
            theBlock.updateNeighborsAlive(theEx3Grid, n);
            theBlock.updateAlive(theEx3Grid,n);
            drawGrid(g,x,y,gridSize,n,theEx3Grid);
            generation+=1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(CTISGOLPROJECT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(generation);
    }
    @Override
    public void paint(Graphics g) {
        //System.out.println(UserInput.uInput);
        //System.out.println(GOLexamples.cT);
        super.paint(g);
        if (GOLexamples.cT==0){
            drawUserInput(g,300,100,300,UserInput.gridLen,UserInput.generation,UserInput.percentAlive);
        }
        if (GOLexamples.cT==1){ //checkerboard
            drawEx1(g,300,100,300,40);
        }
        if (GOLexamples.cT==2){ //you choose
            drawEx2(g,300,100,300,50);
        }
        if (GOLexamples.cT==3){                          try {
            //glider gun https://playgameoflife.com/lexicon/Gosper_glider_gun
            drawEx3(g,300,100,300,50);
            } catch (Exception ex) {
                Logger.getLogger(GOLDrawer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //drawGrid(g,500,100,600,50);
        
    }
}
