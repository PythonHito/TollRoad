public class CustomerAccount implements Comparable<CustomerAccount> {
    protected final String firstName;
    protected final String lastName;
    protected final Vehicle vehicle;
    protected int accountBalance;
    protected DiscountType discount;

    CustomerAccount(String firstName, String lastName, int accountBalance, Vehicle vehicle){
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountBalance = accountBalance;
        this.vehicle = vehicle;
        this.discount = DiscountType.NONE;
    }

    public int getAccountBalance() {
        //TODO: Check that this... situation is ok
        final int temp = accountBalance;
        return temp;
    }

    public DiscountType getDiscount() {
        final DiscountType temp = discount;
        return temp;
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
        //TODO: assuming rounding up costs is correct

        int basicCost = vehicle.calculateBasicTripCost();
        int finalCost;

        if (discount.equals(DiscountType.STAFF)){
            finalCost = (int)Math.ceil(0.5 * basicCost);
        }
        else if (discount.equals(DiscountType.FRIENDS_AND_FAMILY)){
            finalCost = (int)Math.ceil(0.9 * basicCost);
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
        return this.vehicle.getRegistrationNum().compareTo(customer.vehicle.getRegistrationNum());
    }

    public String toString(){
        return "Poo";
    }


}
