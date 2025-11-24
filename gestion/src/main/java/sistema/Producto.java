package sistema;

public class Producto {
    private String name;
    private double price;
    private int cantidad;
    private float weight;
    private String category;

    public Producto(String name, double price, int cantidad, float weight, String category){
        this.name = name;
        this.price = price;
        this.cantidad = cantidad;
        this.weight = weight;
        this.category = category;
    }
    public void SetAll(String name, double price, int cantidad, float weight, String category){
        this.name = name;
        this.price = price;
        this.cantidad = cantidad;
        this.weight = weight;
        this.category = category;
    }
    @Override
    public String toString(){
        return "Nombre: " + name + " Precio: " + price + " Cantidad: " + cantidad + " Peso: " + weight + " Categoria: " + category;
    }
}
