package com.tj.asuna.dao.mapper;

import com.tj.asuna.dao.model.AirQualityDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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
     * 查询某城市的所有检测点名称
     *
     * @param area
     * @return
     */
    Set<String> selectDistinctPosition(String area);

    /**
     * 查询
     *
     * @param area
     * @param positionName
     * @param limit
     * @param offset
     * @return
     */
    List<AirQualityDO> selectByAreaAndPosition(@Param("area") String area,
                                               @Param("positionName") String positionName,
                                               @Param("limit") Long limit,
                                               @Param("offset") Long offset);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(AirQualityDO record);
}