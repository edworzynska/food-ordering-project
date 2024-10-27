public class Dish {
    private final String name;
    private final Double price;

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

//storing price as Double in java is not a great idea see https://stackoverflow.com/questions/3730019/why-not-use-double-or-float-to-represent-currency
    public Double getPrice() {
        return price;
    }

    public String display(){
        return String.format("%s - %.2f GBP", getName(), getPrice());
    }
}
