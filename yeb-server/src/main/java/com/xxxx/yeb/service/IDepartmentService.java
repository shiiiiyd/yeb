package com.xxxx.yeb.service;

import com.xxxx.yeb.modle.DepartmentModle;
import com.xxxx.yeb.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
public interface IDepartmentService extends IService<Department> {
    /**
     * 列表显示部门数据
     *
     * @return list集合
     */
    public List<Department>   listDepartment();

    /**
     * 列出所有departmentmoudle数据
     * @return
     */
    public List<DepartmentModle> getAllListDepartmentModle();

    /**
     * 根据name查询用户是否存在
     * @param name
     * @return
     */
    public Department findByName(String name);

    /**
     * 用户添加操作
     * @param department
     * @return
     */
    public int addDepartment(Department department);

    public void deleteDepartment(Integer id);
}
