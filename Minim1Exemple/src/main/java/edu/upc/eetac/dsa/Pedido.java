package edu.upc.eetac.dsa;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    //Attributes
    List<LProducto> products;
    private Usuario user;

    public Pedido(){
        this.products = new ArrayList<>();
    }

    public void setProducts(List<LProducto> products) {
        this.products = products;
    }

    public Pedido(List<LProducto> products) {
        this.products = products;
    }

    public List<LProducto> getProducts() {
        return products;
    }

    public void setUser(Usuario theUser) {
        this.user = theUser;
    }

    public Usuario getUser() {
        return this.user;
    }
}
