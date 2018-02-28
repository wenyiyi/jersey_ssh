package com.wyd.test;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil {  
  
    public static void main(String[] args) {  
  
        FTPClient ftpClient = new FTPClient();  
  
        try {  
            //连接指定服务器，默认端口为21  
            ftpClient.connect("172.27.160.24");  
  
            System.out.println("connect to server");  
            //获取响应字符串（FTP服务器上可设置）  
            String replyString = ftpClient.getReplyString();  
            System.out.println("replyString: " + replyString);  
            //获取响应码用于验证是否连接成功  
            int reply = ftpClient.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                System.out.println("");  
                System.exit(1);  
            }  
            //设置链接编码，windows主机UTF-8会乱码，需要使用GBK或gb2312编码  
            ftpClient.setControlEncoding("GBK");  
              
            //登录服务器  
            boolean login = ftpClient.login("luojing", "luojing");  
            if (login) {  
                System.out.println("登录成功!");  
            } else {  
                System.out.println("登录失败!");  
            }  
              
            //获取所有文件和文件夹的名字  
            FTPFile[] files = ftpClient.listFiles();  
              
            for(FTPFile file : files){  
                if(file.isDirectory()){  
                    System.out.println(file.getName() + " 是文件夹");  
                }  
                if(file.isFile()){  
                    System.out.println(file.getName() + " 是文件");  
                }  
            }  
              
            //生成InputStream用于上传本地文件  
            InputStream in = new FileInputStream("e:\\1.txt");  
              
            //上传文件  
            ftpClient.storeFile("dest.txt",in);  
            in.close();  
              
            //注销登录  
            boolean logout = ftpClient.logout();  
            if (logout) {  
                System.out.println("注销成功!");  
            } else {  
                System.out.println("注销失败!");  
            }  
  
        } catch (Exception e) {  
            e.printStackTrace();  
  
        } finally {  
            //关闭链接需要放在finally语句块中  
            if (ftpClient.isConnected()) {  
                try {  
                    ftpClient.disconnect();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
  
        }  
    }  
  
}  