package com.wyd.controller;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wyd.domain.Record;

@Controller
public class RecordController extends SuperController{
	private Client client = ClientBuilder.newClient();
	private WebTarget webTarget = client.target(SuperController.BASE_URI);
	private String start;
	private String end;
	
	@RequestMapping("recordShow.do")
    public ModelAndView show() {
		ModelAndView mv = new ModelAndView();
		
		GenericType<List<Record>> hsp = new GenericType<List<Record>>(){};
		//List<Record> records=  webTarget.path("/record/findAll").request().accept(MediaType.APPLICATION_JSON).get(hsp);
		List<Record> records=  webTarget.path("/record/findAll").request().get(hsp);
		
		mv.setViewName("show");//对应show.jsp
		mv.addObject("records", records);
		return mv ;
    }
}
