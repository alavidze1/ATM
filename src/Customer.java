public class Customer {
    private String name;
    private int pin;
    public Customer(String name1, int pin1){
        this.name = name1;
        this.pin = pin1;
    }
    public void updatePin(int newPin){
        this.pin = newPin;
    }

}
