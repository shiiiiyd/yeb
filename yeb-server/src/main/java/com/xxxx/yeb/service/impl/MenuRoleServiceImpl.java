package com.xxxx.yeb.service.impl;

import com.xxxx.yeb.pojo.MenuRole;
import com.xxxx.yeb.mapper.MenuRoleMapper;
import com.xxxx.yeb.service.IMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Resource
    private MenuRoleMapper menuRoleMapper;

    /**
     * 显示授权的角色菜单
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> queryAuthorizationRoleMenu(Integer roleId) {
        return menuRoleMapper.queryAuthorizationRoleMenus(roleId);
    }
}
