package com.xxxx.yeb.controller;


import com.xxxx.yeb.mapper.EmployeeMapper;
import com.xxxx.yeb.pojo.*;
import com.xxxx.yeb.service.IEmployeeService;
import com.xxxx.yeb.utils.EasyPoiExcelUtil;
import io.lettuce.core.output.ScanOutput;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;
    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 查询数据
     *
     * @param
     */
    @ApiOperation(value = "初始化页面，查询所有")
    @GetMapping("/basic")
    public Map<String, Object> showPage(EmployeeQuery employeeQuery,Integer currentPage,Integer size) {
        System.out.println(employeeQuery);
        //查询出结果返回
        return employeeService.getEmpPageAll(employeeQuery, currentPage, size);
    }

    /**
     * 删除用户
     *
     * @param id 通过前台传回的用户id来删除
     */
    @ApiOperation(value = "删除用户")
    @DeleteMapping("/basic/{id}")
    @ResponseBody
    public Map<String, Object> deleteEmployee(@PathVariable Integer id) {
        System.out.println(id);
        employeeService.deleteEmployee(id);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", 200);
        hashMap.put("message", "删除成功");
        return hashMap;
    }

    /**
     * 添加用户
     *
     * @param employee
     * @return
     */
    @ApiOperation(value = "添加用户")
    @PostMapping("/basic")
    @ResponseBody
    public Map<String, Object> addEmployee(@RequestBody Employee employee) throws Exception {
        System.out.println(employee);
        return employeeService.addEmployee(employee);
    }

    /**
     * 更新用户
     *
     * @param employee
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "更新用户")
    @PutMapping("/basic")
    @ResponseBody
    public Map<String, Object> updateEmployee(@RequestBody Employee employee) throws Exception {
        //System.out.println(employee);
        return employeeService.updateEmployee(employee);
    }


    /**
     * 工号
     *
     * @return
     */
    @ApiOperation(value = "工号展示")
    @GetMapping("/basic/maxWorkID")
    @ResponseBody
    public RespBean maxWorkID() {
        return employeeService.getMaxWorkID();
    }

    /**
     * 职位展示
     *
     * @return
     */
    @ApiOperation(value = "职位展示")
    @GetMapping("/basic/positions")
    @ResponseBody
    public List<Position> positions() {
        return employeeService.positions();
    }

    /**
     * 政治面貌
     *
     * @return
     */
    @ApiOperation(value = "政治面貌展示")
    @GetMapping("/basic/politicsstatus")
    @ResponseBody
    public List<PoliticsStatus> politicsStatus() {
        return employeeService.politicsStatus();
    }

    /**
     * 民族
     *
     * @return
     */
    @ApiOperation(value = "民族展示")
    @GetMapping("/basic/nations")
    @ResponseBody
    public List<Nation> nations() {
        return employeeService.nations();
    }

    /**
     * 部门展示
     *
     * @return
     */
    @ApiOperation(value = "部门展示")
    @GetMapping("/basic/deps")
    @ResponseBody
    public List<Department> deps() {
        return employeeService.deps();
    }

    /**
     * 职称展示
     *
     * @return
     */
    @ApiOperation(value = "职称展示")
    @GetMapping("/basic/joblevels")
    @ResponseBody
    public List<Joblevel> Joblevel() {
        return employeeService.Joblevel();
    }

    /**
     * 导出数据
     * @param response
     * @param employee
     */
    @ApiOperation(value = "导出")
    @RequestMapping("/basic/export")
    @ResponseBody
    public void exportData(HttpServletResponse response,EmployeeQuery employee) {
        //查询出结果返回
        employeeService.exportData(response,employee);

    }

    /**
     * 导入数据
     * @param file
     * @throws Exception
     */
    @ApiOperation(value = "导入")
    @RequestMapping("/basic/import")
    public Map<String,Object> uploadExcel(@RequestParam("file") MultipartFile file) throws Exception {
        List<EmployeeQuery> employeeQueryList = EasyPoiExcelUtil.importExcel(file, EmployeeQuery.class);
        Map<String,Object> map = new HashMap<>();
        employeeService.importExcel(employeeQueryList);
        map.put("code",200);
        map.put("msg","success");
        return map;
    }
}


    /*public Map<String,Object> uploadExcel(@RequestParam("file") MultipartFile file) throws Exception {
        List<Employee> employeeList = EasyPoiExcelUtil.importExcel(file, Employee.class);
        Map<String,Object> map = new HashMap<>();
        employeeService.importExcel(employeeList);
        map.put("code",200);
        map.put("msg","success");
        return map;
    }*/