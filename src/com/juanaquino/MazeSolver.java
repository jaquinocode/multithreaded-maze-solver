package com.juanaquino;

public class MazeSolver
{
    private static boolean reachedEnd = false;


    public void solveMaze( int rowLocation , int columnLocation, int rowEnd, int columnEnd, String[][] array)
    {
        array[ rowLocation ][ columnLocation ] = "x";   // mark starting point with x, this is the only mark that works for some reason

        if ( rowLocation == rowEnd && columnLocation == columnEnd )//stops when location is reached at bottom right corner
        {
            reachedEnd = true;
            return;
        }
        else
        {
            if ( array[ rowLocation ][ columnLocation + 1] == "." && reachedEnd == false ) // check if you can go right, and mark 'x' there if you can, repeat w/ recursion
            {
                solveMaze( rowLocation , columnLocation + 1 , rowEnd , columnEnd , array );
            }
            if ( columnLocation != 0 )
                if ( array[ rowLocation ][ columnLocation - 1 ] == "." && reachedEnd == false )// check if you can go left, and mark 'x' there if you can, repeat w/ recursion

                {
                    solveMaze( rowLocation , columnLocation - 1 , rowEnd , columnEnd , array );
                }
            if ( rowLocation != 0 )
                if ( array [ rowLocation - 1 ][ columnLocation ] == "." && reachedEnd == false )  // check if you can go up, and mark 'x' there if you can, repeat w/ recursion

                {
                    solveMaze( rowLocation - 1 , columnLocation , rowEnd , columnEnd , array );
                }
            if ( rowLocation != array.length - 1 )
                if ( array[ rowLocation + 1 ][ columnLocation ] == "." && reachedEnd == false )// check if you can go down, and mark 'x' there if you can, repeat w/ recursion

                {
                    solveMaze( rowLocation + 1 , columnLocation , rowEnd , columnEnd , array );
                }
        }

    }

}
