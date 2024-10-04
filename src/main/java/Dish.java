public class Dish {
    private String name;
    private Double price;

    public Dish(String name, Double price) {
        this.name = name;
        this.price = price;
        if (name.isEmpty()){
            throw new RuntimeException("Name cannot be empty!");
        }
        if (price <= 0){
            throw new RuntimeException("Price must be greater than 0!");
        }
    }
    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String display(){
        return String.format("%s - %.2f GBP", getName(), getPrice());
    }
}
