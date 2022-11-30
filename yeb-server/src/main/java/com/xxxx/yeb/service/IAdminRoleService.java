package com.xxxx.yeb.service;

import com.xxxx.yeb.pojo.AdminRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.yeb.pojo.RespBean;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
public interface IAdminRoleService extends IService<AdminRole> {

    /**
     * 用户角色关系
     * @param adminId
     * @param roleIds
     * @return
     */
    void relationAdminRoles(Integer adminId, String[] roleIds);
}
