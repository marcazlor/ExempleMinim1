package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;
import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OrderTest {
    //Logger
    final static Logger log = Logger.getLogger(OrderTest.class.getName());

    //We have to create and object of type ProductManager, for then use its methods
    static ProductManager pm;

    static Usuario u1, u2;

    static Producto producto1, producto2, producto3, producto4, producto5, producto6;

    static Pedido pedido1, pedido2, pedido3;

    static List<LProducto> lp1, lp2, lp3;

    //Before the tests we have to add Users and some orders
    @BeforeClass
    public static void setUp(){
        //Because ProductManagerImpl implements ProductManager, we can create on top of pm, new ProductManagerImpl
        pm = ProductManagerImpl.getInstance();
        lp1 = new ArrayList<>();
        lp2 = new ArrayList<>();
        lp3 = new ArrayList<>();

        //Create the list of users
        pm.addUser("Pepe");
        pm.addUser("Juan");
        pm.addUser("Carla");
        pm.addUser("Paula");

        //We create the products
        producto1 = new Producto("Manzana",1.5);
        producto2 = new Producto("Pastel",10);
        producto3 = new Producto("Cerveza",2.5);
        producto4 = new Producto("Calamares",5);
        producto5 = new Producto("Chocolate", 3.2);
        pm.addProducto(producto1);
        pm.addProducto(producto2);
        pm.addProducto(producto3);
        pm.addProducto(producto4);
        pm.addProducto(producto5);
    }

    //When the test ends, it's properly to erase the contents added in @Before
    @AfterClass
    public static void tearDown(){
        pm = null;
    }

    @Test
    public void placeAnOrder() throws ProductNotFoundException {
        try {
            //Place new orders
            LProducto l1 = new LProducto();
            l1.producto = "Manzana";
            l1.q = 3;
            LProducto l2 = new LProducto();
            l2.producto = "Pastel";
            l2.q = 5;
            LProducto l3 = new LProducto();
            l3.producto = "Pastel";
            l3.q = 3;
            lp1.add(l1);
            lp1.add(l2);
            lp1.add(l3);
            lp2.add(l1);
            pedido1 = new Pedido(lp1);
            pedido2 = new Pedido(lp2);
            log.info("Pedido1: " +pedido1);
            pm.placeAnOrder("Pepe", pedido1);
            pm.placeAnOrder("Juan", pedido2);
        }
        catch (UserNotFoundException e){
            log.error("EL usuario no existe en la lista " +e.getMessage());
        }
    }

    @Test
    public void serveAnOrder(){
        pedido1 = this.pm.serveAnOrder();
        pedido2 = this.pm.serveAnOrder();

        assertEquals(pedido1.products.get(0).producto, "Pastel", "Pastel");
        assertEquals(pedido1.products.get(1).producto, "Manzana", "Manzana");
    }

    @Test
    public void getOrdersSortedByPrice(){
        List<Producto> ret = this.pm.getAllProductsSortedByPrice();

        assertEquals(ret.get(0).name, "Manzana", "Manzana");
        assertEquals(ret.get(1).name, "Cerveza", "Cerveza");
        assertEquals(ret.get(2).name, "Chocolate", "Chocolate");
        assertEquals(ret.get(3).name, "Calamares", "Calamares");
        assertEquals(ret.get(4).name, "Pastel", "Pastel");

    }

    @Test
    public void getOrdersSortedByNumberOfSales(){
        List<Producto> ret = this.pm.getAllProductsSortedByNumberOfSales();

        assertEquals(ret.get(0).name, "Pastel", "Pastel");
        assertEquals(ret.get(1).name, "Manzana", "Manzana");
        assertEquals(ret.get(2).name, "Chocolate", "Chocolate");
        assertEquals(ret.get(3).name, "Calamares", "Calamares");
        assertEquals(ret.get(4).name, "Cerveza", "Cerveza");

    }

    @Test
    public void getAllOrderOfAUser(){
        try {
            LinkedList<Pedido> l = this.pm.getAllOrdersOfAUser("Pepe");

            /*assertEquals(l.get(0).products.get(0).producto, "Manzana", "Manzana");
            assertEquals(l.get(0).products.get(1).producto, "Pastel", "Pastel");*/

        }
        catch(UserNotFoundException e){
            log.warn("El usuario del cual intenta visualizar su historial de getPedidos NO existe en la lista! " +e.getMessage());
        }
    }


}
