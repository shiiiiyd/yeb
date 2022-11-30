package com.xxxx.yeb.mapper;

import com.xxxx.yeb.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id查询菜单
     *
     * @param id
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);

    /**
     * 查询菜单角色
     *
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 初始化菜单
     * @return
     */
    List<Menu> getInitializeMenus();

}
