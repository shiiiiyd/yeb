package com.xxxx.yeb.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xxxx.yeb.mapper.MenuMapper;
import com.xxxx.yeb.mapper.MenuRoleMapper;
import com.xxxx.yeb.pojo.Menu;
import com.xxxx.yeb.pojo.MenuRole;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.pojo.Role;
import com.xxxx.yeb.mapper.RoleMapper;
import com.xxxx.yeb.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private MenuRoleMapper menuRoleMapper;

    /**
     * 角色加载
     * @return
     */
    @Override
    public List<Role> getRoleData() {
        List<Role> roles = roleMapper.getRolesData();
        return roles;
    }

    /**
     * 角色添加
     * @param role
     * @return
     */
    @Override
    public Integer addingRole(Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        return roleMapper.insert(role);
    }

    /**
     * 初始化角色
     * @return
     */
    @Override
    public List<Role> initializeRole() {
        return roleMapper.getRolesData();
    }

    /**
     * 加载角色菜单
     * @return
     */
    @Override
    public List<Menu> initializeMenus() {
        return menuMapper.getInitializeMenus();
    }

    /**
     * 修改角色全新啊
     *      roleId:角色Id
     *      menuIds：菜单权限Id
     *      
     * @param roleId
     * @param menuIds
     * @return
     */
    @Override
    public RespBean doUpdatePermissions(Integer roleId, String[] menuIds) {
        menuRoleMapper.deleteRoleMenuByRoleId(roleId);
        if(menuIds != null && menuIds.length>0){
            List<MenuRole> menuRoles = new ArrayList<>();
            for (String mid:menuIds){
                MenuRole menuRole = new MenuRole();
                menuRole.setMid(Integer.parseInt(mid));
                menuRole.setRid(roleId);
                menuRoles.add(menuRole);
            }
            menuRoleMapper.insertBatch(menuRoles);
            return RespBean.success("授权失败");
        }
        return RespBean.error("授权失败");
    }

    /**
     * 删除角色
     * @param rid
     * @return
     */
    @Override
    public Integer deleteRoleById(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }


}
