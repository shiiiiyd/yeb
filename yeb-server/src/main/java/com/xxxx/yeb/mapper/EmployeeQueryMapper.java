package com.xxxx.yeb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.yeb.pojo.Employee;
import com.xxxx.yeb.pojo.EmployeeQuery;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
public interface EmployeeQueryMapper extends BaseMapper<Employee> {

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteEmployee(Integer id);

    /**
     * 查询
     *
     * @param employee
     * @return
     */
    List<EmployeeQuery> selectAll(EmployeeQuery employee);

    /**
     * 查询最大工号
     *
     * @return
     */
    @Select("select max(workId)+1  from t_employee")
    Integer selectMaxId();
}
