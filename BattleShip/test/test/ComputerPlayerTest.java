package test;

import edu.colorado.team20.ComputerPlayer;
import edu.colorado.team20.Board;
import edu.colorado.team20.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ComputerPlayerTest {

    @Test
    public void SingleRandomShot () {
        ComputerPlayer CPU = new ComputerPlayer();
        Board cpuBoard = new Board('P');
        String randShot = CPU.RandomShot(cpuBoard);
        char randCol=randShot.charAt(0);
        int randRow=Character.getNumericValue(randShot.charAt(1))+1;

        System.out.println(randCol);
        System.out.println(randRow);
        //to know what position RandomShot is shooting at

     //   assertFalse(cpuBoard.CheckSpot(randCol,randRow));
        assertEquals(cpuBoard.GetPositionChar(randCol, randRow), 'X');

    }

    @Test
    public void MultipleRandomShot () {
        ComputerPlayer CPU = new ComputerPlayer();
        Board cpuBoard = new Board('P');

        String[] shotLocations = new String[5];//Array to keep track of shot coordinates

        ///The following is to make 5 random shots mark those locations and test it they are marked correctly
        shotLocations[0]=CPU.RandomShot(cpuBoard);
        shotLocations[1]=CPU.RandomShot(cpuBoard);
        shotLocations[2]=CPU.RandomShot(cpuBoard);
        shotLocations[3]=CPU.RandomShot(cpuBoard);
        shotLocations[4]=CPU.RandomShot(cpuBoard);

        //These all print the shots
//        System.out.println(shotLocations[0].charAt(0));
//        System.out.println(Character.getNumericValue(shotLocations[0].charAt(1)));
//        System.out.println(shotLocations[1].charAt(0));
//        System.out.println(Character.getNumericValue(shotLocations[1].charAt(1)));
//        System.out.println(shotLocations[2].charAt(0));
//        System.out.println(Character.getNumericValue(shotLocations[2].charAt(1)));
//        System.out.println(shotLocations[3].charAt(0));
//        System.out.println(Character.getNumericValue(shotLocations[3].charAt(1)));
//        System.out.println(shotLocations[4].charAt(0));
//        System.out.println(Character.getNumericValue(shotLocations[4].charAt(1)));


        //   assertFalse(cpuBoard.CheckSpot(randCol,randRow));
        assertEquals(cpuBoard.GetPositionChar(shotLocations[0].charAt(0), Character.getNumericValue(shotLocations[0].charAt(1))+1), 'X');
        assertEquals(cpuBoard.GetPositionChar(shotLocations[1].charAt(0), Character.getNumericValue(shotLocations[1].charAt(1))+1), 'X');
        assertEquals(cpuBoard.GetPositionChar(shotLocations[2].charAt(0), Character.getNumericValue(shotLocations[2].charAt(1))+1), 'X');
        assertEquals(cpuBoard.GetPositionChar(shotLocations[3].charAt(0), Character.getNumericValue(shotLocations[3].charAt(1))+1), 'X');
        assertEquals(cpuBoard.GetPositionChar(shotLocations[4].charAt(0), Character.getNumericValue(shotLocations[4].charAt(1))+1), 'X');

    }

    @Test
    public void RandPlaceShipTest (){

        Ship testShip1 = new Ship("Minesweeper", 2);
        Ship testShip2 = new Ship("Destroyer", 3);
        //Ship testShip3 = new Ship("Battleship", 4);
        ComputerPlayer CPU = new ComputerPlayer();
        String ShipInfo;

        char c;
        int n;
        char o;
        //These will hold the parsed info from randShipPlacement
        //c=starting column,n=starting row, o=orientation



        /////Do testing for first ship placement//////

        ShipInfo=CPU.RandShipPlacement(testShip1);

        char [] colArray = testShip1.getColumn();
        int [] rowArray = testShip1.getRow();
        //Gets currently stored vals for testship1

        System.out.println("Ship1 Info:");
        System.out.println(colArray);
        System.out.println(rowArray[0]);

        c=ShipInfo.charAt(0);
        n=Character.getNumericValue(ShipInfo.charAt(1))+1;
        o=ShipInfo.charAt(2);
        //Parse info returned by RandShipPlacement

        System.out.println(c);
        System.out.println(n);
        System.out.println(o);
        //Print Parsed info

        assertEquals(c,colArray[0]);
        assertEquals(n,rowArray[0]);
        //Checked if saved starting row is what is returned by RandShipPlacement

        if(colArray[0]==colArray[1]){//ie, ship is going vertical
            assertEquals(n+1,rowArray[1]);
        }



        /////Do testing for second ship placement//////

        ShipInfo=CPU.RandShipPlacement(testShip2);

        char [] colArray1 = testShip2.getColumn();
        int [] rowArray1 = testShip2.getRow();
        //Gets currently stored vals for testship1

        System.out.println("Ship2 Info:");
        System.out.println(colArray1);
        System.out.println(rowArray1[0]);

        c=ShipInfo.charAt(0);
        n=Character.getNumericValue(ShipInfo.charAt(1))+1;
        o=ShipInfo.charAt(2);
        //Parse info returned by RandShipPlacement

        System.out.println(c);
        System.out.println(n);
        System.out.println(o);
        //Print Parsed info

        assertEquals(c,colArray1[0]);
        assertEquals(n,rowArray1[0]);
        //Checked if saved starting row is what is returned by RandShipPlacement

        if(colArray1[0]==colArray1[1]){//ie, ship is going vertical
            assertEquals(n+1,rowArray1[1]);
            assertEquals(n+2,rowArray1[2]);
        }

    }
}