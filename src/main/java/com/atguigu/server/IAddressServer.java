package com.atguigu.server;


import javax.jws.WebService;

@WebService
public interface IAddressServer {

    public String selectAllAddress();

    String get_address(Integer id);
}
