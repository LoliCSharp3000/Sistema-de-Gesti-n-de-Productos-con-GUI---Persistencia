package sistema;

public class Producto {
    private String name;
    private double price;
    private int cantidad;
    private float weight;
    private String category;

    public Producto(){}

    public Producto(String name, double price, int cantidad, float weight, String category){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }else{
            this.name = name;
        }
        if (price <= 0) {
            throw new IllegalArgumentException("No puede ser grati ni negativo");
        }else{
            this.price = price;
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("No puede ser grati ni negativo");
        }else{
            this.cantidad = cantidad;
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("No puede ser grati ni negativo");
        }else{
            this.weight = weight;
        }
        
        this.category = category;
    }
    public void setAll(String name, double price, int cantidad, float weight, String category){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }else{
            this.name = name;
        }
        if (price <= 0) {
            throw new IllegalArgumentException("No puede ser grati ni negativo");
        }else{
            this.price = price;
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("No puede ser grati ni negativo");
        }else{
            this.cantidad = cantidad;
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("No puede ser grati ni negativo");
        }else{
            this.weight = weight;
        }
        this.category = category;
    }
    
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }else{
            this.name = name;
        }
    }
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("No puede ser grati ni negativo");
        }else{
            this.price = price;
        }
    }
    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("No puede ser grati ni negativo");
        }else{
            this.cantidad = cantidad;
        }
    }
    public void setWeight(float weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("No puede ser grati ni negativo");
        }else{
            this.weight = weight;
        }
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getCantidad() {
        return cantidad;
    }
    public float getWeight() {
        return weight;
    }
    public String getCategory() {
        return category;
    }
    @Override
    public String toString(){
        return "Nombre: " + name + " Precio: " + price + " Cantidad: " + cantidad + " Peso: " + weight + " Categoria: " + category;
    }
}
