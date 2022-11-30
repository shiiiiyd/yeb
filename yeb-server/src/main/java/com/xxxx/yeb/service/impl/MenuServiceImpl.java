package com.xxxx.yeb.service.impl;

import com.xxxx.yeb.mapper.MenuRoleMapper;
import com.xxxx.yeb.pojo.Admin;
import com.xxxx.yeb.pojo.Menu;
import com.xxxx.yeb.mapper.MenuMapper;
import com.xxxx.yeb.pojo.MenuRole;
import com.xxxx.yeb.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private MenuRoleMapper menuRoleMapper;

    /**
     * 根据用户id查询菜单
     *
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //查询缓存中是否有数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
        if (CollectionUtils.isEmpty(menus)) {
            //如果没数据，数据库中查询，并设置到缓存中
            menus = menuMapper.getMenusByAdminId(adminId);
            valueOperations.set("menu_" + adminId, menus);
        }
        return menus;
    }

    /**
     * 查询菜单角色
     *
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

}
