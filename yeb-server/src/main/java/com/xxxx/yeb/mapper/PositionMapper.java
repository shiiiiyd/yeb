package com.xxxx.yeb.mapper;

import com.xxxx.yeb.pojo.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
public interface PositionMapper extends BaseMapper<Position> {
    /**
     * 根据名称查询指定职位信息
     * @param name
     * @return
     */
    public Position findByName(String name);

    /**
     * 根据职位名称调用数据层查询是否已经存在的职位信息
     * @param name
     * @return
     */
    public int getCountByName(@Param("name") String name);
    /**
     * 批量更新
     */
    void batchUpdateDemo(@Param("list") List<Integer> list);
}
