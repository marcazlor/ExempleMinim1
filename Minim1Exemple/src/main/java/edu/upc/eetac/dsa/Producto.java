package edu.upc.eetac.dsa;

public class Producto{
    //Atributes
    String name;
    double price;
    private int sales = 0;

    public Producto(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSales() {
        return sales;
    }

    public void addSales(int q) {
        this.sales = this.sales + q;
    }



    @Override
    public String toString() {
        return "Producto [Name = " + name + ", Precio = " + price +", Sales = " + sales + "]";
    }
}
