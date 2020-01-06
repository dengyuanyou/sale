package com.atguigu.controller;

import com.atguigu.domain.ShoppingCar;
import com.atguigu.domain.User;
import com.atguigu.service.IShoppingCarService;
import com.atguigu.util.MyJsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 购物车相关
 */

@Controller
public class CartController {

    @Autowired
    private IShoppingCarService shoppingCarService;

    /**
     * 修改购物车状态
     * @param response
     * @param list_cart_cookie
     * @param cart
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping("/change_isCheck")
    public String change_isCheck(HttpServletResponse response, @CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie,ShoppingCar cart,HttpSession session,ModelMap modelMap){

        List<ShoppingCar> list_cart;
        User user = (User)session.getAttribute("user");

        //如果用户没有登录
        if (user==null){
            list_cart = MyJsonUtil.json_to_list(list_cart_cookie, ShoppingCar.class);

        //如果用户登录
        }else {
            list_cart = (List<ShoppingCar>)session.getAttribute("list_cart_session");

        }

        for (int i=0;i<list_cart.size();i++){
            ShoppingCar shoppingCar = list_cart.get(i);
            if (shoppingCar.getSkuId() == cart.getSkuId()){
                shoppingCar.setIsCheck(cart.getIsCheck());
                if (user==null){
                    //覆盖cookie
                    Cookie cookie = new Cookie("list_cart_cookie",MyJsonUtil.list_to_json(list_cart));
                    cookie.setMaxAge(60 * 60 * 24);
                    response.addCookie(cookie);
                }else {
                    shoppingCarService.updateCart(shoppingCar);
                }
            }

        }

        modelMap.put("sum",get_sum(list_cart));
        modelMap.put("list_cart",list_cart);
        return "cartListInner";
    }


    /**
     * 购物车列表页面
     * @param list_cart_cookie
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping("/goto_cart_list")
    public String goto_cart_list(@CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie,HttpSession session,ModelMap modelMap){

        List<ShoppingCar> list_cart = new ArrayList<ShoppingCar>();

        User user = (User)session.getAttribute("user");
        if (user!=null){

            list_cart = (List<ShoppingCar>)session.getAttribute("list_cart_session");

        }else {

            list_cart = MyJsonUtil.json_to_list(list_cart_cookie, ShoppingCar.class);
        }

        modelMap.put("sum",get_sum(list_cart));
        modelMap.put("list_cart",list_cart);
        return "cartList";
    }

    //计算购物车总价格
    private BigDecimal get_sum(List<ShoppingCar> list_cart) {

        BigDecimal sum = new BigDecimal("0");

        if (list_cart!=null){
            for (int i=0;i<list_cart.size();i++){
                if (list_cart.get(i).getIsCheck().equals("1")){
                    sum = sum.add(new BigDecimal(list_cart.get(i).getTotal() + ""));

                }
            }

        }
        return sum;
    }


    @RequestMapping("/miniCart")
    public String miniCart(@CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie,HttpSession session,ModelMap modelMap){
        List<ShoppingCar> list_cart = new ArrayList<ShoppingCar>();

        User user = (User)session.getAttribute("user");
        if (user!=null){

            list_cart = (List<ShoppingCar>)session.getAttribute("list_cart_session");

        }else {

            list_cart = MyJsonUtil.json_to_list(list_cart_cookie, ShoppingCar.class);
        }

        modelMap.put("list_cart",list_cart);
        return "miniCartList";
    }


    @RequestMapping("/add_cart")
    public String addCart(HttpSession session, HttpServletResponse response, @CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie, ModelMap map, ShoppingCar shoppingCar){
        List<ShoppingCar> list_cart = new ArrayList<ShoppingCar>();
        Integer userId = shoppingCar.getUserId();

        //用户未登录，操作cookie
        if (userId==null){
            if (StringUtils.isBlank(list_cart_cookie)){
                list_cart.add(shoppingCar);
            }else {
                list_cart = MyJsonUtil.json_to_list(list_cart_cookie, ShoppingCar.class);
                //如果当前操作的list_cart_cookie与cookie中list_cart_cookie相同
                boolean b = if_new_cookie(list_cart,shoppingCar);
                if (b){
                    //如果不相同,在cookie中添加当前购物车
                    list_cart.add(shoppingCar);
                }else {
                    //否则修改cookie中的购物车
                    for (int i = 0;i<list_cart.size();i++){
                        ShoppingCar cart = list_cart.get(i);
                        if (cart.getSkuId()==shoppingCar.getSkuId()){
                            cart.setNumber(cart.getNumber() + shoppingCar.getNumber());
                            cart.setTotal(cart.getSkuPrice() * cart.getNumber());
                        }
                    }
                }
            }
            //最后都要覆盖cookie中的购物车
            Cookie cookie = new Cookie("list_cart_cookie",MyJsonUtil.list_to_json(list_cart));
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);
        }else {

            //获取session中的购物车
            list_cart = (List<ShoppingCar>)session.getAttribute("list_cart_session");

            //数据库是否存在
            boolean b= shoppingCarService.if_cart_exist(shoppingCar);
            if (!b){
                shoppingCarService.addCart(shoppingCar);
                if (list_cart == null || list_cart.isEmpty()){
                    list_cart = new ArrayList<ShoppingCar>();
                    list_cart.add(shoppingCar);
                    session.setAttribute("list_cart_session",list_cart);
                }else {
                    list_cart.add(shoppingCar);
                }
            }else {
                for (int i=0;i<list_cart.size();i++){
                    ShoppingCar car = list_cart.get(i);
                    if (car.getSkuId()==shoppingCar.getSkuId()){
                        car.setNumber(car.getNumber() + shoppingCar.getNumber());
                        car.setTotal(car.getSkuPrice() * car.getNumber());
                        //修改购物车
                        shoppingCarService.updateCart(car);
                    }
                }
            }
        }

        return "redirect:/cart_success.do";
    }

    //判断两个购物车是否相同
    private boolean if_new_cookie(List<ShoppingCar> list_cart, ShoppingCar shoppingCar) {
        for (int i=0;i<list_cart.size();i++){
            if (list_cart.get(i).getSkuId()==shoppingCar.getSkuId()){
                return false;
            }
        }
        return true;
    }

    @RequestMapping("/cart_success")
    public String cart_success(ModelMap map){

        return "cartSuccess";
    }
}
