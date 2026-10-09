package model;

public class Customer {
    private final long customerId;
    private final String name;

    public Customer(long customerId, String name) {
        if(customerId<=0){
            throw new IllegalArgumentException("Customer id should be greater than 0");
        }
        if(name == null || name.isBlank()){
            throw  new IllegalArgumentException("Customer name cannot be null or blank");
        }
        this.customerId = customerId;
        this.name = name;
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}
