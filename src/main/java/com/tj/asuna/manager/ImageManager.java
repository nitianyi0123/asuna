package com.tj.asuna.manager;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nitianyi
 * @date 2021/3/2
 */
@Component
public class ImageManager {

    private static final Logger log = LoggerFactory.getLogger(ImageManager.class);

    private static final Pattern imagePattern = Pattern.compile("https?://.*/(.*?\\..*?)$");

    @Autowired
    private CloseableHttpClient httpClient;


    public void downloadAndSave(String url, String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filename;
        Matcher m = imagePattern.matcher(url);
        if (m.find()) {
            filename = m.group(1);
        } else {
            log.warn("invalid url");
            return;
        }
        File file = new File(dir + "/" + filename);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse resp = httpClient.execute(request)) {
                fos.write(EntityUtils.toByteArray(resp.getEntity()));
            }
        } catch (Exception e) {
            log.error("download image occur error, url: {}", url, e);
        }
    }
}
