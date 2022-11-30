package com.xxxx.yeb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xxxx.yeb.pojo.*;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
public interface IEmployeeService extends IService<Employee> {


    Map<String, Object> getEmpPageAll(EmployeeQuery employeeQuery, Integer currentPage, Integer size);

    void deleteEmployee(Integer id);

    //Map<String,Object> selectEmployee(String userName);

    RespBean getMaxWorkID();

    List<Position> positions();

    List<PoliticsStatus> politicsStatus();

    List<Nation> nations();

    List<Department> deps();

    List<Joblevel> Joblevel();

    Map<String, Object> addEmployee(Employee employee) throws Exception;

    Map<String, Object> updateEmployee(Employee employee);

    void exportData(HttpServletResponse response,EmployeeQuery employee);

    void importExcel(List<EmployeeQuery> employeeList);

}
