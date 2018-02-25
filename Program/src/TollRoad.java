import java.util.HashMap;

//TODO: Sort into different packages and poop
//TODO: Check how to represent enums in uml

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

    public CustomerAccount findCustomer(String regNum){
        return customers.get(regNum);
    }

    public void chargeCustomer(String regNum) throws InsufficientAccountBalanceException{
        CustomerAccount customer = customers.get(regNum);

        try{
            int profit = customer.makeTrip();
            moneyMade = moneyMade + profit;
        } catch (InsufficientAccountBalanceException e){
            throw e;
        }
    }

    public String ToString(){
        return "SOme bull";
        //TODO: DO THIS PLEASE
    }

    public static void main(String[] args){
        //TODO: THIS LATER, SUNDAY IS A DAY OF REST YOU BAFOON
    }
}
