public class Car extends Vehicle {
    private final int numberOfSeats;

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

    public int getNumberOfSeats(){
        return this.numberOfSeats;
    }

    public static void main(String[] args){
        Car car = new Car("FUN R3G", "Toyota", 5);
        Car bigCar = new Car("1M R1CH", "Mercedes", 20);

        //Testing Vehicle methods
        System.out.println(car.getRegistrationNum().equals("FUN R3G"));
        System.out.println(car.getVehicleMake().equals("Toyota"));

        //Testing Car methods
        System.out.println(car.getNumberOfSeats() == 5);
        System.out.println(car.calculateBasicTripCost() == 500);
        System.out.println(bigCar.calculateBasicTripCost() == 600);
    }
}
