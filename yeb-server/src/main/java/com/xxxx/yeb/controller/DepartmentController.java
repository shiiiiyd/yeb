package com.xxxx.yeb.controller;


import com.xxxx.yeb.mapper.DepartmentMapper;
import com.xxxx.yeb.mapper.EmployeeMapper;
import com.xxxx.yeb.modle.DepartmentModle;
import com.xxxx.yeb.pojo.Department;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xxxx.yeb.modle.DepartmentModle;

import com.xxxx.yeb.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/system/basic/")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;


    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private EmployeeMapper employeeMapper;
    /**
     * 列表显示
     * @return
     */
    @GetMapping("/department/")
    @ResponseBody
    public List<DepartmentModle> listDeptparment(){
//        List<Department> list = departmentService.listDepartment();
//        System.out.println(list);

        List<DepartmentModle> list= departmentService.getAllListDepartmentModle();
        System.out.println(list);
        //将list数据转为树形控件返回
        List<DepartmentModle> resultList= new ArrayList<>();
        //定义一个map查询
        Map<Integer,DepartmentModle> map = new HashMap<>();
        for(int i=0;i<list.size();i++){
            map.put(list.get(i).getId(),list.get(i));
          //  System.out.println(list.get(i).getParentId()+"----"+list.get(i));
        }
        for(int i=0;i<list.size();i++){
            //判断是否是上级
            if(list.get(i).getParent()){
                // 查询它的所有孩子
                List<DepartmentModle> listModle = new ArrayList<>();
                Iterator<Map.Entry<Integer, DepartmentModle>> entries = map.entrySet().iterator();
                while(entries.hasNext()){
                    Map.Entry<Integer,DepartmentModle> entry = entries.next();
                    //找到与当前id匹配的所有
                    if(list.get(i).getId()==entry.getValue().getParentId()) {
                        listModle.add(entry.getValue());
                    }
                }
                list.get(i).setChildren(listModle);
                resultList.add(list.get(i));
            }
        }
       System.out.println(Arrays.toString(resultList.toArray()));
//        System.out.println(resultList.size());
    List<DepartmentModle> finalList= new ArrayList<>();
//        for(int x=0;x<resultList.size();x++){
//            if(resultList.get(x).getChildren().size()==0){
//
//            }
//        }
        System.out.println(resultList.get(0));
        finalList.add(resultList.get(0));
        return finalList;
    }

    /**
     * 部门数据添加
     * @param department
     * @return
     */
     @PostMapping("/department/")
    public RespBean addDeptparment(@RequestBody Department  department){
        if(department.getName()==null || department.getName()==""){
            return RespBean.error("添加的部门为空",this.listDeptparment());
        }
        if(departmentService.findByName(department.getName())!=null){
            return RespBean.error("添加的部门已存在",this.listDeptparment());
        }
        // System.out.println(department);
          departmentService.addDepartment(department);
        return RespBean.success("添加成功",department);
        // return this.listDeptparment();
    }
    @DeleteMapping("/department/{id}")
    public RespBean deleteById(@PathVariable Integer id){
        System.out.println("部门"+id);
        Department department = departmentMapper.selectById(id);
        if(department.getIsParent()){
         return    RespBean.error("有子部门不能删除");
        }
        if(employeeMapper.getCountByDeptment(id)>0){
           return   RespBean.error("部门下有雇员不能删除");
        }
       departmentService.deleteDepartment(id);
         return RespBean.success("删除成功");

    }

}
