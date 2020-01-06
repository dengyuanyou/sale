package com.atguigu.controller;

import com.atguigu.domain.ShoppingCar;
import com.atguigu.domain.User;
import com.atguigu.server.ILoginServer;
import com.atguigu.service.IShoppingCarService;
import com.atguigu.service.IUserService;
import com.atguigu.util.MyJsonUtil;
import com.atguigu.util.MyPropertyUtil;
import com.atguigu.util.MyWsFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 用户登录
 */
@Controller
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IShoppingCarService shoppingCarService;

    @Autowired
    private ILoginServer loginServer;

    @RequestMapping("/login")
    public String login(@RequestParam(value = "redirect",required = false) String redirect,@CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie, HttpServletResponse response, HttpSession session, User user, ModelMap map, String dataSource_type){

        User loginUser = new User();

        String login = "";

        //调用远程登录接口服务
        if (dataSource_type.equals("1")){
            login = loginServer.login1(user);
        }else if (dataSource_type.equals("2")){
            login = loginServer.login2(user);
        }

        loginUser = MyJsonUtil.json_to_object(login,User.class);

        if (loginUser==null){
            return "redirect:/login.do";
        }else {
           session.setAttribute("user",loginUser);
            try {

                //在客户端保存用户的个性化设置
                Cookie cookie = new Cookie("name", URLEncoder.encode(loginUser.getName(),"utf-8"));
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);

                Cookie cookie2 = new Cookie("nickname", URLEncoder.encode("周润发","utf-8"));
                cookie2.setPath("/");
                cookie2.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie2);
                
                //同步购物车
                combine_cart(response,session,list_cart_cookie,loginUser);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        //如果redirect为空则为正常登录
        if (StringUtils.isBlank(redirect)){
            //防止表单重复提交，登录必须重定向到首页
            return "redirect:/index.do";
            //否则为结算登录
        }else {
            return "redirect:/"+redirect;
        }
    }

    private void combine_cart(HttpServletResponse response, HttpSession session, String list_cart_cookie,User user) {

        List<ShoppingCar> list_cart;
        list_cart = MyJsonUtil.json_to_list(list_cart_cookie,ShoppingCar.class);
        //查询这个用户的购物车列表
        List<ShoppingCar> list_cart_db = shoppingCarService.get_list_cart_by_user(user);

        if (list_cart==null){

        }else {
            for (int i=0;i<list_cart.size();i++){
                ShoppingCar cart = list_cart.get(i);
                cart.setUserId(user.getId());
                boolean b = shoppingCarService.if_cart_exist(list_cart.get(i));
                if (b){
                    //更新
                    for (int j=0;j<list_cart_db.size();j++){
                        if (list_cart_db.get(j).getSkuId()== cart.getSkuId()){
                            cart.setNumber(list_cart_db.get(j).getNumber() + cart.getNumber());
                            cart.setTotal(cart.getSkuPrice() * cart.getNumber());
                            shoppingCarService.updateCart(cart);
                        }
                    }

                }else {
                    //添加
                    shoppingCarService.addCart(list_cart.get(i));
                }
            }
        }

        //同步session，清空cookie
        session.setAttribute("list_cart_session",shoppingCarService.get_list_cart_by_user(user));
        response.addCookie(new Cookie("list_cart_cookie",""));

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

}
