package com.xxxx.yeb.service.impl;

import com.xxxx.yeb.mapper.EmployeeMapper;
import com.xxxx.yeb.pojo.Position;
import com.xxxx.yeb.mapper.PositionMapper;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {
    //注入数据层
      @Resource
    private PositionMapper positionMapper;
      @Resource
      private EmployeeMapper employeeMapper;

    /**
     * 职位数据层实现
     * @return
     */
      public List<Position> listPosition(){
          //创建HashMap
          HashMap<String, Object> positionMap = new HashMap<>();
          //把要查询的数据字段和值放入Map中
          positionMap.put("enabled", true);
          //调用BaseMapper的查询所有的方法，把要传入的参数放入。
          List<Position> positionList = positionMapper.selectByMap(positionMap);

          return  positionList;
      }

    /**
     * 调用数据层实现职位添加操作
     * @param pos
     * @return
     */
    @Override
    public boolean addPosition(Position pos) {
        //查询职位名字，和添加的职位名称是否重复
//        List<Position> positionList = this.selectName(pos);
//        //判断是否存在
//        if (positionList.size() > 0) {
//            return false;
//        }
//        Position p = positionMapper.findByName(pos.getName());
//        if(p!=null){
//            return false;
//        }
          pos.setEnabled(true);
//        Instant instant = new Date().toInstant();
//        ZoneId zoneId = ZoneId.systemDefault();
//        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
          pos.setCreateDate(LocalDateTime.now());
        return  positionMapper.insert(pos)>0;
    }

    /**
     * 查询是否存在已有职位
     * @param pos
     * @return
     */
    private List<Position> selectName(Position pos) {
        //创建HashMap
        HashMap<String, Object> posMap = new HashMap<>();
        //将要查询的数据存放到map中
        posMap.put("name", pos.getName());
        posMap.put("enabled", true);
        //查询
        List<Position> positionList = positionMapper.selectByMap(posMap);
        return positionList;
    }
    /**
     * 根据指定名称查询指定职位信息
     * @param name
     * @return
     */
    @Override
    public Position findByName(String name) {
        return positionMapper.findByName(name);
    }

    /**
     * 根据职位名称调用数据层查询是否已经存在的职位信息
     * @param name
     * @return
     */
    @Override
    public int getCountByname(String name) {
          int count= positionMapper.getCountByName(name);
        return count;
    }

    /**
     * 调用数据层进行职位信息的修改操作
     * @param p
     * @return
     */
    @Transactional
    @Override
    public boolean updatePosition(Position p) {

        return positionMapper.updateById(p)>0;
    }

    /**
     * 根据id调用数据层删除指定id信息
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteById(Integer id) {
        Position position = new Position();
        position.setId(id);
        //把表中Enabled字段设置为false--关闭 true--开启
        position.setEnabled(false);
        return positionMapper.updateById(position)>0;
    }

    /**
     * 批量删除职位信息
     * @param ids
     * @return
     */
    @Transactional
    @Override
    public RespBean deleteAllPosition(String ids) {
        //职位中有人数的个数
        int error = 0;
        //职位中没有人的个数
        int success = 0;
        //查看ID是否为空
        if (null == ids) {
            return RespBean.error("请选择要删除的职位");
        }

        String[] idStr = ids.split(",");

        //创建Position对象
        Position position = new Position();
        for (int i = 0; i < idStr.length; i++) {

            int id = Integer.parseInt(idStr[i]);

            //查询所有使用该职位的人数
            int count = employeeMapper.getCountByPosition(id);
            //判断总人数
            if (count == 0) {
                ++success;
                position.setId(id);
                //把表中Enabled字段设置为false--关闭 true--开启
                position.setEnabled(false);
                //调用更新操作
                int result = positionMapper.updateById(position);
                //判断影响数
                if (result < 1) {
                    return RespBean.error("删除失败！");
                }
            }
        }
        error = idStr.length - success;
        return RespBean.success("成功删除" + success + "条数据，失败" + error + "条数据");
    }


}
