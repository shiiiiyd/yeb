package com.xxxx.yeb.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xxxx.yeb.pojo.AdminRole;
import com.xxxx.yeb.mapper.AdminRoleMapper;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.service.IAdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {

    @Resource
    private AdminRoleMapper adminRoleMapper;
    /**
     * 用户角色关系
     * @param adminId
     * @param roleIds
     */
    @Override
    public void relationAdminRoles(Integer adminId, String[] roleIds) {
        //删除所有的用户角色
        adminRoleMapper.deleteAdminRoleByAdminId(adminId);
        /**
         * 重新添加角色
         */
        List<AdminRole> adminRoles = new ArrayList<>();
        if(roleIds==null){
            return;
        }else {
            for (String rid:roleIds){
                AdminRole adminRole = new AdminRole();
                adminRole.setAdminId(adminId);
                adminRole.setRid(Integer.parseInt(rid));
                adminRoles.add(adminRole);
            }
            adminRoleMapper.insertBatch(adminRoles);
        }
    }
}
