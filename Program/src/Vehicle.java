public abstract class Vehicle {
    private final String registrationNum;
    private final String vehicleMake;

    Vehicle(String registrationNum, String vehicleMake){
        this.registrationNum = registrationNum;
        this.vehicleMake = vehicleMake;
    }

    public String getRegistrationNum(){
        return this.registrationNum;
    }

    public String getVehicleMake(){
        return this.vehicleMake;
    }

    public abstract int calculateBasicTripCost();

    static public void main(String[] args){
        //As we can't instatiate an abstract class, Vehicle methods are tested
        //in subclass Car
    }
}

