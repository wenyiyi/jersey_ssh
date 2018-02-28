package com.wyd.resource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.wyd.domain.Record;
import com.wyd.service.RecordService;
import com.wyd.util.FtpsFileList;

@Component
@Path("/file")
public class FileResource {
	
	@Autowired
	private RecordService recordService;
	private String filePath = "D://test//";
	private StreamingOutput fileStream;
	
	/**
	 * upload file to Linux
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @return
	 */
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		String filename = fileDetail.getFileName();
		FtpsFileList.upload(filename, uploadedInputStream);
		String output = "File uploaded to linux success !" ;
		return Response.status(200).entity(output).build();
	}
	
	/**
	 * download file from Linux
	 * @param filename
	 * @return
	 */
	@GET
    @Path("/download/{filename}")
    public Response downloadFile(
    		@PathParam("filename") final String filename){
		FtpsFileList.download(filename);
        return Response.status(200).entity(filename + " download to D://test/ success!").build();
    } 
	
	public StreamingOutput Write(final String filename) {
		    fileStream =  new StreamingOutput(){
        	String filePath = "D://test//";
            public void write(OutputStream output) throws IOException, WebApplicationException{
                try{
                    java.nio.file.Path path = Paths.get(filePath + filename);
                    byte[] data = Files.readAllBytes(path);
                    output.write(data);
                    output.flush();
                }catch (Exception e){
                    throw new WebApplicationException();
                }
            }
        };
        return fileStream;
	}
		
	@Path("/exportPdf/{r_id}/{e_id}")
   	@GET
    public Response exportPdf(@PathParam("r_id") String r_id, @PathParam("e_id") String e_id) throws Exception {
       	Record record = recordService.getByRecordId(r_id,e_id);
       	PdfReader reader = new PdfReader("D:/test/empty.pdf"); // input PDF
        PdfStamper stamper = new PdfStamper(reader,
          new FileOutputStream("D:/test/folio.pdf")); // output PDF
        BaseFont bf = BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font
        //loop on pages (1-based)从第一页开始写
        for (int i=1; i<=reader.getNumberOfPages(); i++){
            PdfContentByte over = stamper.getOverContent(i);
            // write text 
            
            BigDecimal less = new BigDecimal(record.getDays()*62.37).
            		setScale(2, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_DOWN);
            BigDecimal tax = new BigDecimal(record.getDays()*7.79625)
            		.setScale(2, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_DOWN);
            
            BigDecimal room = new BigDecimal(record.getDays()*(62.37 - 7.79625))
            		.setScale(2, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_DOWN);
            
            System.out.println(less.toString());
            
            //打印当前的时间
            over.beginText();
            
            over.setFontAndSize(bf, 10);    // set font and size
            over.setTextMatrix(20, 762);   // set x,y position (0,0 is at the bottom left)
            over.showText(record.getPrintTime());  // set text
            
            
            //打印 From 9/01/2017 Through 9/29/2017
            over.setFontAndSize(bf, 10);    
            over.setTextMatrix(220, 680);
            
            over.showText("From "+record.getStart()+" Through "+ record.getEnd());
            
            //打印 Room: 223
            over.setFontAndSize(bf, 10);   
            over.setTextMatrix(522, 647);
            
            over.showText(" "+record.getRoom()); 
            
            //打印 Arrival: 9/01/2017
            over.setFontAndSize(bf, 10);   
            over.setTextMatrix(522, 633);   
            over.showText(" "+record.getStart()); 
            
           //打印 Departure离开（apartment公寓）: 9/29/2017
            over.setFontAndSize(bf, 10);   
            over.setTextMatrix(522, 619);  
            over.showText(" "+record.getEnd());  
            
            //打印  Folio # 166013
            over.setFontAndSize(bf, 10);   
            over.setTextMatrix(32, 647);  
            over.showText("Folio # "+record.getFolio()); 
            
            //打印YUAN XIAOYING
            over.setFontAndSize(bf, 10);   
            over.setTextMatrix(32, 633);  
            over.showText(record.getEmployee().getName().toUpperCase());
            
            //打印Room Charges
            over.setFontAndSize(bf, 10);   
            over.setTextMatrix(538, 506);  
            over.showText("$"+room.toString());
            
            //打印Tax：$200.99
            over.setFontAndSize(bf, 10);   
            over.setTextMatrix(544, 467);  
            over.showText("$"+tax.toString());
            
            //打印Less Payments:$1,827.23
            over.setFontAndSize(bf, 10);   
            
            //8 over.setTextMatrix(533, 455);
            int length = less.toString().length()+1;
            
            DecimalFormat df = new DecimalFormat("#,###.00"); 
            df.format(less);
            
            System.out.println(length);
            over.setTextMatrix(533, 455);
            
            //7 over.setTextMatrix(533, 455);
            int length2 = less.toString().length()+1;
            System.out.println(length);
            over.setTextMatrix(538, 455);
            over.showText("$"+less.toString());
            
            over.endText();
            stamper.close();
            
   	    }
        
       final String filename = "folio.pdf";
       fileStream = Write(filename);
	   return Response
               .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
               .header("content-disposition","attachment; filename = " + filename)
               .build();
	}
	
}
