package com.xxxx.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.pojo.Salary;
import com.xxxx.yeb.mapper.SalaryMapper;
import com.xxxx.yeb.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {
    /**
     * 加载列表
     */
    @Resource
    private SalaryMapper salaryMapper;
    public List<Salary> salaryList(){
        return salaryMapper.selectList(new QueryWrapper<>());
    }

    /**
     * 根据ID删除表
     * @param id
     * @return
     */
    @Transactional
    @Override
    public RespBean deleteSalary(Integer id) {
        Salary number=salaryMapper.querySalary(id);
        if(number != null){

            Integer count=salaryMapper.deleteSalary(id);
            if (count < 1) {
                return RespBean.error("删除失败！");
            }
        }
        return RespBean.success("删除成功！");
    }

    @Transactional

    public RespBean showAddSalaryView() {

        //创建Salary对象
        Salary salary=new Salary();
        //插入数据返回受影响的行数
        int count = salaryMapper.updateById(salary);
        //判断受影响行数
        if (count < 1) {
            return RespBean.error("添加失败！");
        }
        return RespBean.success("添加成功！");}
}
