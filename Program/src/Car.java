public class Car extends Vehicle {
    protected final int numberOfSeats;

    @Override
    public int calculateBasicTripCost() {
        if (numberOfSeats <= 5){
            return 500;
        } else {
            return 600;
        }
    }

    Car(String registrationNum, String vehicleMake, int numberOfSeats){
        super(registrationNum, vehicleMake);
        this.numberOfSeats = numberOfSeats;
    }

    //TODO: Sort setter and stuff

    public int getNumberOfSeats(){
        return this.numberOfSeats;
    }

    public static void main(String[] args){
        Car car = new Car("FUN R3G", "Toyota", 5);
        Car bigCar = new Car("1M R1CH", "Mercedes", 20);

        //Testing Vehicle methods
        assert(car.getRegistrationNum().equals("FUN R3G"));
        assert(car.getVehicleMake().equals("Toyota"));

        //Testing Car methods
        assert(car.getNumberOfSeats() == 5);
        assert(car.calculateBasicTripCost() == 500);
        assert(bigCar.calculateBasicTripCost() == 600);
    }
}
