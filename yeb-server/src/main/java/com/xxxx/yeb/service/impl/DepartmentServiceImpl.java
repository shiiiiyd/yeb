package com.xxxx.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.yeb.modle.DepartmentModle;
import com.xxxx.yeb.pojo.Department;
import com.xxxx.yeb.mapper.DepartmentMapper;
import com.xxxx.yeb.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 调用数据层进行列表显示
     * @return
     */
    @Override
    public List<Department> listDepartment() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("enabled",true);

        return departmentMapper.selectByMap(map);

    }

    @Override
    public List<DepartmentModle> getAllListDepartmentModle() {
        return departmentMapper.findAll();
    }

    @Override
    public Department findByName(String name) {

        return departmentMapper.findByName(name);
    }

    /**
     * 部门添加操作
     * @param department
     * @return
     */
    @Override
    public int addDepartment(Department department) {
        departmentMapper.updateParentId(department.getParentId());
        Department depPath = departmentMapper.findByParentId(department.getParentId());
        System.out.println(depPath);
        department.setEnabled(true);
        Integer path= Integer.parseInt(depPath.getDepPath().substring(depPath.getDepPath().lastIndexOf(".")+1))+1;

        String basepath="."+path;
        department.setDepPath(depPath.getDepPath()+basepath);
        return departmentMapper.insert(department) ;
    }

    @Override
    public void deleteDepartment(Integer id) {
        departmentMapper.deleteById(id);
    }
}