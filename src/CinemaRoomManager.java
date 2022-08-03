import java.text.DecimalFormat;
import java.util.Scanner;

public class CinemaRoomManager { public static void main(String[] args) {
    int counter=0;
    System.out.println("Enter the number of rows:");
    Scanner scanner = new Scanner(System.in);
    int numberOfRows = scanner.nextInt();
    System.out.println("Enter the number of seats in each row:");
    int numberOfSeats = scanner.nextInt();
    create(numberOfRows,numberOfSeats);
}

    public static void create(int numberOfRows, int numberOfSeats){
        String[][] seats= buildingStage(numberOfRows,numberOfSeats);
        int flag=1;
        int counter=0;
        int holePrice=0;
        while (flag>0){
            System.out.println("");
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            if (input == 1) {
                System.out.println("Cinema:");
                for (int i = 0; i < seats.length; i++) {
                    for (int j = 0; j < seats[i].length; j++) {
                        System.out.print(seats[i][j]);
                    }
                    System.out.println();
                }
            }
            else if (input == 2) {
                boolean isCorrect=true;
                int rowNum=0;
                int seatNum=0;
                while (isCorrect){
                    System.out.println("Enter a row number:");
                    rowNum = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    seatNum = scanner.nextInt();
                    if(rowNum>numberOfRows || seatNum>numberOfSeats){
                        System.out.println("Wrong input!");
                        System.out.println(" ");
                    }
                    else {
                        if(seats[rowNum][seatNum]=="B"+" "){
                            System.out.println("That ticket has already been purchased!");
                            System.out.println("");
                        }
                        else {
                            isCorrect=false;
                            int price = calculatingPrice(numberOfRows, numberOfSeats, rowNum);
                            counter = counter + 1;
                            holePrice = holePrice + price;
                            System.out.println("Ticket price: $" + price);
                            seats[rowNum][seatNum] = "B" + " ";
                        }
                    }
                }
            }
            else if (input==3){
                final DecimalFormat df = new DecimalFormat("0.00");
                //df.setRoundingMode(RoundingMode.UP);
                System.out.println("Number of purchased tickets:"+counter);
                int holeSeats=numberOfSeats*numberOfRows;
                double percentage=(double)counter/holeSeats*100;
                System.out.println("Percentage:"+df.format(percentage)+"%");
                System.out.println("Current income: $"+holePrice);
                System.out.println("Total income: $"+calculatingIncome(numberOfRows,numberOfSeats));
            }
            else if (input==0){
                counter--;
                return;
            }
        }
    }
    public static String[][] buildingStage(int numberOfRows, int numberOfSeats) {
        String[][] seats = new String[numberOfRows+1][numberOfSeats+1];
        for (int i = 0; i <= numberOfRows ; i++) {
            for (int j = 0; j <= numberOfSeats ; j++) {
                if (i == 0 & j == 0) {
                    seats[0][0] = " ";
                } else {
                    if (i == 0 && j > 0) {
                        seats[i][j] = j + " ";
                    } else {
                        if (j == 0 && i > 0) {
                            seats[i][j] = i + " ";
                        } else {
                            seats[i][j] = "S" + " ";
                        }
                    }
                }
            }
        }
        return seats;
    }

    public static int calculatingIncome(int numberOfRows, int numberOfSeats) {
        int income;
        int totalSeats = numberOfRows * numberOfSeats;
        if (totalSeats < 60) {
            income = 10 * totalSeats;
        } else {
            int firstHalf = numberOfRows / 2;
            income = firstHalf * 10 * numberOfSeats + (numberOfRows - firstHalf) * 8 * numberOfSeats;
        }
        return income;
    }

    public static int calculatingPrice(int numberOfRows, int numberOfSeats, int rowNum) {
        int price;
        int totalSeats = numberOfRows * numberOfSeats;
        if (totalSeats < 60) {
            price = 10;
        } else {
            if (rowNum <= numberOfRows / 2) {
                price = 10;
            } else {
                price = 8;
            }
        }
        return price;
    }

}
