package com.xxxx.yeb.mapper;

import com.xxxx.yeb.pojo.AdminRole;
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
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    /**
     * 用户拥有的角色数量
     * @param adminId
     * @return
     */
    Integer countAdminRoleByAdminId(Integer adminId);

    /**
     * 删除所有的用户数量
     * @param adminId
     * @return
     */
    int deleteAdminRoleByAdminId(Integer adminId);

    /**
     * 添加重新选择的角色
     * @param adminRoles
     * @return
     */
    Integer insertBatch(List<AdminRole> adminRoles);
}
