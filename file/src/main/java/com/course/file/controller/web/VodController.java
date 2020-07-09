package com.course.file.controller.web;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.course.file.controller.admin.FileController;
import com.course.server.dto.ResponseDto;
import com.course.server.util.VodUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("webVodController")
@RequestMapping("/web")
public class VodController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @Value("${vod.accessKeyId}")
    private String accessKeyId;

    @Value("${vod.accessKeySecret}")
    private String accessKeySecret;

    public static final String BUSINESS_NAME = "VOD";

    @RequestMapping(value = "/get-auth/{vod}", method = RequestMethod.GET)
    public ResponseDto getAuth(@PathVariable String vod) throws ClientException {
        LOG.info("获取播放授权开始: ");
        ResponseDto responseDto = new ResponseDto();
        DefaultAcsClient client = VodUtil.initVodClient(accessKeyId, accessKeySecret);
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            response = VodUtil.getVideoPlayAuth(client, vod);
            LOG.info("授权码 = {}", response.getPlayAuth());
            responseDto.setContent(response.getPlayAuth());
            //VideoMeta信息
            LOG.info("VideoMeta = {}", JSON.toJSONString(response.getVideoMeta()));
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        LOG.info("获取播放授权结束");
        return responseDto;
    }
}
