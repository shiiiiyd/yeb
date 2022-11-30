package com.xxxx.yeb.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxx.yeb.mapper.DepartmentMapper;
import com.xxxx.yeb.mapper.EmployeeMapper;
import com.xxxx.yeb.mapper.SalaryMapper;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.pojo.Salary;
import com.xxxx.yeb.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Resource
    private SalaryMapper salaryMapper;

    @Autowired
    private ISalaryService iSalaryService;

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @ApiOperation(value = "工资账套管理列表")
    @RequestMapping("/sob/")
    public List<Salary> salaryList(){
        return iSalaryService.salaryList();
    }



    @DeleteMapping("/sob/")
    @ApiOperation(value = "根据传过来的ID进行数据删除")
    public RespBean deleteSalary( Integer id) {
        //判断是否有级联关系
        /*if(employeeMapper.count(id)==0){
            //调用删除方法
            return iSalaryService.deleteSalary(id);
        }*/
       return RespBean.error("该账套与员工表有级联关系，删除失败");

    }
    /**
     * 添加
     *
     * @param req
     * @param res
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/sob/")
    public RespBean addSalary(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = getStringFromStream(req);
        Salary salary = objectMapper.readValue(s, Salary.class);
        String name = salary.getName();
        if(null==name){
            return RespBean.error("添加帐套的名字不能为空");
        }
        //工资账套名不能重复
        int count =salaryMapper.countByName(name);
        if(count>0){
            return RespBean.error("工资账套名不能重复");
        }
        //更新时间
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        salary.setCreateDate(sdf.parse(dateNowStr));
        int line = salaryMapper.insert(salary);
        if (line == 0) {
            return RespBean.error("添加失败");
        }
        return RespBean.success("添加成功");
    }

    /**
     * 编辑
     *
     * @param req
     * @param res
     * @return
     * @throws JsonProcessingException
     */
    @PutMapping( value = "/sob/")
    public RespBean editSalary(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = getStringFromStream(req);
        Salary salary = objectMapper.readValue(s, Salary.class);
        int line = salaryMapper.updateById(salary);
        if (line == 0) {
            return RespBean.error("更新失败");
        }
        return RespBean.success("更新成功");
    }

    /**
     * 后台要想从Request Payload中得到自己想要的数据,就要从流中来获取数据
     *
     * @param req
     * @return
     */
    private String getStringFromStream(HttpServletRequest req) {
        ServletInputStream is;
        try {
            is = req.getInputStream();
            int nRead = 1;
            int nTotalRead = 0;
            byte[] bytes = new byte[10240];
            while (nRead > 0) {
                nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
                if (nRead > 0) {
                    nTotalRead = nTotalRead + nRead;
                }
            }
            String str = new String(bytes, 0, nTotalRead, "utf-8");
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }



}
