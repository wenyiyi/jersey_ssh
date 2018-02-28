package com.wyd.resource;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wyd.domain.Employee;
import com.wyd.domain.Record;
import com.wyd.service.RecordService;
import com.wyd.util.DateUtil;

@Component
@Path("/record")
public class RecordResource {

	@Autowired
	private RecordService recordService;
	
    @Path("/findAll")
	@GET
    public List<Record> findAll() {
    	List<Record> records = recordService.findAll();
	    return records;
	}
    
    @Path("/save")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response save(@FormDataParam("e_id") String e_id,
    		@FormDataParam("name") String name,
    		@FormDataParam("start") String start,
    		@FormDataParam("end") String end,
    		@FormDataParam("location") String location,
    		@FormDataParam("room") String room,
    		@FormDataParam("folio") String folio
    		) throws Exception {
    	
    	Date date = new Date();
    	Record record = new Record();
    	Employee employee = new Employee();
    	employee.setE_id(Integer.valueOf(e_id));
    	employee.setName(name);
    	record.setStart(start);
    	record.setEnd(end);
    	record.setRoom(room);
    	record.setFolio(folio);
    	record.setLocation(location);
    	record.setEmployee(employee);
    	record.setDays(DateUtil.calculateDays(end, start));
		record.setPrintTime(DateUtil.gainPrintTime(date));
		record.setEmployee(employee);
		Random random  = new Random();
		record.setR_id(String.valueOf(random.nextInt(1000000000)));
		
		recordService.save(record);
    	return Response.status(200).entity("Add Success!").build();
	}
}
