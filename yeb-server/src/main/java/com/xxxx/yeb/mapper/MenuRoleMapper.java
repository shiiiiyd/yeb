package com.xxxx.yeb.mapper;

import com.xxxx.yeb.pojo.Menu;
import com.xxxx.yeb.pojo.MenuRole;
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
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 修改角色菜单
     *      返回受影响的行数
     * @param menus
     * @return
     */
    Integer insertBatch(List<MenuRole> menus);

    /**
     * 通过角色id删除角色id
     * @param roleId
     * @return
     */
    Integer deleteRoleMenuByRoleId(Integer roleId);

    /**
     * 查询角色所有的菜单id
     * @param roleId
     * @return
     */
    List<Integer> queryAuthorizationRoleMenus(Integer roleId);

}
