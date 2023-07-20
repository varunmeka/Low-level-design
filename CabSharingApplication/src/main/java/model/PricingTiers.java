package model;

public enum PricingTiers {
    Short(2),
    Medium(5),
    Large(Integer.MAX_VALUE);
    private int value;

    PricingTiers(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }

}
