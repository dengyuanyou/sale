package com.atguigu.server;

import com.atguigu.domain.User;

import javax.jws.WebService;

@WebService
public interface ILoginServer {

    public String login1(User user);

    public String login2(User user);

    //public String register(User user);


}
