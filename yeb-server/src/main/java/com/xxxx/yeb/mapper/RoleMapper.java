package com.xxxx.yeb.mapper;

import com.xxxx.yeb.pojo.AdminRole;
import com.xxxx.yeb.pojo.Role;
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
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户获取角色列表
     *
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);

    /**
     * 获取所有的角色
     * @return
     */
    List<Role> getRolesData();

    /**
     * 删除角色
     * @param rid
     * @return
     */
    Integer deleteByPrimaryKey(Integer rid);
}
