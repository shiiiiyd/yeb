package com.xxxx.yeb.service;

import com.xxxx.yeb.pojo.MenuRole;
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
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 显示授权的角色菜单
     * @param roleId
     * @return
     */
    List<Integer> queryAuthorizationRoleMenu(Integer roleId);
}
