package com.example.macie.mobilecheckout.program_logic;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mac on 16/05/2017.
 */

public class VirtualBasket {
    private List<Product> productList;
    private static VirtualBasket instance;

    private VirtualBasket() {
        productList = new LinkedList<>();
    }

    public static VirtualBasket getInstance() {
        if(instance == null) {
            instance = new VirtualBasket();
        }
        return instance;
    }

    public void add(Product product){
        productList.add(product);
    }

    public void remove(Product product){
        productList.remove(product);
    }

    public List<Product> getProductList(){
        return productList;
    }

    public Product findByIndex(int index) {
        return productList.get(index);
    }

}
