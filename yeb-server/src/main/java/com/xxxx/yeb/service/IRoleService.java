package com.xxxx.yeb.service;

import com.xxxx.yeb.pojo.Menu;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
public interface IRoleService extends IService<Role> {

    /**
     * 加载角色列表
     * @return
     */
    List<Role> getRoleData();

    /**
     * 添加角色
     * @param role
     * @return
     */
    Integer addingRole(Role role);

    /**
     * 初始化角色
     * @return
     */
    List<Role> initializeRole();

    /**
     * 角色菜单加载
     * @return
     */
    List<Menu> initializeMenus();

    /**
     * 修改角色权限
     * @param roleId
     * @param menuIds
     * @return
     */
    RespBean doUpdatePermissions(Integer roleId, String[] menuIds);

    /**
     * 删除角色
     * @param rid
     * @return
     */
    Integer deleteRoleById(Integer rid);
}
