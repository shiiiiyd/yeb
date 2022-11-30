package com.xxxx.yeb.controller;



import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.service.IAdminService;
import com.xxxx.yeb.service.IAdminRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.xxxx.yeb.pojo.Admin;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.service.IAdminService;
import com.xxxx.yeb.service.IAdminRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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
@RequestMapping("/system")
public class AdminRoleController {

    @Resource
    private IAdminRoleService adminRoleService;
    @Resource
    private IAdminService adminService;

    @ApiOperation(value = "角色记录管理")
    @RequestMapping("/admin/role")
    public RespBean relationAdminRoles(HttpServletRequest request){
        //获取用户id
        Integer adminId = Integer.valueOf(request.getParameter("adminId"));
        //获取角色id
        String[] roleIds = request.getParameterValues("rids");
        adminRoleService.relationAdminRoles(adminId,roleIds);
        return RespBean.success("操作成功！");
    }
}
