package com.xxxx.yeb.service;

import com.xxxx.yeb.pojo.Position;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.yeb.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
public interface IPositionService extends IService<Position> {
    /**
     * 职位列表业务层实现
     * @return
     */
    public List<Position> listPosition();

    /**
     * 职位添加业务方法
     * @return
     */
    public boolean addPosition(Position pos);

    /**
     * 查询职位是否存在
     * @param name
     * @return
     */
    public Position findByName(String name);

    /**
     * 调用数据层getCountBnme方法查询是否有存在的职位
     * @param name
     * @return
     */
    public int getCountByname(String name);

    /**
     * 调用数据层进行更新操作
     * @param p
     * @return
     */
    public boolean updatePosition(Position p);

    /**
     * 根据指定id删除指定数据
     * @param id
     * @return
     */
     public boolean deleteById(Integer id);

    /**
     * 批量删除职位信息
     * @param ids
     * @return
     */
     public RespBean deleteAllPosition(String ids);
}
