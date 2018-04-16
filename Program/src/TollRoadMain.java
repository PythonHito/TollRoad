import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TollRoadMain {
    static public TollRoad initialiseTollRoadFromFile(){
        File file = new File("customerData.txt");
        TollRoad toll = new TollRoad();

        try (Scanner scan = new Scanner(file)){
            scan.useDelimiter("#");
            while (scan.hasNext()){
                String record = scan.next();
                String[] data = record.split(",");

                //Label data elements
                String vehicleType = data[0];
                String regNum = data[1];
                String firstName = data[2];
                String lastName = data[3];
                String vehicleMake = data[4];
                int vehicleInfo = Integer.parseInt(data[5]);
                int startingBalance = Integer.parseInt(data[6]);
                String discountType = data[7];

                Vehicle vehicle;
                if (vehicleType.equals("Car")){
                    vehicle = new Car(regNum, vehicleMake, vehicleInfo);
                } else if (vehicleType.equals("Van")){
                    vehicle = new Van(regNum, vehicleMake, vehicleInfo);
                } else {
                    vehicle = new Truck(regNum, vehicleMake, vehicleInfo);
                }

                CustomerAccount currentCustomer = new CustomerAccount(firstName, lastName,
                                                                      startingBalance, vehicle);
                if (discountType.equals("FRIENDS_AND_FAMILY")){
                    currentCustomer.activateFriendsAndFamilyDiscount();
                } else if (discountType.equals("STAFF")){
                    currentCustomer.activateStaffDiscount();
                }

                toll.addCustomer(currentCustomer);
            }


        }
        catch (FileNotFoundException e){
            System.err.println("customerData.txt doesn't exist, please place in directory");
            System.exit(1);
        }

        return toll;

    }

    static public void simulateFromFile(TollRoad road){
        File file = new File("transactions.txt");
        try (Scanner scan = new Scanner(file)){
            scan.useDelimiter("\\$");
            while (scan.hasNext()){
                String instruction = scan.next();
                String[] data = instruction.split(",");

                String method = data[0];
                if (method.equals("addFunds")){
                    addFundsSimulate(road, data);
                }
                else if (method.equals("makeTrip")){
                    makeTripSimulate(road, data);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("transactions.txt doesn't exist, please place in directory");
            System.exit(1);
        }
    }

    static private void addFundsSimulate(TollRoad road, String[] data){
        String regNum = data[1];
        int amount = Integer.parseInt(data[2]);

        try {
            road.findCustomer(regNum).addFunds(amount);
            //Print this if customer found
            System.out.println(regNum + ": " + amount + " added successfully");
        } catch (CustomerNotFoundException e){
            //Otherwise print this
            System.out.println(regNum + ": addFunds failed. Customer does not exist");
        }

    }

    static private void makeTripSimulate(TollRoad road, String[] data){
        //makeTrip processing
        String regNum = data[1];

        try {
            road.chargeCustomer(regNum);
            //If customer exists and has sufficient money print this
            System.out.println(regNum + ": Trip completed successfully");
        } catch (InsufficientAccountBalanceException e){
            //Else if they don't have sufficient money print this
            System.out.println(regNum + ": makeTrip failed. Insufficient funds");
        } catch (CustomerNotFoundException e) {
            //Else if they don't exist print this
            System.out.println(regNum + ": makeTrip failed. CustomerAccount does not exist");
        }
    }

    static public void main(String[] args){
        //Testing TollRoadMain methods
        TollRoad toll = initialiseTollRoadFromFile();
        //Have tested without customerData.txt present; successfully printed error message and exited program
        //Inspected by eye that customerAccounts were created accurately

        simulateFromFile(toll);
        String str = String.format("Total profit: £%,d", toll.getMoneyMade());
        System.out.println(str);
    }
}
