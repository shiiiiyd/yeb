package com.xxxx.yeb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.yeb.mapper.*;
import com.xxxx.yeb.pojo.*;
import com.xxxx.yeb.rabbitMQ.Sender;
import com.xxxx.yeb.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.yeb.utils.EasyPoiExcelUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private NationMapper nationMapper;

    @Resource
    private PositionMapper positionMapper;

    @Resource
    private PoliticsStatusMapper politicsStatusMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private JoblevelMapper joblevelMapper;

    @Resource
    private Sender sender;

    @Resource
    private  EmployeeQueryMapper employeeQueryMapper;



    /**
     * 查询
     *
     * @param employeeQuery
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> getEmpPageAll(EmployeeQuery employeeQuery, Integer currentPage, Integer size) {
        //开启分页
        PageHelper.startPage(currentPage,size);
        //查询员工资料
        List<EmployeeQuery> employeeQueryList = employeeMapper.selectAll(employeeQuery);
        //把查询到的集合放到分页中
        PageInfo pageInfo = new PageInfo<>(employeeQueryList);
        //把数据返回
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    /**
     * 根据用户id删除用户
     *
     * @param id
     */
    @Override
    public void deleteEmployee(Integer id) {

        employeeMapper.deleteEmployee(id);
    }

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @Override
    public Map<String, Object> addEmployee(Employee employee) throws Exception {
        //参数校验
        Map<String, Object> map = new HashMap<>();
        int i = employeeMapper.insert(employee);
        if (i > 0) {
            map.put("code", 200);
            map.put("message", "添加成功");
            return map;
        }
        map.put("code", 500);
        map.put("message", "添加失败，请重试");
        return map;


    }

    /**
     * 更新用户
     *
     * @param employee
     * @return
     */
    @Override
    public Map<String, Object> updateEmployee(Employee employee) {
        Map<String, Object> map = new HashMap<>();
        int i = employeeMapper.updateById(employee);
        System.out.println(i);
        if (i > 0) {
            map.put("code", 200);
            map.put("message", "更新成功");
            return map;
        }
        map.put("code", 500);
        map.put("message", "更新失败，请重试");
        return map;
    }


    /**
     * 工号展示
     *
     * @return
     */
    @Override
    public RespBean getMaxWorkID() {
        RespBean respBean = new RespBean();
        int i = employeeMapper.selectMaxId();
        respBean.setObj(i);

        return respBean;
    }


    /**
     * 职位展示
     *
     * @return
     */
    @Override
    public List<Position> positions() {
        return positionMapper.selectList(null);
    }

    /**
     * 政治面貌展示啊
     *
     * @return
     */
    @Override
    public List<PoliticsStatus> politicsStatus() {
        return politicsStatusMapper.selectList(null);
    }

    /**
     * 民族展示
     *
     * @return
     */
    @Override
    public List<Nation> nations() {
        return nationMapper.selectList(null);
    }

    /**
     * 职称展示
     *
     * @return
     */
    @Override
    public List<Joblevel> Joblevel() {
        return joblevelMapper.selectList(null);
    }

    /**
     * 部门展示
     *
     * @return
     */
    @Override
    public List<Department> deps() {
        return departmentMapper.selectList(null);
    }

    /**
     * 导出数据
     * @param response
     * @param employee
     */
    @Override
    public void exportData(HttpServletResponse response,EmployeeQuery employee) {
        List<EmployeeQuery> queryList = employeeMapper.selectAll(employee);
        EasyPoiExcelUtil.exportExcel(queryList,"员工信息统计","员工信息",EmployeeQuery.class,"员工信息.xls",response);

    }

    /**
     * 导入数据
     * @param employeeList
     */
    @Override
    public void importExcel(List<EmployeeQuery> employeeList) {
        for(EmployeeQuery employeeQuery:employeeList){
            employeeMapper.insert(employeeQuery);
        }
    }
}

