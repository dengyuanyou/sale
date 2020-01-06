package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 首页
 */

@Controller
public class IndexController {

    /**
     * 普通登录页
     * @return
     */
    @RequestMapping("/goto_login")
    public String goto_login(HttpServletRequest request, ModelMap map){

        return "login";
    }

    /**
     * 结算登录页
     * @return
     */
    @RequestMapping("/goto_login_checkOrder")
    public String goto_login_checkOrder(HttpServletRequest request, ModelMap map){

        return "login_chekcOrder";
    }

    //注销
    @RequestMapping("/goto_logout")
    public String goto_logout(HttpSession session,ModelMap modelMap){

        session.invalidate();

        return "redirect:/goto_login.do";

    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request, ModelMap map){

        return "index";
    }
}
