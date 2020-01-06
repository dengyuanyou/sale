package com.atguigu.service;

import com.atguigu.domain.ShoppingCar;
import com.atguigu.domain.User;

import java.util.List;

public interface IShoppingCarService {

    void addCart(ShoppingCar shoppingCar);

    boolean if_cart_exist(ShoppingCar shoppingCar);

    void updateCart(ShoppingCar car);

    List<ShoppingCar> get_list_cart();

    List<ShoppingCar> get_list_cart_by_user(User user);
}
