package com.tj.asuna.manager;

import com.tj.asuna.model.WbMsgInfo;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nitianyi
 * @date 2021/2/27
 */
@Component
public class WeiboManager {

    private static final Logger log = LoggerFactory.getLogger(WeiboManager.class);

    private static final String subToken = "_2A25NOXGkDeRhGeFL6VUU8ivFzTiIHXVuT-RsrDV8PUNbmtANLRjxkW9NQleH9BSwahi7-fhTYx1vv2vcMg0Y9qAe";

    @Autowired
    private CloseableHttpClient httpClient;

    private static final Pattern cardPattern = Pattern.compile("<!--card-wrap-->([\\s\\S]*?)<!--/card-wrap-->");
    private static final Pattern contentPattern = Pattern.compile("<p class=\"txt\" node-type=\"feed_list_content\" nick-name=\"(.*)\">\\n(.*)</p>(\\n\\s*\\n*\\s*<p class=\"txt\" node-type=\"feed_list_content_full\" nick-name=\"(.*)\" style=\"display: none\">\\n(.*))?");
    private static final Pattern picPattern = Pattern.compile("<li><img src=\"(.*?)\".*?action-type=\"fl_pics\" suda-data=\"key=tblog_search_weibo&value=weibo_ss_1_pic\"></li>");
    private static final Pattern timePattern = Pattern.compile("wb_time\">\\n\\s*(.*)");
    private static final Pattern userPattern = Pattern.compile("<a href=\"//weibo\\.com/(.*)\\?refer_flag=1001030103_\" class=\"name\" target=\"_blank\" nick-name=");
    private static final Pattern locationPattern = Pattern.compile("所在地.*?pt_detail\\\\\">(.*?)<\\\\/span>");
    private static final Pattern genderPattern = Pattern.compile("性别.*?pt_detail\\\\\">(.*?)<\\\\/span>");
    private static final Pattern birthdayPattern = Pattern.compile("生日.*?pt_detail\\\\\">(.*?)<\\\\/span>");

    public List<WbMsgInfo> searchFurtherRetry(String keyword, int page, String date, int retry) {
        for (int i = 0; i < retry; i++) {
            try {
                List<WbMsgInfo> results = searchFurther(keyword, page, date);
                if (!CollectionUtils.isEmpty(results)) {
                    log.info("关键词: {}, 第{}页, 日期: {}, 爬取成功", keyword, page, date);
                    return results;
                }
            } catch (Exception e) {
                log.error("searchFurtherRetry occur error, keyword: {}, page: {}, date: {}", keyword, page, date, e);
            }
            log.info("关键词: {}, 第{}页, 日期: {}, 未成功爬取，进行第{}次重试...", keyword, page, date, i);
        }
        return Collections.emptyList();
    }

    public List<WbMsgInfo> searchFurther(String keyword, int page, String date) {
        log.info("关键词: {}, 第{}页, 日期: {}, 正在爬取...", keyword, page, date);
        List<WbMsgInfo> wbMsgInfos = new ArrayList<>();
        String result = search(keyword, page, date);
        Matcher m = cardPattern.matcher(result);
        while (m.find()) {
            WbMsgInfo wbMsgInfo = new WbMsgInfo();
            Matcher m1 = contentPattern.matcher(m.group(1));
            if (m1.find()) {
                wbMsgInfo.setNickname(m1.group(1));
                if (m1.group(3) != null) {
                    wbMsgInfo.setArticle(m1.group(3).trim());
                } else {
                    wbMsgInfo.setArticle(m1.group(2).trim());
                }
                Matcher m2 = picPattern.matcher(m.group(1));
                while (m2.find()) {
                    wbMsgInfo.addPicUrl("https:" + m2.group(1));
                }
                Matcher m3 = timePattern.matcher(m.group(1));
                if (m3.find()) {
                    wbMsgInfo.setPostTime(m3.group(1));
                }
            }
            Matcher m4 = userPattern.matcher(m.group(1));
            if (m4.find()) {
                String userInfo = userInfo(m4.group(1));
                Matcher m5 = locationPattern.matcher(userInfo);
                if (m5.find()) {
                    wbMsgInfo.setLocation(m5.group(1));
                }
                Matcher m6 = genderPattern.matcher(userInfo);
                if (m6.find()) {
                    wbMsgInfo.setGender(m6.group(1));
                }
                Matcher m7 = birthdayPattern.matcher(userInfo);
                if (m7.find()) {
                    wbMsgInfo.setBirthday(m7.group(1));
                }
            }
            wbMsgInfos.add(wbMsgInfo);
        }
        return wbMsgInfos;
    }

    public String search(String keyword, int page, String date) {
        try {
            String url = "https://s.weibo.com/weibo?q=" +
                    URLEncoder.encode(keyword, StandardCharsets.UTF_8.displayName()) +
                    "&scope=ori&Refer=SWeibo_box&page=" + page + "&timescope=custom:" + date + ":" + date;
            return curl(url, subToken);
        } catch (Exception e) {
            log.error("weibo search occur error, keyword: {}, page: {}, date: {}", keyword, page, date, e);
        }
        return "";
    }

    public String userInfo(String id) {
        try {
            String url = "https://weibo.com/p/100505" + id + "/info?mod=pedit_more";
            return curl(url, subToken);
        } catch (Exception e) {
            log.error("weibo get userInfo occur error, id: {}", id, e);
        }
        return "";
    }

    public String curl(String url, String subToken) {
        HttpGet request = new HttpGet(url);
        request.addHeader("Cookie", "SUB=" + subToken + ";");
        try (CloseableHttpResponse resp = httpClient.execute(request)) {
            return EntityUtils.toString(resp.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
