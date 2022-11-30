package com.xxxx.yeb.controller;


import com.xxxx.yeb.mapper.EmployeeMapper;
import com.xxxx.yeb.mapper.PositionMapper;
import com.xxxx.yeb.pojo.Position;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/system")
public class PositionController {
    @Autowired
    private IPositionService positionService;
    @Resource
    private EmployeeMapper employeeMapper;
    /**
     * 职位列表显示
     * @return
     */
    @GetMapping("/basic/pos")
    public List<Position> listPosition(){
       return positionService.listPosition();
    }

    /**
     * 职位增加
     * @param position
     * @return
     */
    @PostMapping("/basic/pos")
    public RespBean addPosition(@RequestBody Position position){

        Position p = positionService.findByName(position.getName());

        if(p!=null){
            return RespBean.error("职位已存在");
        }
       Boolean result=positionService.addPosition(position);
//       if(result == false){
//           RespBean.error("添加的职位已存在");
//       }

           return RespBean.success("添加成功");

    }

    /**
     * 数据修改
     * @param p
     * @return
     */
    @PutMapping("/basic/pos")
   public RespBean updatePosition(@RequestBody Position p){
//        System.out.println(p);
        if(p.getName()==null ||  p.getName()==""){
            return RespBean.error("修改的职位不能为空");
        }
//       int count=positionService.getCountByname(p.getName());
//       if(count>0){
//           return RespBean.error("修改的职位已存在");
//       }
        Position position= positionService.findByName(p.getName());
        System.out.println(position);
        System.out.println("======="+p);
        if(position==null){
            if(positionService.updatePosition(p)){
                return RespBean.success("修改成功");
            }
        }
        if(p.getId()==position.getId()){
           return RespBean.success("修改成功");
        }else{
            return RespBean.error("修改的职位已存在");
        }



   }

    /**
     * 数据删除
     * @return
     */
    @DeleteMapping("/basic/pos/{id}")
    public RespBean deleteById(@PathVariable Integer id){
        if(employeeMapper.getCountByPosition(id)>0){
            return RespBean.error("职位下有雇员，不能删除");
        }
       if(positionService.deleteById(id)){
           return RespBean.success("删除成功");
       }

        return RespBean.error("删除失败");
    }
    @DeleteMapping("/basic/pos/")
    @ResponseBody
    public RespBean deleteManyPositions(String ids){
        RespBean respBean= positionService.deleteAllPosition(ids);
        return respBean;
    }
}
