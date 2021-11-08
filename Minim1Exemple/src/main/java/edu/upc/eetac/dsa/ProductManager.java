package edu.upc.eetac.dsa;

import java.util.LinkedList;
import java.util.List;

public interface ProductManager {
    //Methods
    //Ascending order
    List<Producto> getAllProductsSortedByPrice();
    void placeAnOrder(String user, Pedido p) throws UserNotFoundException;
    Pedido serveAnOrder();
    LinkedList<Pedido> getAllOrdersOfAUser(String user) throws UserNotFoundException;
    //Descending order
    List<Producto> getAllProductsSortedByNumberOfSales();


    void addUser(String user);
    void addProducto(Producto p);
    List<Producto> allProducts();
    int size();
}
