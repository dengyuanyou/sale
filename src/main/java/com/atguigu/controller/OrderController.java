package com.atguigu.controller;

import com.atguigu.domain.*;
import com.atguigu.exception.OverSaleException;
import com.atguigu.server.IAddressServer;
import com.atguigu.service.IOrderService;
import com.atguigu.service.IShoppingCarService;
import com.atguigu.util.MyJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * 订单相关
 */

@Controller
@SessionAttributes("order")
public class OrderController {

    @Autowired
    private IAddressServer addressServer;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IShoppingCarService shoppingCarService;

    /**
     * 结算
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping("/goto_checkOrder")
    public String goto_checkOrder(HttpSession session, ModelMap modelMap){
        List<ShoppingCar> list_cart = new ArrayList<ShoppingCar>();

        User user = (User)session.getAttribute("user");
        //用户没登录走结算登录页
        if (user==null){
            return "redirect:/goto_login_checkOrder.do";
        }else {
            //获取session中的购物车
            list_cart = (List<ShoppingCar>)session.getAttribute("list_cart_session");

            //主订单
            ObjectOrder order = new ObjectOrder();
            order.setUserId(user.getId());
            order.setScheduleId(1);
            order.setTotalAmout(get_sum(list_cart));
            order.setCreateTime(new Date());

            Set<String> list_address = new HashSet<String>();
            for (int i=0;i<list_cart.size();i++){
                if (list_cart.get(i).getIsCheck().equals("1")){
                    //按照购物车的送货地址进行拆单
                    list_address.add(list_cart.get(i).getAddress());
                }
            }

            //送货清单集合
            List<ObjectFlow> list_flow = new ArrayList<ObjectFlow>();

            Iterator<String> iterator = list_address.iterator();
            while (iterator.hasNext()){
                String address = iterator.next();

                //送货清单
                ObjectFlow flow = new ObjectFlow();
                flow.setCurrentPlace("商品未出库");
                flow.setUserId(user.getId());
                flow.setDeliver("硅谷快递");
                flow.setCreateTime(new Date());

                //订单详情集合
                List<OrderInfo> list_info = new ArrayList<OrderInfo>();

                //根据地址将购物车转化成订单详情
                for (int i=0;i<list_cart.size();i++){
                    if (list_cart.get(i).getIsCheck().equals("1")&&list_cart.get(i).getAddress().equals(address)){

                        //购物车
                        ShoppingCar cart = list_cart.get(i);

                        //订单详情
                        OrderInfo order_Info = new OrderInfo();
                        order_Info.setShoppingCarId(cart.getId());
                        order_Info.setProductImage(cart.getImageUrl());
                        order_Info.setSkuId(cart.getSkuId());
                        order_Info.setSkuPrice(new BigDecimal(cart.getSkuPrice()));
                        order_Info.setSkuAddress(cart.getAddress());
                        order_Info.setSkuName(cart.getSkuName());
                        order_Info.setSkuNumber(cart.getNumber());
                        order_Info.setCreateTime(new Date());

                        //将订单详情放到订单详情集合中
                        list_info.add(order_Info);

                    }
                }

                //将订单详情集合放到送货清单中
                flow.setList_info(list_info);

                //将送货清单放到送货清单集合中
                list_flow.add(flow);

            }

            //将送货清单集合设置到主订单中
            order.setList_flow(list_flow);
            //将主订单放到整个controller中，在这个controller的所有方法中都可以取到

            //获取用户地址
            String address = addressServer.selectAllAddress();
            List<Address> list_addr = MyJsonUtil.json_to_list(address, Address.class);

            modelMap.put("list_addr",list_addr);
            modelMap.put("order",order);

        }

        return "checkOrder";
    }

    /**
     * 提交订单
     * @param order
     * @return
     */
    @RequestMapping("save_order")
    public String save_order(HttpSession session, @ModelAttribute("order") ObjectOrder order,Address address){

        User user = (User)session.getAttribute("user");
        //调用远程地址接口
        String addr_json = addressServer.get_address(address.getId());
        Address addr = MyJsonUtil.json_to_object(addr_json, Address.class);

        //保存订单
        orderService.save_order(addr,order);

        //同步session中的购物车
        List<ShoppingCar> list_cart = shoppingCarService.get_list_cart_by_user(user);
        session.setAttribute("list_cart_session",list_cart);

        //重定向到支付系统
        return "redirect:/goto_pay.do";
    }

    /**
     * 调用支付
     * @return
     */
    @RequestMapping("/goto_pay")
    public String goto_pay(){
        return "pay";
    }

    /**
     * 支付成功
     * @return
     */
    @RequestMapping("/pay_success")
    public String pay_success(@ModelAttribute("order") ObjectOrder order){
        //支付成功之后修改订单信息
        try {
            orderService.pay_success(order);
        } catch (OverSaleException e) {
            e.printStackTrace();
            return "redirect:/order_fail.do";
        }
        //重定向到成功页面
        return "redirect:/order_success.do";
    }

    /**
     *支付成功页面
     * @return
     */
    @RequestMapping("/order_success")
    public String order_success(){
        return "orderSuccess";
    }

    /**
     *支付失败页面
     * @return
     */
    @RequestMapping("/order_fail")
    public String order_fail(){
        return "error";
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
}
