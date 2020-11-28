package com.tj.asuna.dao.mapper;

import com.tj.asuna.dao.model.AirQualityDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author nitianyi
 * @since 2020/11/28
 */
@Mapper
@Repository
public interface AirQualityMapper {

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insert(AirQualityDO record);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    AirQualityDO selectByPrimaryKey(Long id);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(AirQualityDO record);
}