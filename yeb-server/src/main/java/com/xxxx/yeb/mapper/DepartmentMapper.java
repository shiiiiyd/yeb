package com.xxxx.yeb.mapper;

import com.xxxx.yeb.modle.DepartmentModle;
import com.xxxx.yeb.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 部门列表
     * @return
     */
    public List<DepartmentModle> findAll();

    /**
     * 根据name查询部门名称是否存在
     * @param
     * @return
     */
    public Department findByName(String name);

    /**
     * 根据符id查询父路径
     * @param
     * @return
     */
    public Department findByParentId(Integer id);
    /**
     *修改父id的isParent=true
     */
    public int updateParentId(Integer id);
}
