package com.jimecc.diaryserver.utils;

import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jim
 * @Description
 * @createTime 2023年02月12日
 */
public class FastDFSUtils {

    static {
        try {
            String filePath = new ClassPathResource("config/fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
            System.out.println("FastDFS初始化成功");
        } catch (Exception e) {
            System.out.println("FastDFS Client Init Fail! ");
        }
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    public static String[] upload(MultipartFile file){
        String filename = file.getOriginalFilename();
        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        StorageClient storageClient = null;
        //获取storage客户端
        try {
            storageClient = getStorageClient();
            //上传
            try {
                uploadResults = storageClient.upload_file(file.getBytes(),filename.substring(filename.lastIndexOf(".")+1),null);
            } catch (IOException e) {
                System.out.println("IO Exception when uploadind the file:" + filename);
            }
        } catch (Exception e) {
            System.out.println("Non IO Exception when uploadind the file:" + filename);
        }
        System.out.println("upload_file time used:" + (System.currentTimeMillis() - startTime) + " ms");
        //验证上传结果
        if (uploadResults == null && storageClient != null){
            System.out.println("upload file fail, error code:" + storageClient.getErrorCode());
        }
        //上传成功返回groupName
        return uploadResults;
    }


    /**
     * 获取文件信息
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static FileInfo getFileInfo(String groupName,String remoteFileName){
        try {
            StorageClient storageClient = getStorageClient();
            return storageClient.get_file_info(groupName,remoteFileName);
        } catch (IOException e) {
            System.out.println("IO Exception: Get File from Fast DFS failed");
        }catch (Exception e) {
            System.out.println("Non IO Exception: Get File from Fast DFS failed");
        }
        return null;
    }

    /**
     * 下载
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static InputStream downFile(String groupName,String remoteFileName){
        try {
            StorageClient storageClient = getStorageClient();
            byte[] bytes = storageClient.download_file(groupName, remoteFileName);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            return inputStream;
        } catch (IOException e) {
            System.out.println("IO Exception: Get File from Fast DFS failed");
        }catch (Exception e) {
            System.out.println("Non IO Exception: Get File from Fast DFS failed");
        }
        return null;
    }

    /**
     * 删除文件
     * @param groupName
     * @param remoteFileName
     * @throws Exception
     */
    public static void deleteFile(String groupName,String remoteFileName) throws Exception {
        StorageClient storageClient = getStorageClient();
        int i = storageClient.delete_file(groupName, remoteFileName);
        System.out.println("delete file successfully!!!" + i);
    }

    /**
     * 生成Storage客户端
     * @return
     */
    private static StorageClient getStorageClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    /**
     * 生成Tracker服务器端
     * @return
     */
    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        return trackerServer;
    }

    /**
     * 获取文件路径
     * @return
     */
    public static String getTrackerUrl(){
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
            storageServer = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "http://"+storageServer.getInetSocketAddress().getHostString() + ":8888/";
    }


}
