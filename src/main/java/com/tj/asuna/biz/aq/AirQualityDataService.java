package com.tj.asuna.biz.aq;

import com.tj.asuna.dao.mapper.AirQualityMapper;
import com.tj.asuna.dao.model.AirQualityDO;
import com.tj.asuna.model.AirQualityDetail;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author nitianyi
 * @since 2020/11/28
 */
@Service
public class AirQualityDataService {

    private static final Logger logger = LoggerFactory.getLogger(AirQualityDataService.class);

    private SqlSessionTemplate sqlSessionTemplate;
    private AirQualityMapper airQualityMapper;

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Autowired
    public void setAirQualityMapper(AirQualityMapper airQualityMapper) {
        this.airQualityMapper = airQualityMapper;
    }

    /**
     * 持久化数据
     *
     * @param data
     */
    public void save(List<AirQualityDetail> data) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory()
                .openSession(ExecutorType.BATCH, false);
        AirQualityMapper mapper = sqlSession.getMapper(AirQualityMapper.class);
        try {
            long currentTimeMillis = System.currentTimeMillis();
            for (int i = 0; i < data.size(); i++) {
                AirQualityDO record = convert(data.get(i));
                record.setCtime(currentTimeMillis);
                record.setMtime(currentTimeMillis);
                record.setVersion(0);
                mapper.insert(record);

                if (i % 1000 == 0 || i == data.size() - 1) {
                    sqlSession.commit();
                    sqlSession.clearCache();
                }
            }
        } catch (Exception e) {
            logger.error("air quality detail data save occur error", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    private static final long LIMIT = 1000;

    /**
     * 导出某城市所有检测点的环境质量数据
     *
     * @param area 城市
     */
    public void exportExcel(String area, OutputStream outputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            Set<String> positions = airQualityMapper.selectDistinctPosition(area);
            for (String position : positions) {
                if (!StringUtils.hasText(position)) {
                    continue;
                }
                XSSFSheet sheet = workbook.createSheet(position);
                customExcelHead(sheet);
                int rowNum = 1;
                long offset = 0;
                List<AirQualityDO> results;
                do {
                    results = airQualityMapper.selectByAreaAndPosition(area, position, LIMIT, offset);
                    for (AirQualityDO result : results) {
                        XSSFRow row = sheet.createRow(rowNum++);
                        customCellValue(row, result);
                    }
                    offset += LIMIT;
                } while (!CollectionUtils.isEmpty(results));
            }
        } catch (Exception e) {
            logger.error("query air quality data from database occur error", e);
        }
        workbook.write(outputStream);
        workbook.close();
    }

    private AirQualityDO convert(AirQualityDetail airQualityDetail) {
        AirQualityDO record = new AirQualityDO();
        record.setTimePoint(airQualityDetail.getTimePoint());
        record.setArea(airQualityDetail.getArea());
        record.setPositionName(Optional.ofNullable(airQualityDetail.getPositionName()).orElse(""));
        record.setStationCode(Optional.ofNullable(airQualityDetail.getStationCode()).orElse(""));
        record.setAqi(airQualityDetail.getAqi());
        record.setPrimaryPollutant(airQualityDetail.getPrimaryPollutant());
        record.setQuality(airQualityDetail.getQuality());
        record.setSo2(airQualityDetail.getSo2());
        record.setSo224h(airQualityDetail.getSo224h());
        record.setNo2(airQualityDetail.getNo2());
        record.setNo224h(airQualityDetail.getNo224h());
        record.setPm10(airQualityDetail.getPm10());
        record.setPm1024h(airQualityDetail.getPm1024h());
        record.setCo(airQualityDetail.getCo());
        record.setCo24h(airQualityDetail.getCo24h());
        record.setO3(airQualityDetail.getO3());
        record.setO324h(airQualityDetail.getO324h());
        record.setO38h(airQualityDetail.getO38h());
        record.setO38h24h(airQualityDetail.getO38h24h());
        record.setPm25(airQualityDetail.getPm25());
        record.setPm2524h(airQualityDetail.getPm2524h());
        return record;
    }

    private static final String[] HEAD = new String[]{
            "城市", "监测点", "时间", "AQI", "空气质量指数类别", "首要污染物",
            "PM2.5细颗粒物1小时平均", "PM2.5细颗粒物24小时滑动平均", "PM10可吸入颗粒物1小时平均", "PM10可吸入颗粒物24小时滑动平均",
            "SO2二氧化硫1小时平均", "SO2二氧化硫24小时滑动平均", "NO2二氧化氮1小时平均", "NO2二氧化氮24小时滑动平均",
            "CO一氧化碳1小时平均", "CO一氧化碳24小时滑动平均", "O3臭氧1小时平均", "O3臭氧日最大1小时平均",
            "O3臭氧8小时滑动平均", "O3臭氧日最大8小时滑动平均"
    };

    private void customExcelHead(XSSFSheet sheet) {
        XSSFRow headRow = sheet.createRow(0);
        for (int i = 0; i < HEAD.length; i++) {
            XSSFCell cell = headRow.createCell(i, CellType.STRING);
            cell.setCellValue(HEAD[i]);
        }
    }

    private void customCellValue(XSSFRow row, AirQualityDO data) {
        row.createCell(0, CellType.STRING).setCellValue(data.getArea());
        row.createCell(1, CellType.STRING).setCellValue(data.getPositionName());
        row.createCell(2, CellType.STRING).setCellValue(data.getTimePoint());
        row.createCell(3, CellType.STRING).setCellValue(data.getAqi());
        row.createCell(4, CellType.STRING).setCellValue(data.getQuality());
        row.createCell(5, CellType.STRING).setCellValue(data.getPrimaryPollutant());
        row.createCell(6, CellType.STRING).setCellValue(data.getPm25());
        row.createCell(7, CellType.STRING).setCellValue(data.getPm2524h());
        row.createCell(8, CellType.STRING).setCellValue(data.getPm10());
        row.createCell(9, CellType.STRING).setCellValue(data.getPm1024h());
        row.createCell(10, CellType.STRING).setCellValue(data.getSo2());
        row.createCell(11, CellType.STRING).setCellValue(data.getSo224h());
        row.createCell(12, CellType.STRING).setCellValue(data.getNo2());
        row.createCell(13, CellType.STRING).setCellValue(data.getNo224h());
        row.createCell(14, CellType.STRING).setCellValue(data.getCo());
        row.createCell(15, CellType.STRING).setCellValue(data.getCo24h());
        row.createCell(16, CellType.STRING).setCellValue(data.getO3());
        row.createCell(17, CellType.STRING).setCellValue(data.getO324h());
        row.createCell(18, CellType.STRING).setCellValue(data.getO38h());
        row.createCell(19, CellType.STRING).setCellValue(data.getO38h24h());
    }
}
