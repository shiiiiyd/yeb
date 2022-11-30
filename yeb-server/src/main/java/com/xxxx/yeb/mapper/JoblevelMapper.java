package com.xxxx.yeb.mapper;

import com.xxxx.yeb.pojo.Joblevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
public interface JoblevelMapper extends BaseMapper<Joblevel> {

    List<String> selectTitleByName(String name);
    List<Integer> selectIdByName(String name);

    String selectNameById(Integer id);

    String selectTitleById(Integer id);

    @Override
    int deleteById(Serializable id);

    int selectEmployeeById(Integer id);

    void updateEmployeeById(Integer id);

}
