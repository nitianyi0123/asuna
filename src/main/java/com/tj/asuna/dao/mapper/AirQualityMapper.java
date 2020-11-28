package com.tj.asuna.dao.mapper;

import com.tj.asuna.dao.model.AirQualityDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nitianyi
 * @since 2020/11/28
 */
@Mapper
@Repository
public interface AirQualityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AirQualityDO record);

    AirQualityDO selectByPrimaryKey(Long id);

    List<AirQualityDO> selectAll();

    int updateByPrimaryKey(AirQualityDO record);
}