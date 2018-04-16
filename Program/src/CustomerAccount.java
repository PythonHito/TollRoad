public class CustomerAccount implements Comparable<CustomerAccount> {
    private final String firstName;
    private final String lastName;
    private final Vehicle vehicle;
    private int accountBalance;
    private DiscountType discount;

    private enum DiscountType {
        NONE,
        STAFF,
        FRIENDS_AND_FAMILY
    }

    CustomerAccount(String firstName, String lastName, int accountBalance, Vehicle vehicle){
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountBalance = accountBalance;
        this.vehicle = vehicle;
        this.discount = DiscountType.NONE;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public DiscountType getDiscount() {
        return discount;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void activateStaffDiscount(){
        discount = DiscountType.STAFF;
    }

    public void activateFriendsAndFamilyDiscount(){
        discount = DiscountType.FRIENDS_AND_FAMILY;
    }

    public void deactivateDiscount(){
        discount = DiscountType.NONE;
    }

    public void addFunds(int amount){
        accountBalance = accountBalance + amount;
    }

    public int makeTrip() throws InsufficientAccountBalanceException{
        int basicCost = vehicle.calculateBasicTripCost();
        int finalCost;

        if (discount.equals(DiscountType.STAFF)){
            finalCost = (int)Math.floor(0.5 * basicCost);
        }
        else if (discount.equals(DiscountType.FRIENDS_AND_FAMILY)){
            finalCost = (int)Math.floor(0.9 * basicCost);
        }
        else {
            finalCost = basicCost;
        }

        if(finalCost <= accountBalance){
            accountBalance = accountBalance - finalCost;
            return finalCost;
        }
        else {
            throw new InsufficientAccountBalanceException();
        }

    }

    public int compareTo(CustomerAccount customer){
        return this.getVehicle().getRegistrationNum().compareTo(customer.getVehicle().getRegistrationNum());
    }

    public String toString(){
        return firstName + " " + lastName + ": " + vehicle.getClass().getSimpleName() +  " " + vehicle.getRegistrationNum();
    }

    public static void main(String[] args){
        Vehicle customer1Vehicle = new Car("C001 B01", "Hyundai", 4);
        CustomerAccount customer1 = new CustomerAccount("Dave", "Gloat", 1000,
                                                        customer1Vehicle);
        //makeTrip tests
        try {
            int basicCost = customer1.makeTrip();
            System.out.println(basicCost == 500);

            customer1.addFunds(500);

            //Friends discount test
            customer1.activateFriendsAndFamilyDiscount();
            int friendsDiscountCost = customer1.makeTrip();
            System.out.println(friendsDiscountCost == 450);

            customer1.addFunds(450);

            //Staff discount test
            customer1.activateStaffDiscount();
            int staffDiscountCost = customer1.makeTrip();
            System.out.println(staffDiscountCost == 250);

            customer1.addFunds(250);

            customer1.deactivateDiscount();
        } catch (InsufficientAccountBalanceException e) {
            System.err.println(e);
        }

        //Test that exception InsufficientAccountBalanceException is raised appropiately
        try{
            customer1.makeTrip();
            customer1.makeTrip();
            customer1.makeTrip();


            //Should never reach this point!
            System.out.println(false);
        } catch (InsufficientAccountBalanceException e){
            //Bypasses println(false)
            System.out.println(true);
        }

        System.out.println(customer1);
    }


}
