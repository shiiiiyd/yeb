package com.xxxx.yeb.controller;


import com.xxxx.yeb.mapper.RoleMapper;
import com.xxxx.yeb.pojo.Menu;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.pojo.Role;
import com.xxxx.yeb.service.IMenuRoleService;
import com.xxxx.yeb.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class RoleController {
    @Resource
    private IRoleService roleService;
    @Resource
    private IMenuRoleService menuRoleService;
    @Resource
    private RoleMapper roleMapper;

    @ApiOperation(value = "添加角色")
    @PostMapping("/role")
    public RespBean addingRoles(@RequestBody Role role){
        List<Role> roles = roleMapper.getRolesData();
        for (Role role1:roles){
            if(("ROLE_" + role.getName()).equals(role1.getName())){
                return RespBean.error("角色英文名称已存在!");
            }
            if (role.getNameZh().equals(role1.getNameZh())){
                return RespBean.error("角色中文名称已存在!");
            }
        }
        if(roleService.addingRole(role)==1){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "角色加载")
    @GetMapping("/")
    public List<Role> initializeRoleList(){
        return roleService.initializeRole();
    }

    @ApiOperation(value = "角色菜单加载")
    @GetMapping("/menus")
    public List<Menu> initializeMenus(){
        return roleService.initializeMenus();
    }

    @ApiOperation(value = "根据角色id加载菜单,回显")
    @GetMapping("/mid/{rid}")
    public List<Integer> initializeRoleMenus(@PathVariable Integer rid){
        return menuRoleService.queryAuthorizationRoleMenu(rid);
    }

    @ApiOperation(value = "角色授权")
    @PutMapping("/")
    public RespBean doUpdatePermissions(HttpServletRequest request){
        Integer roleId= Integer.valueOf(request.getParameter("rid"));
        String[] menuIds = request.getParameterValues("mids");
        //roleId:角色Id，menuIds：菜单id
        roleService.doUpdatePermissions(roleId,menuIds);
        return RespBean.success("授权成功！");
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleById(@PathVariable Integer rid) {
        if (roleService.deleteRoleById(rid) == 1) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

}
