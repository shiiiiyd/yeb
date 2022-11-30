package com.xxxx.yeb.service;

import com.xxxx.yeb.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.yeb.pojo.AdminUserInfo;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.pojo.Role;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录返回token
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);


    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户获取角色列表
     *
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);

    RespBean updateAdmin(AdminUserInfo adminUserInfo);

    /**
     * 初始化界面
     * @param keywords
     * @return
     */
    Map<String,Admin> getAdminAndOperating(String keywords);


    /**
     * 关闭用户
     * @param admin
     * @return
     */
    Integer enabledChangeClose(Map<String,Object> admin);

    /**
     * 开启用户
     * @param admin
     * @return
     */
    Integer enabledChangeOpen(Map<String, Object> admin);


    /**
     * 根据用户id删除用户
     * @param adminId
     * @return
     */
    Integer deleteAdminById(Integer adminId);

    /**
     *  获得除当前管理员以外的所有管理员
     * @return
     */
    List<Admin> getAllAdminsExceptCurrentAdmin();
}
