public class Truck extends Vehicle {
    private final int numberOfTrailers;

    @Override
    public int calculateBasicTripCost() {
        if (numberOfTrailers <= 1){
            //Assuming that 0 trailers is equivilent to 1 trailer
            return 1250;
        } else {
            return 1500;
        }
    }

    Truck(String registrationNum, String vehicleMake, int numberOfTrailers){
        super(registrationNum, vehicleMake);
        this.numberOfTrailers = numberOfTrailers;
    }

    public int getNumberOfTrailers() {
        return this.numberOfTrailers;
    }

    static public void main(String[] args){
        Truck teenyestTruck = new Truck("T1NY", "Ford", 1);
        Truck australianRoadTrain = new Truck("N0T T1NY", "Fosters", 16);

        //Testing Truck methods
        System.out.println(teenyestTruck.getNumberOfTrailers() == 1);

        System.out.println(teenyestTruck.calculateBasicTripCost() == 1250);
        System.out.println(australianRoadTrain.calculateBasicTripCost() == 1500);
    }
}
