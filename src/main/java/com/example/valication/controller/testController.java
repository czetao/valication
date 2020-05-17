package com.example.valication.controller;

import com.example.valication.param.AclParam;
import com.example.valication.param.UserInfo;
import com.example.valication.utils.BeanValidator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class testController {

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String teseParm( @Valid @RequestBody AclParam aclParam){
        System.out.println(aclParam);
        //BeanValidator.check(aclParam);
        return "true";
    }
}
