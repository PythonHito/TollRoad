public class Van extends Vehicle {
    protected final int payload;

    @Override
    public int calculateBasicTripCost() {
        if (payload <= 600){
            return 500;
        } else if (payload <= 800){
            return 750;
        } else {
            return 1000;
        }
    }

    Van(String registrationNum, String vehicleMake, int payload){
        super(registrationNum, vehicleMake);
        this.payload = payload;
    }

    public int getPayload(){
        return this.payload;
    }

    static public void main(String[] args){
        Van smallVan = new Van("H3Y M4T3", "My kitchen sink", 450);
        Van bigVan = new Van("W4TCH 0UT", "Renault", 700);
        Van bigMeanerVan = new Van("4UT0 GL455", "Citroen", 850);

        //Testing Van methods
        assert(smallVan.getPayload() == 450);

        assert(smallVan.calculateBasicTripCost() == 500);
        assert(bigVan.calculateBasicTripCost() == 750);
        assert(bigMeanerVan.calculateBasicTripCost() == 1000);

    }
}
