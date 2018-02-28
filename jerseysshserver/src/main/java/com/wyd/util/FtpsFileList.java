package com.wyd.util;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;  
import java.util.UUID;  
import java.util.Vector;

import javax.ws.rs.core.StreamingOutput;

import com.jcraft.jsch.Channel;  
import com.jcraft.jsch.ChannelSftp;  
import com.jcraft.jsch.JSch;  
import com.jcraft.jsch.Session;  
import com.jcraft.jsch.SftpException;  
  
public class FtpsFileList {  
  
    private static ChannelSftp sftp = null;  
    //账号  
    private static String user = "root";  
    //主机ip  
    private static String host =  "172.27.160.24";  
    //密码  
    private static String password = "Abcd@1234";  
    //端口  
    private static int port = 22;  
    //上传地址  
    private static String directory = "/usr/local/file";  
    //下载目录  
    private static String saveFile = "D://test/";  
      
    static { 
        FtpsFileList ftp = new FtpsFileList();  
        try {  
            JSch jsch = new JSch();  
  
            //获取sshSession  账号-ip-端口  
            Session sshSession =jsch.getSession(user, host,port);  
            //添加密码  
            sshSession.setPassword(password);  
            Properties sshConfig = new Properties();  
            //严格主机密钥检查  
            sshConfig.put("StrictHostKeyChecking", "no");  
              
            sshSession.setConfig(sshConfig);  
            //开启sshSession链接  
            sshSession.connect();  
            //获取sftp通道  
            Channel channel = sshSession.openChannel("sftp");  
            //开启  
            channel.connect();  
            sftp = (ChannelSftp) channel;  
        } catch (Exception e) {  
            e.printStackTrace();  
      }
    }
      
    /** 
     *  
     * @param uploadFile 上传文件的路径 
     * @return 服务器上文件名 
     */  
    public static String upload(String uploadFile) {  
        File file = null;  
        String fileName = null;  
        try {  
            sftp.cd(directory);  
            file = new File(uploadFile);  
            //获取随机文件名  
            fileName  = UUID.randomUUID().toString() + file.getName().substring(file.getName().length()-5);  
            //文件名是 随机数加文件名的后5位  
            sftp.put(new FileInputStream(file), fileName);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return file == null ? null : fileName;  
    } 
    
    /** 
     *  
     * @param uploadFile 上传文件的路径 
     * @return 服务器上文件名 
     */  
    public static void upload(String fileName, InputStream uploadedInputStream) {  
        try {  
            sftp.cd(directory);  
            sftp.put(uploadedInputStream, fileName);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
      
    /** 
     * 下载文件 
     *  
     * @param directory 
     *            下载目录 
     * @param downloadFile 
     *            下载的文件名 
     * @param saveFile 
     *            存在本地的路径 
     * @param sftp 
     */  
    public static void download(String downloadFileName) {  
        try {  
            sftp.cd(directory);  
            File file = new File(saveFile+downloadFileName);  
            sftp.get(downloadFileName, new FileOutputStream(file));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    /** 
     * 删除文件 
     *  
     * @param deleteFile 
     *            要删除的文件名字 
     * @param sftp 
     */  
    public static void delete(String deleteFile) {  
        try {  
            sftp.cd(directory);  
            sftp.rm(deleteFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 列出目录下的文件 
     *  
     * @param directory 
     *            要列出的目录 
     * @param sftp 
     * @return 
     * @throws SftpException 
     */  
    public static Vector listFiles(String directory)  
            throws SftpException {  
        return sftp.ls(directory);  
    }
    
    public static void main(String[] args) throws Exception {
    	FtpsFileList.download("f9174937-e201-43df-a6d3-59a5b9cd7d0et.txt");
	}
}  