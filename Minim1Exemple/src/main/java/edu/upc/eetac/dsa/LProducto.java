package edu.upc.eetac.dsa;

public class LProducto {
    //Each field of products List has the name of the product and the quantity. A tuple of two fields (columns)
    //For that reason we create an inner class, which is protected
    public int q;
    public String producto;

    @Override
    public String toString() {
        return "Pedido [Name=" + producto + ", Quantity=" + q + "]";
    }
}
