package Games;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay<lengthofsnake> extends JPanel implements KeyListener, ActionListener {

    private ImageIcon titleimage;

    int [] snakeXlength=new int[750];
    int [] snakeYlength=new int[750];

    int [] enemyXpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    int [] enemyYpos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

    private Random random=new Random();

    private int xpos=random.nextInt(34);
    private int ypos=random.nextInt(23);

    private  boolean left=false;
    private  boolean right=false;
    private  boolean up=false;
    private  boolean down=false;

    private int lengthofsnake=5;
    private int moves=0;

    private ImageIcon leftMouth;
    private ImageIcon rightMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;
    private ImageIcon snakeImage;
    private ImageIcon enemyImage;

    private Timer timer;
    private int delay=100;


    public Gameplay(){
        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);
        timer=new Timer(delay,this);
        timer.start();
        System.out.println("constructor");
    }



    //////////////////////////////////////////////////////////////////////////////paint //////////////////////////////
    public void paint(Graphics g){

        if(moves==0){
            snakeXlength[0]=100;
            snakeXlength[1]=75;
            snakeXlength[2]=50;

            snakeYlength[0]=100;
            snakeYlength[1]=100;
            snakeYlength[2]=100;
        }

        //title image border
        g.setColor(Color.white);
        g.drawRect(24,10,854,57);

        //drawing titleImage inside Rectangle
        titleimage=new ImageIcon("snakeTitle.png");
        titleimage.paintIcon(this,g,25,11);

        //border for gameplay area
        g.setColor(Color.white);
        g.drawRect(24,74,851,577);

        //set gameplay background
        g.setColor(Color.black);
        g.fillRect(25,75,850,575);


        rightMouth=new ImageIcon("rightmouth.png");
        rightMouth.paintIcon(this,g,snakeXlength[0],snakeYlength[0]);

        for(int a=0;a<lengthofsnake;a++){
            if(a==0 && right){
                rightMouth=new ImageIcon("rightmouth.png");
                rightMouth.paintIcon(this,g,snakeXlength[a],snakeYlength[a]);
            }
            if(a==0 && left){
                leftMouth=new ImageIcon("leftmouth.png");
                leftMouth.paintIcon(this,g,snakeXlength[a],snakeYlength[a]);
            }
            if(a==0 && up){
                upMouth=new ImageIcon("upmouth.png");
                upMouth.paintIcon(this,g,snakeXlength[a],snakeYlength[a]);
            }
            if(a==0 && down){
                downMouth=new ImageIcon("downmouth.png");
                downMouth.paintIcon(this,g,snakeXlength[a],snakeYlength[a]);
            }
            if (a!=0){
                snakeImage=new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this,g,snakeXlength[a],snakeYlength[a]);
            }
        }


        enemyImage=new ImageIcon("enemy.png");
        enemyImage.paintIcon(this,g,enemyXpos[xpos],enemyYpos[ypos]);

        if (snakeXlength[0]==enemyXpos[xpos] && snakeYlength[0]==enemyYpos[ypos]){
            lengthofsnake++;

            xpos=random.nextInt(34);
            ypos=random.nextInt(23);
        }

        for (int b=1;b<lengthofsnake;b++){
            if(snakeXlength[0]==snakeXlength[b] && snakeYlength[0]==snakeYlength[b]){
                 left=false;
                 right=false;
                 up=false;
                 down=false;

                 g.setColor(Color.white);
                 g.setFont(new Font("arial",Font.BOLD,50));
                 g.drawString("Game Over",300,300);

                g.setColor(Color.white);
                g.setFont(new Font("arial",Font.BOLD,20));
                g.drawString("Press Space to restart the game",300,340);

                moves=0;
            }
        }
        System.out.println("paint");

       g.dispose();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void actionPerformed(ActionEvent e) {//this method is called when timer starts
       // timer.start();

        if (right){
            for (int r=lengthofsnake-1;r>=0;r--){//ye tatti hai nai likha toh bhi chalega!
                snakeYlength[r+1]=snakeYlength[r];
            }
            for (int r=lengthofsnake-1;r>=0;r--){
                if (r==0){
                    snakeXlength[r]=snakeXlength[r]+25;
                }
                else {
                    snakeXlength[r]=snakeXlength[r-1];
                }
                if(snakeXlength[r]>850){
                    snakeXlength[r]=25;
                }
            }
            repaint();
        }
        if (left){
            for (int r=lengthofsnake-1;r>=0;r--){//ye tatti hai nai likha toh bhi chalega!
                snakeYlength[r+1]=snakeYlength[r];
            }
            for (int r=lengthofsnake-1;r>=0;r--){
                if (r==0){
                    snakeXlength[r]=snakeXlength[r]-25;
                }
                else {
                    snakeXlength[r]=snakeXlength[r-1];
                }
                if(snakeXlength[r]<25){
                    snakeXlength[r]=850;
                }
            }
            repaint();

        }
        if (up){
            for (int r=lengthofsnake-1;r>=0;r--){//ye tatti hai nai likha toh bhi chalega!
                snakeXlength[r+1]=snakeXlength[r];
            }
            for (int r=lengthofsnake-1;r>=0;r--){
                if (r==0){
                    snakeYlength[r]=snakeYlength[r]-25;
                }
                else {
                    snakeYlength[r]=snakeYlength[r-1];
                }
                if(snakeYlength[r]<75){
                    snakeYlength[r]=625;
                }
            }
            repaint();
        }
        if (down){
            for (int r=lengthofsnake-1;r>=0;r--){//ye tatti hai nai likha toh bhi chalega!
                snakeXlength[r+1]=snakeXlength[r];
            }
            for (int r=lengthofsnake-1;r>=0;r--){
                if (r==0){
                    snakeYlength[r]=snakeYlength[r]+25;
                }
                else {
                    snakeYlength[r]=snakeYlength[r-1];
                }
                if(snakeYlength[r]>625){
                    snakeYlength[r]=75;
                }
            }
            repaint();
        }
        System.out.println("action performd");

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode()==KeyEvent.VK_SPACE){
            lengthofsnake=5;
            repaint();
        }
        if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            moves++;
            right=true;
            if(!left){
                right=true;
            }
            else {
                right=false;
                left=true;
            }
            up=false;
            down=false;
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT){
            moves++;
            left=true;
            if(!right){
                left=true;
            }
            else {
                left=false;
                right=true;
            }
            up=false;
            down=false;
        }
        if (e.getKeyCode()==KeyEvent.VK_UP){
            moves++;
            up=true;
            if(!down){
                up=true;
            }
            else {
                up=false;
                down=true;
            }
            left=false;
            right=false;
        }
        if (e.getKeyCode()==KeyEvent.VK_DOWN){
            moves++;
            down=true;
            if(!up){
                down=true;
            }
            else {
                down=false;
                up=true;
            }
            left=false;
            right=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
