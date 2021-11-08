package edu.upc.eetac.dsa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;

public class Usuario {
    //Attributes
    String username;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    LinkedList<Pedido> pedidos;

    public Usuario() {

    }

    //In the constructor we only pass the username value, not the LinkedList getPedidos
    public Usuario(String username) {
        this.username = username;
        this.pedidos = new LinkedList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LinkedList<Pedido> getPedidos() {
        return this.pedidos;
    }

    public void setPedidos(LinkedList<Pedido> p) {
        this.pedidos = p;
    }

    //We add addOrder function in Usuario, because we need to know which order has the user made to serveAnOrder
    public void addOrder(Pedido p) {
        this.pedidos.add(p);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.pedidos.size(); i++)
            s = "Producto [Name = " + pedidos.get(0).products.get(i).producto + ", Quantity = " + pedidos.get(0).products.get(i).q + "]";

        return s;
    }
}
