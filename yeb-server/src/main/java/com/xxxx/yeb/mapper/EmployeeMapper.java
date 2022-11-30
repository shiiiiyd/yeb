package com.xxxx.yeb.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.yeb.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteEmployee(Integer id);

    /**
     * 查询
     *
     * @param employeeQuery
     * @return
     */
    List<EmployeeQuery> selectAll(EmployeeQuery employeeQuery);

    /**
     * 查询最大工号
     *
     * @return
     */
    @Select("select max(workId)+1  from t_employee")
    Integer selectMaxId();

    /**
     * 查询指定职位下有没有雇员
     * @param posId
     * @return
     */
   public int getCountByPosition(@Param("posId") int posId);


    /**
     * 查询指定职位下有没有雇员
     * @param departmentId
     * @return
     */
    public int getCountByDeptment(@Param("departmentId") int departmentId);
}
