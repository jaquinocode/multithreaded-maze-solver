package com.juanaquino;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class MazeApplet extends Applet implements MouseListener, MouseMotionListener, Runnable
{
    private MazeGenerator mazeGenerator;
    private String[][] mazeArray = new String[25][25];

    int xStart = 1;
    int yStart = 0;
    int xEnd = mazeArray.length - 2;
    int yEnd = mazeArray[0].length - 1;

    TextField xStartText = new TextField("1");
    TextField yStartText = new TextField("0");
    TextField xEndText = new TextField((mazeArray.length-2) + "");
    TextField yEndText = new TextField((mazeArray[0].length-1) + "");
    TextField xRandomize = new TextField(mazeArray.length + "");
    TextField yRandomize = new TextField(mazeArray[0].length + "");

    String wall = "#";
    String open = ".";
    String visited = "x";


    Button solveButton = new Button("Solve It For Me");
    Button refreshButton = new Button("Refresh Coordinates");
    Button clearButton = new Button("Clear");
    Button randomizeButton = new Button("Randomize!");

    boolean reachedTarget = false;
    boolean isRunning = true;

    Thread thread = null;
    int delay = 50;

    public void init()
    {
        setSize(625,625);

        addMouseListener(this);
        addMouseMotionListener(this);

        add(xStartText);
        add(yStartText);
        add(xEndText);
        add(yEndText);
        add(solveButton);
        add(refreshButton);
        add(clearButton);
        add(randomizeButton);
        add(xRandomize);
        add(yRandomize);

        solveButton.addActionListener(new solveButtonListener());
        refreshButton.addActionListener(new refreshButtonListener());
        clearButton.addActionListener(new clearButtonListener());
        randomizeButton.addActionListener(new randomizeButtonListener());

        mazeGenerator = new MazeGenerator();
        mazeGenerator.generateMaze(mazeArray);
    }

    public void paint(Graphics g)
    {
        for (int i = 0; i < mazeArray.length; i++)
            for (int j = 0; j < mazeArray[0].length; j++)
            {
                if (mazeArray[i][j] == wall)
                    g.setColor(Color.black);
                else if (i == xStart && j == yStart)
                    g.setColor(Color.red);
                else if (i == xEnd && j == yEnd)
                    g.setColor(Color.green);
                else
                    g.setColor(Color.white);
                g.fillRect(j*25, i*25, 25, 25);
            }
    }

    public void run()
    {
        nextStep(xStart, yStart, 1);
    }

    void nextStep(int x, int y, int level)
    {
        try{
            // repaint, then mark current location as visited and see if reached target
            repaint();
            thread.sleep(delay);
            if(x == xEnd && y == yEnd)
                reachedTarget = true;
            mazeArray[x][y] = visited;
            xStart = x; yStart = y;
            if(reachedTarget) return;

            if(x != 0 && mazeArray[x-1][y] != wall && mazeArray[x-1][y] != visited) // check if x can go up and repeat
                nextStep(x - 1, y, level+1);
            xStart = x; yStart = y;
            repaint();
            thread.sleep(delay);
            if(reachedTarget) {xEnd = x; yEnd = y;return;}

            if(x != (mazeArray.length-1) && mazeArray[x+1][y] != wall && mazeArray[x+1][y] != visited) // check down and repeat
                nextStep(x+1, y, level+1);
            xStart = x; yStart = y;
            repaint();
            thread.sleep(delay);
            if(reachedTarget) {xEnd = x; yEnd = y;return;}

            if(y != 0 && mazeArray[x][y-1] != wall && mazeArray[x][y-1] != visited) // check left and repeat
                nextStep(x, y-1, level+1);
            xStart = x; yStart = y;
            repaint();
            thread.sleep(delay);
            if(reachedTarget) {xEnd = x; yEnd = y;return;}

            if(y != (mazeArray[0].length-1) && mazeArray[x][y+1] != wall && mazeArray[x][y+1] != visited) // check down and repeat
                nextStep(x, y+1, level+1);
            xStart = x; yStart = y;
            repaint();
            thread.sleep(delay);
            if(reachedTarget) {xEnd = x; yEnd = y; return;}
        }
        catch(InterruptedException ie)
        {
            System.out.println("Interrupted");
        }
    }

    public void mouseClicked(MouseEvent e){}

    public void mouseDragged(MouseEvent e)
    {
        int xClick = e.getX()/ 25;
        int yClick = e.getY()/ 25;
        mazeArray[yClick][xClick] = open;
        repaint();
    }

    public void mouseMoved(MouseEvent e){}

    public void mousePressed(MouseEvent e)
    {
        int xClick = e.getX()/ 25;
        int yClick = e.getY()/ 25;
        mazeArray[yClick][xClick] = (mazeArray[yClick][xClick] == open ? wall : open);
        repaint();

    }

    public void mouseReleased(MouseEvent e){}

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

    class solveButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            for (int i = 0; i < mazeArray.length; i++)
                for (int j = 0; j < mazeArray[0].length; j++)
                    if (mazeArray[i][j] == visited)
                        mazeArray[i][j] = open;
            reachedTarget = false;
            thread = new Thread(MazeApplet.this);
            thread.start();
        }
    }

    class refreshButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            xStart = Integer.parseInt(xStartText.getText());
            yStart = Integer.parseInt(yStartText.getText());
            xEnd   = Integer.parseInt(xEndText.getText());
            yEnd   = Integer.parseInt(yEndText.getText());
            if (xStart > 0 && yStart >= 0 && xStart <= (mazeArray.length-2) && yStart <= (mazeArray[0].length-1) &&
                    xEnd > 0 && yEnd >= 0 && xEnd <= (mazeArray.length-2) && yEnd <= (mazeArray[0].length-1))
            {
                repaint();
            }
            else
            {
                xStart = 1;
                yStart = 0;
                xEnd = mazeArray.length - 2;
                yEnd = mazeArray[0].length - 1;
            }
        }
    }

    class clearButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            for(int i=0; i < mazeArray.length; i++)
                for (int j=0; j < mazeArray[0].length; j++)
                    if(i == xStart && j == yStart || i == xEnd && j == yEnd){}
                    else mazeArray[i][j] = wall;

            repaint();
        }
    }

    class randomizeButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int xDimension = Integer.parseInt(xRandomize.getText());
            int yDimension = Integer.parseInt(yRandomize.getText());

            mazeArray = new String[xDimension][yDimension];
            mazeGenerator.generateMaze(mazeArray);

            repaint();
        }
    }
}