package com.tj.asuna.biz.weibo;

import com.tj.asuna.manager.ImageManager;
import com.tj.asuna.manager.WeiboManager;
import com.tj.asuna.model.WbMsgInfo;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author nitianyi
 * @date 2021/3/2
 */
@Service
public class WeiboService {

    private static final Logger log = LoggerFactory.getLogger(WeiboService.class);

    @Autowired
    private ImageManager imageManager;

    @Autowired
    private WeiboManager weiboManager;

    @Autowired
    private ExecutorService executorService;

    private static final String[] HEAD = new String[]{
            "昵称", "文字", "照片", "上传时间", "性别", "所在地", "生日"
    };

    private void createWorkbookHead(XSSFSheet sheet) {
        XSSFRow headRow = sheet.createRow(0);
        for (int i = 0; i < HEAD.length; i++) {
            XSSFCell cell = headRow.createCell(i, CellType.STRING);
            cell.setCellValue(HEAD[i]);
        }
    }

    public synchronized void searchUntilNow(String keyword) {
        String dir;
        if (System.getenv("logDir") != null) {
            dir = System.getenv("logDir") + "/" + keyword + "-" + System.currentTimeMillis();
        } else {
            dir = keyword + "-" + System.currentTimeMillis();
        }
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        Calendar now = Calendar.getInstance();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(keyword);
        createWorkbookHead(sheet);
        int rowNum = 1;
        while (cal.compareTo(now) <= 0) {
            Date d = cal.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String df = simpleDateFormat.format(d);
            for (int i = 0; i < 3; i++) {
                List<WbMsgInfo> wbMsgInfos = weiboManager.searchFurtherRetry(keyword, i, df, 2);
                for (WbMsgInfo wbMsgInfo : wbMsgInfos) {
                    XSSFRow row = sheet.createRow(rowNum++);
                    row.createCell(0, CellType.STRING).setCellValue(wbMsgInfo.getNickname());
                    row.createCell(1, CellType.STRING).setCellValue(wbMsgInfo.getArticle());
                    row.createCell(2, CellType.STRING).setCellValue(String.join(",", wbMsgInfo.getPicUrls()));
                    row.createCell(3, CellType.STRING).setCellValue(wbMsgInfo.getPostTime());
                    row.createCell(4, CellType.STRING).setCellValue(wbMsgInfo.getGender());
                    row.createCell(5, CellType.STRING).setCellValue(wbMsgInfo.getLocation());
                    row.createCell(6, CellType.STRING).setCellValue(wbMsgInfo.getBirthday());
                }
                for (WbMsgInfo wbMsgInfo : wbMsgInfos) {
                    for (String picUrl : wbMsgInfo.getPicUrls()) {
                        int page = i;
                        executorService.execute(() -> {
                            log.info("关键词: {}, 第{}页, 日期: {}, 图片下载中...", keyword, page, df);
                            imageManager.downloadAndSave(picUrl, dir + "/" + wbMsgInfo.getNickname());
                        });
                    }
                }
            }
            cal.add(Calendar.DATE, 1);
        }
        File file = new File(dir + "/data.xlsx");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
            workbook.close();
        } catch (Exception e) {
            log.error("excel file save occur error", e);
        }
    }

}
