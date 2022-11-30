package com.xxxx.yeb.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.yeb.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.yeb.pojo.AdminUserInfo;
import com.xxxx.yeb.pojo.PasswordInfo;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 初始化界面 查询
     * @param keywords
     * @return
     */
    List<Admin> operatingTheAdmin(String keywords);

    /**
     * 关闭用户
     * @param adminId
     * @return
     */
    Integer enabledChangeCloseById(Integer adminId);

    int updateByInfo(AdminUserInfo adminUserInfo);

    String selectPasswordById(Integer id);

    Integer updatePassword(PasswordInfo passwordInfo);



    /**
     *  开启用户
     * @param adminId
     * @return
     */
    Integer enabledChangeOpenById(Integer adminId);

    /**
     * 根据用户id删除用户
     * @param adminId
     * @return
     */
    Integer deleteAdminById(Integer adminId);

    /**
     * 获得除当前管理员以外的所有管理员
     * @param id
     * @return
     */
    List<Admin> getAllAdminsExceptCurrentAdmin(Integer id);

    /**
     * 更新头像
     * @param id
     * @param url
     * @return
     */
    int updateUserFace(Integer id,String url);
}
