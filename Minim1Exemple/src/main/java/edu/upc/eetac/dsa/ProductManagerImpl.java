package edu.upc.eetac.dsa;

import java.util.*;

import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager{
    //We call the log4j properties file
    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());

    //We implement the façade using a Singleton pattern
    private static ProductManager instance;

    private List<Producto> productos;
    private LinkedList<Pedido> pedidos;
    //Initialize the hashmap(key: string; value: Usuario) of users
    private HashMap<String, Usuario> usuarios;

    private ProductManagerImpl(){
        productos = new ArrayList<>();
        pedidos = new LinkedList<>();
        usuarios = new HashMap<>();
    }

    public static ProductManager getInstance(){
        if(instance==null) instance = new ProductManagerImpl();
        return instance;
    }

    public int size(){
        log.info("Tamaño: " + this.productos.size());
        return this.productos.size();
    }

    public void addUser(String user){
        usuarios.put(user, new Usuario(user));
    }

    public void addProducto(Producto p){
        this.productos.add(p);
    }

    public List<Producto> allProducts(){
        List<Producto> ret = new ArrayList<>();
        ret.addAll(this.productos);

        return ret;
    }

    public List<Producto> getAllProductsSortedByPrice(){
        //We create a copy of the list
        log.info("List before changes: " + this.productos);
        List<Producto> ret = new ArrayList<>();
        ret.addAll(this.productos);

        //We have to tell to the sort method, which criteria we want to apply
        Collections.sort(ret, new Comparator<Producto>() {
            @Override
            public int compare(Producto o1, Producto o2) {
                //Ascending order
                return (int)(o1.getPrice()-o2.getPrice());
            }
        });
        log.info("List after changes: " + ret);
        return ret;
    }

    public List<Producto> getAllProductsSortedByNumberOfSales(){
        //We create a copy of the list
        log.info("List before changes: " + this.productos);
        List<Producto> ret = new ArrayList<>();
        ret.addAll(this.productos);

        //We have to tell to the sort method, which criteria we want to apply
        Collections.sort(ret, new Comparator<Producto>() {
            @Override
            public int compare(Producto o1, Producto o2) {
                //Descending order
                return (-1)*(o1.getSales()-o2.getSales());
            }
        });
        log.info("List after changes: " + ret);
        return ret;
    }

    public LinkedList<Pedido> getAllOrdersOfAUser(String user) throws UserNotFoundException{
        LinkedList<Pedido> pedidos;

        //We want to find the given user
        Usuario theUser = this.usuarios.get(user);

        if(theUser!=null){
            log.info("User: " + theUser);
            pedidos = theUser.getPedidos();
        }
        else {
            throw new UserNotFoundException();
        }
        log.info("Historial de getPedidos: " +pedidos);
        return pedidos;
    }

    public void placeAnOrder(String user, Pedido p) throws UserNotFoundException{
        //We want to know if the user exists or not
        Usuario theUser = this.usuarios.get(user);
        log.info("User: " +theUser);

        if(theUser!=null){
            //First we verify that product exists in the list of products

            //If the user exists we will say who place this order with setUser, and then we add this order to the LinkedList this.getPedidos
            p.setUser(theUser);
            this.pedidos.add(p);
            log.info("Lista this.getPedidos :" +this.pedidos);
        }
        else{
            //If it doesn't exist we will throw an error and we will show the value of theUser with a log.error
            throw new UserNotFoundException();
        }

    }

    public Pedido serveAnOrder(){
        //To serve an order we want to delete this order in the LinkedList of this.getPedidos with pop()
        Pedido p = this.pedidos.pop();
        log.info("Order served: " +p);

        //We need to process this order, which means that we need to increment the sales of the product
        process(p);

        //We will get the user user who made this order, and then we will add this order to the history of orders of this user
        Usuario usuario = p.getUser();
        log.info("Usuario: " +usuario);
        usuario.addOrder(p);

        return p;
    }

    private void process(Pedido p) {
        //When we process and order, we have to go across the List of LProducto (function that exists in Pedido's class)
        //For that reason we create an auxiliary List called "l"
        List<LProducto> l = p.getProducts();
        Producto producto;

        //For every line (which means for every product, regardless it repeats) we increment the total number of sales
        for (LProducto lp: l) {
           producto = this.getProducto(lp.producto);
           producto.addSales(lp.q);
           log.info("Ventas: " +producto.getSales());
        }
    }

    //Private method to return the product given its name (String producto)
    private Producto getProducto(String producto) {
        log.info("I'm in");
        Producto p = null;
        for(int i = 0; i< this.productos.size(); i++) {
            if(producto.equals(this.productos.get(i).name)){
                p = this.productos.get(i);
            }
        }
        return p;
    }

}
