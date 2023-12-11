package com.example.quinbookSearch.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value="search" ,url="10.20.2.122:8080",fallbackFactory = SearchUserfallback.class)
public interface SearchUserFeign {
    @RequestMapping(method =RequestMethod.GET,value="/user/find-all-friends")
    List<String> getAllFriendsByUserId(@RequestParam("userId") String userId);

    @RequestMapping(method =RequestMethod.GET,value="/user/find-all-friendrequests")
    public List<String> getAllFriendRequestByUserId(@RequestParam("userId") String userId);

    @RequestMapping(method =RequestMethod.GET,value="/user/find-all-sentfriendrequests")
    public List<String> getAllSentFriendRequestByUserId(@RequestParam("userId") String userId);

}
