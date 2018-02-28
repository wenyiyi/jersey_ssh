package com.wyd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController extends SuperController{
	
	@RequestMapping("employeeUpdate.do")
    public String update() {
        return "update";
    }
}
