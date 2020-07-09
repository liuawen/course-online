package com.course.server.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.InputStream;

public class VodUtil {

    /**
     * 使用AK初始化VOD客户端
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws ClientException
     */
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        // 点播服务接入区域，国内请填cn-shanghai，其他区域请参考文档[点播中心](~~98194~~)
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    /**
     * 获取视频上传地址和凭证
     * @param vodClient
     * @return
     * @throws ClientException
     */
    public static CreateUploadVideoResponse createUploadVideo(DefaultAcsClient vodClient, String fileName) throws ClientException {
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setFileName(fileName);
        request.setTitle(fileName);
        //request.setDescription("this is desc");
        //request.setTags("tag1,tag2");
//        request.setCoverURL("http://vod.aliyun.com/test_cover_url.jpg");
        request.setCateId(1000115308L);
        request.setTemplateGroupId("78fffb8c0c2426efd5baaaafed76fe36");
        //request.setWorkflowId("");
        //request.setStorageLocation("");
        //request.setAppId("app-1000000");
        //设置请求超时时间
        request.setSysReadTimeout(1000);
        request.setSysConnectTimeout(1000);
        return vodClient.getAcsResponse(request);
    }

    /**
     * 使用上传凭证和地址初始化OSS客户端（注意需要先Base64解码并Json Decode再传入）
     * @param uploadAuth
     * @param uploadAddress
     * @return
     */
    public static OSSClient initOssClient(JSONObject uploadAuth, JSONObject uploadAddress) {
        String endpoint = uploadAddress.getString("Endpoint");
        String accessKeyId = uploadAuth.getString("AccessKeyId");
        String accessKeySecret = uploadAuth.getString("AccessKeySecret");
        String securityToken = uploadAuth.getString("SecurityToken");
        return new OSSClient(endpoint, accessKeyId, accessKeySecret, securityToken);
    }

    /**
     * 简单上传
     * @param ossClient
     * @param uploadAddress
     * @param inputStream
     */
    public static void uploadLocalFile(OSSClient ossClient, JSONObject uploadAddress, InputStream inputStream){
        String bucketName = uploadAddress.getString("Bucket");
        String objectName = uploadAddress.getString("FileName");
        // 单文件上传
        ossClient.putObject(bucketName, objectName, inputStream);

        /* 视频点播不支持追加上传
        // 追加上传
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("text/plain");
        AppendObjectRequest request = new AppendObjectRequest(bucketName, objectName, file, meta);
        request.setPosition(0L);
        ossClient.appendObject(request);*/
    }

    /**
     * 上传本地文件
     * @param ossClient
     * @param uploadAddress
     * @param localFile
     */
    public static void uploadLocalFile(OSSClient ossClient, JSONObject uploadAddress, String localFile){
        String bucketName = uploadAddress.getString("Bucket");
        String objectName = uploadAddress.getString("FileName");
        File file = new File(localFile);
        // 单文件上传
         ossClient.putObject(bucketName, objectName, file);

        /* 视频点播不支持追加上传
        // 追加上传
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("text/plain");
        AppendObjectRequest request = new AppendObjectRequest(bucketName, objectName, file, meta);
        request.setPosition(0L);
        ossClient.appendObject(request);*/
    }

    /**
     * 刷新上传凭证
     * @param vodClient
     * @return
     * @throws ClientException
     */
    public static RefreshUploadVideoResponse refreshUploadVideo(DefaultAcsClient vodClient) throws ClientException {
        RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
        request.setAcceptFormat(FormatType.JSON);
        request.setVideoId("VideoId");
        //设置请求超时时间
        request.setSysReadTimeout(1000);
        request.setSysConnectTimeout(1000);
        return vodClient.getAcsResponse(request);
    }

    /**
     * 获取源文件信息
     * @param client 发送请求客户端
     * @return GetMezzanineInfoResponse 获取源文件信息响应数据
     * @throws Exception
     */
    public static GetMezzanineInfoResponse getMezzanineInfo(DefaultAcsClient client, String videoId) throws Exception {
        GetMezzanineInfoRequest request = new GetMezzanineInfoRequest();
        request.setVideoId(videoId);
        //源片下载地址过期时间
        request.setAuthTimeout(3600L);
        return client.getAcsResponse(request);
    }

    /**
     * 获取播放凭证函数
     * @param client
     * @return
     * @throws Exception
     */
    public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client, String videoId) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        return client.getAcsResponse(request);
    }

    public static void main(String[] argv) {
        //您的AccessKeyId
        String accessKeyId = "LTAI4FnmXZVs9Pufn8kt2whV";
        //您的AccessKeySecret
        String accessKeySecret = "yQqZJ0WGnyAxaT9Gy1mKIJXY68F6A6";
        //需要上传到VOD的本地视频文件的完整路径，需要包含文件扩展名
        String localFile = "D:\\imooc\\course\\workspace\\course\\admin\\public\\static\\image\\小节视频\\test.mp4";
        try {
            // 初始化VOD客户端并获取上传地址和凭证
            DefaultAcsClient vodClient = initVodClient(accessKeyId, accessKeySecret);
            String fileName = "test.mp4";
            CreateUploadVideoResponse createUploadVideoResponse = createUploadVideo(vodClient, fileName);
            // 执行成功会返回VideoId、UploadAddress和UploadAuth
            String videoId = createUploadVideoResponse.getVideoId();
            JSONObject uploadAuth = JSONObject.parseObject(
                    Base64.decodeBase64(createUploadVideoResponse.getUploadAuth()), JSONObject.class);
            JSONObject uploadAddress = JSONObject.parseObject(
                    Base64.decodeBase64(createUploadVideoResponse.getUploadAddress()), JSONObject.class);
            // 使用UploadAuth和UploadAddress初始化OSS客户端
            OSSClient ossClient = initOssClient(uploadAuth, uploadAddress);
            // 上传文件，注意是同步上传会阻塞等待，耗时与文件大小和网络上行带宽有关
            uploadLocalFile(ossClient, uploadAddress, localFile);
            System.out.println("上传视频成功, VideoId : " + videoId); // 7d6b8c07ab48456e932187080f42e88f

            GetMezzanineInfoResponse response = new GetMezzanineInfoResponse();
            response = getMezzanineInfo(vodClient, videoId);
            System.out.println("获取视频信息, response : " + JSON.toJSONString(response));
        } catch (Exception e) {
            System.out.println("上传视频失败, ErrorMessage : " + e.getLocalizedMessage());
        }
    }
}
