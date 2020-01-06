package com.atguigu.service.impl;

import com.atguigu.domain.ShoppingCar;
import com.atguigu.domain.User;
import com.atguigu.mapper.ShoppingCarMapper;
import com.atguigu.service.IShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCarServiceImpl implements IShoppingCarService {

    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    public void addCart(ShoppingCar shoppingCar) {

        shoppingCarMapper.addCart(shoppingCar);
    }

    /**
     * 数据库是否存在购物车
     * @param shoppingCar
     * @return
     */
    public boolean if_cart_exist(ShoppingCar shoppingCar) {
        boolean b = false;
        int i = shoppingCarMapper.select_cart_exist(shoppingCar);
        if (i>0){
            b = true;
        }
        return b;
    }

    public void updateCart(ShoppingCar car) {
        shoppingCarMapper.updateCart(car);
    }

    public List<ShoppingCar> get_list_cart() {
        return shoppingCarMapper.selectAll();
    }

    //根据用户查询购物车
    public List<ShoppingCar> get_list_cart_by_user(User user) {
        return shoppingCarMapper.select_list_cart_by_user(user);
    }
}
