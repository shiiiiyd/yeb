package com.xxxx.yeb.service;

import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.pojo.Salary;
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
public interface ISalaryService extends IService<Salary> {

   public List<Salary> salaryList();

   RespBean deleteSalary(Integer id);
}
