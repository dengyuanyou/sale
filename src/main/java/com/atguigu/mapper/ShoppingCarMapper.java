package com.atguigu.mapper;

import com.atguigu.domain.ShoppingCar;
import com.atguigu.domain.User;
import java.util.List;
import java.util.Map;

public interface ShoppingCarMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ShoppingCar record);

    ShoppingCar selectByPrimaryKey(Integer id);

    List<ShoppingCar> selectAll();

    int updateByPrimaryKey(ShoppingCar record);

    void addCart(ShoppingCar shoppingCar);

    int select_cart_exist(ShoppingCar shoppingCar);

    void updateCart(ShoppingCar car);

    List<ShoppingCar> select_list_cart_by_user(User user);

    void delect_carts(Map<Object, Object> map_cart);

}