import java.util.HashMap;

public class TollRoad {
    private HashMap<String, CustomerAccount> customers;
    private int moneyMade;

    TollRoad(){
        customers = new HashMap<>();
        moneyMade = 0;
    };

    public int getMoneyMade() {
        return moneyMade;
    }

    public HashMap<String, CustomerAccount> getCustomers() {
        return new HashMap<>(customers);
    }

    public void addCustomer(CustomerAccount customer){
        customers.put(customer.getVehicle().getRegistrationNum(), customer);
    }

    public CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException{
        CustomerAccount customer = customers.get(regNum);
        if (customer == null){
            throw new CustomerNotFoundException();
        }
        else {
            return customer;
        }

    }

    public void chargeCustomer(String regNum) throws InsufficientAccountBalanceException, CustomerNotFoundException{
        try {
            CustomerAccount customer = findCustomer(regNum);
            int profit = customer.makeTrip();
            moneyMade = moneyMade + profit;
        } catch (CustomerNotFoundException e){
            throw e;
        } catch (InsufficientAccountBalanceException e){
            throw e;
        }
    }

    public String toString(){
        return "Money collected: " + moneyMade + " Customers: " + customers;
    }

    public static void main(String[] args){
        //Initialise toll with two customers
        Vehicle customer1Vehicle = new Car("C001 B01", "Hyundai", 4);
        CustomerAccount customer1 = new CustomerAccount("Dave", "Gloat", 1000,
                customer1Vehicle);
        Vehicle customer2Vehicle = new Car("SUP B01", "Toyota", 6);
        CustomerAccount customer2 = new CustomerAccount("Steve", "Sleve", 1500,
                customer2Vehicle);

        TollRoad toll = new TollRoad();
        toll.addCustomer(customer1);
        toll.addCustomer(customer2);

        //Check that getCustomers returns a deep copy
        HashMap<String, CustomerAccount> customers = toll.getCustomers();
        customers.remove("C001 B01");
        System.out.println(!customers.equals(toll.getCustomers()));

        //Check chargeCustomer
        try {
            toll.chargeCustomer("C001 B01");
            System.out.println(toll.getMoneyMade() == 500);
            System.out.println(toll.findCustomer("C001 B01").getAccountBalance() == 500);

            toll.chargeCustomer("SUP B01");
            System.out.println(toll.getMoneyMade() == 1100);
            System.out.println(toll.findCustomer("SUP B01").getAccountBalance() == 900);

            //Check that InsufficientAccountBalanceException propagates appropiately
            toll.chargeCustomer("C001 B01");
            toll.chargeCustomer("C001 B01");

            //Should reach this point!
            System.out.println(false);

        } catch (InsufficientAccountBalanceException e){
            //Should terminate at this point
            System.out.println(true);
        } catch (CustomerNotFoundException e){
            //Shouldn't reach this point!
            System.err.println(false);
        }

        System.out.println(toll);

    }
}
