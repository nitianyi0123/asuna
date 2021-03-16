package com.tj.asuna.biz.weibo;

import com.tj.asuna.manager.FacePlusManager;
import com.tj.asuna.model.FaceData;
import com.tj.asuna.model.FaceDetectResp;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author nitianyi
 * @date 2021/3/15
 */
@Service
public class ImageDetectService {

    private static final Logger log = LoggerFactory.getLogger(ImageDetectService.class);

    @Autowired
    private FacePlusManager facePlusManager;


    public void detect(String imagesPath, String excelFile) {
        File dir = new File(imagesPath);
        File[] images = dir.listFiles();
        if (images == null) {
            return;
        }
        List<FaceData> dataList = new ArrayList<>();
        for (File image : images) {
            try {
                FaceDetectResp resp = null;
                while (resp == null || StringUtils.hasText(resp.getErrorMessage())) {
                    resp = facePlusManager.detectFace(image);
                    if (resp == null || StringUtils.hasText(resp.getErrorMessage())) {
                        Thread.sleep(2000);
                    }
                }
                log.info("images total: {}, already: {}", images.length, dataList.size());
                for (FaceDetectResp.Face face : resp.getFaces()) {
                    FaceDetectResp.Attributes attributes = face.getAttributes();
                    FaceData faceData = new FaceData();
                    faceData.setImageName(image.getName());
                    faceData.setGender(attributes.getGender().get("value"));
                    faceData.setAge(attributes.getAge().get("value"));
                    faceData.setSmile((Double) attributes.getSmile().get("value"));
                    faceData.setSmileThreshold((Double) attributes.getSmile().get("threshold"));
                    Map<String, Double> emotion = attributes.getEmotion();
                    faceData.setAnger(emotion.get("anger"));
                    faceData.setDisgust(emotion.get("disgust"));
                    faceData.setFear(emotion.get("fear"));
                    faceData.setHappiness(emotion.get("happiness"));
                    faceData.setNeutral(emotion.get("neutral"));
                    faceData.setSadness(emotion.get("sadness"));
                    faceData.setSurprise(emotion.get("surprise"));
                    dataList.add(faceData);
                }
            } catch (Exception e) {
                log.error("error", e);
            }
        }
        XSSFWorkbook workbook = exportExcel(dataList);
        File excel = new File(excelFile);
        try (FileOutputStream fos = new FileOutputStream(excel)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public XSSFWorkbook exportExcel(List<FaceData> dataList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        customExcelHead(sheet);
        int rowNum = 1;
        for (FaceData data : dataList) {
            XSSFRow row = sheet.createRow(rowNum++);
            customCellValue(row, data);
        }
        return workbook;
    }

    private static final String[] HEAD = new String[]{
            "图片名", "性别", "年龄", "笑容", "笑容的阈值", "愤怒",
            "厌恶", "恐惧", "高兴", "平静", "伤心", "惊讶"
    };

    private void customExcelHead(XSSFSheet sheet) {
        XSSFRow headRow = sheet.createRow(0);
        for (int i = 0; i < HEAD.length; i++) {
            XSSFCell cell = headRow.createCell(i, CellType.STRING);
            cell.setCellValue(HEAD[i]);
        }
    }

    private void customCellValue(XSSFRow row, FaceData data) {
        row.createCell(0, CellType.STRING).setCellValue(data.getImageName());
        row.createCell(1, CellType.STRING).setCellValue(data.getGender());
        row.createCell(2, CellType.STRING).setCellValue(data.getAge());
        row.createCell(3, CellType.STRING).setCellValue(data.getSmile());
        row.createCell(4, CellType.STRING).setCellValue(data.getSmileThreshold());
        row.createCell(5, CellType.STRING).setCellValue(data.getAnger());
        row.createCell(6, CellType.STRING).setCellValue(data.getDisgust());
        row.createCell(7, CellType.STRING).setCellValue(data.getFear());
        row.createCell(8, CellType.STRING).setCellValue(data.getHappiness());
        row.createCell(9, CellType.STRING).setCellValue(data.getNeutral());
        row.createCell(10, CellType.STRING).setCellValue(data.getSadness());
        row.createCell(11, CellType.STRING).setCellValue(data.getSurprise());
    }
}
