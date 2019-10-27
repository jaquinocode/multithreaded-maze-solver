package com.juanaquino;

import java.util.Stack;
import java.awt.Point;
import java.util.Random;

public class MazeGenerator
{

    public void generateMaze( String[][] array )
    {
        Stack<Point> stack;
        Random random;
        StringBuffer possible;
        Point location;
        final int rows;
        final int columns;
        final int totalCells;
        int x;
        int y;
        int visitedCells;

        stack = new Stack<Point>();
        random = new Random();
        possible = new StringBuffer();
        location = new Point();
        rows = array.length;
        columns = array[0].length;
        totalCells = (rows / 2) * (columns / 2);
        x = 1 + (2 * random.nextInt((int) rows / 2));
        y = 1 + (2 * random.nextInt((int) columns / 2));
        visitedCells = 1;


        createRawArray(array); // make array with only .'s and #'s


        while(visitedCells < totalCells)
        {
            possible.setLength(0);


            if (y > 1)                                        // check up for north cell
            {
                if (checkWalls(x , y - 2 , array) == true)
                    possible.append('N');
            }


            if (y < array[0].length - 2)
            {
                if (checkWalls(x , y + 2 , array) == true)    // check down for south cell
                    possible.append('S');
            }


            if (x < array.length - 2)                         // check right for east cell
            {
                if ( checkWalls( x + 2 , y , array) == true)
                    possible.append('E');
            }


            if (x > 1)                                                //check left for west cell
            {
                if (checkWalls(x - 2 , y , array) == true)
                    possible.append('W');
            }


            if (possible.length() != 0)
            {
                // there is a neighboring wall

                // push the current location into stack
                stack.push(new Point(x , y));

                // select a random neighbor
                int direction = random.nextInt(possible.length());

                switch (possible.charAt(direction))
                {
                    case 'N': array[x][y - 1] = ".";
                        y = y - 2;
                        break;

                    case 'S': array[x][y + 1] = ".";
                        y = y + 2;
                        break;

                    case 'E': array[x + 1][y] = ".";
                        x = x + 2;
                        break;

                    case 'W': array[x - 1][y] = ".";
                        x = x - 2;
                        break;

                } // end switch

                visitedCells++;
            }
            else
            {
                // there is no neighbor, pop stack


                location = stack.pop();
                x = location.x;
                y = location.y;
            }

        }

        // make enter and exit point
        array[1][0] = ".";
        array[array.length - 2][array[0].length - 1] = ".";

    } // end method generateMaze


    // this method checks whether all the walls of input
    // cell are intact
    private boolean checkWalls( int x , int y , String[][] array )
    {
        if (array[x - 1][y] == "#")
            if (array[x + 1][y] == "#")
                if (array[x][y - 1] == "#")
                    if (array[x][y + 1] == "#")
                        return true;

        // at least one wall is broken down
        // so return false
        return false;
    }



    private void createRawArray(String[][] emptyArray)  //makes array with '.'s amd '#''s
    {
        int i , j;

        for (i = 0 ; i < emptyArray.length; i++)
            for(j = 0; j < emptyArray[0].length; j++) // if coordinates are odd, make dot, if not make #
                if ((i % 2) == 1 && (j % 2) == 1)
                    emptyArray[i][j]=".";
                else
                    emptyArray[i][j]="#";
    }

}
