package com.tj.asuna.biz.aq;

import com.tj.asuna.dao.mapper.AirQualityMapper;
import com.tj.asuna.dao.model.AirQualityDO;
import com.tj.asuna.model.AirQualityDetail;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author nitianyi
 * @since 2020/11/28
 */
@Service
public class AirQualityDataService {

    private static final Logger logger = LoggerFactory.getLogger(AirQualityDataService.class);

    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

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
}
