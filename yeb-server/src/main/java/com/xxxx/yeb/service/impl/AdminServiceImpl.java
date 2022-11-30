package com.xxxx.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.xxxx.yeb.config.UploadProperties;
import com.xxxx.yeb.config.security.JwtTokenUtil;
import com.xxxx.yeb.mapper.AdminMapper;
import com.xxxx.yeb.mapper.RoleMapper;
import com.xxxx.yeb.pojo.*;
import com.xxxx.yeb.pojo.Admin;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.pojo.Role;
import com.xxxx.yeb.service.IAdminRoleService;
import com.xxxx.yeb.pojo.Admin;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.pojo.Role;
import com.xxxx.yeb.pojo.*;
import com.xxxx.yeb.service.IAdminRoleService;
import com.xxxx.yeb.service.IAdminService;
import com.xxxx.yeb.service.UploadService;
import com.xxxx.yeb.utils.AdminUtils;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@Service
@EnableConfigurationProperties(UploadProperties.class)
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private AdminMapper adminMapper;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private IAdminRoleService adminRoleService;

    /**
     * 登录成功返回token
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isBlank(code) || !captcha.equals(code)) {
            return RespBean.error("验证码填写错误！");
        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //判断用户名或密码是否正确
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        //判断用户是否被禁用
        if (!userDetails.isEnabled()) {
            return RespBean.error("用户被禁用，请联系管理员！");
        }
        //更新security上下文的用户对象
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //生成token并返回
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username));
    }


    /**
     * 根据用户获取角色列表
     *
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    /**
     * 初始化界面 查询
     * @param keywords
     * @return
     */
    @Override
    public Map<String,Admin> getAdminAndOperating(String keywords) {
        Map<String,Admin> adminMap = new HashMap<>();
        //获取查询的用户
        List<Admin> listAdmin = adminMapper.operatingTheAdmin(keywords);
        for (Admin admins: listAdmin){
            admins.setPassword(null);
            admins.setRoles(getRoles(admins.getId()));
            adminMap.put(admins.getName(),admins);
        }
        return adminMap;
    }


    /**
     *  启用状态
     *      开启用户
     * @param admin
     * @return
     */
    @Override
    public Integer enabledChangeOpen(Map<String, Object> admin) {
        return adminMapper.enabledChangeOpenById((Integer) admin.get("id"));
    }

    /**
     * 删除用户
     * @param adminId
     * @return
     */
    @Override
    public Integer deleteAdminById(Integer adminId) {
        return adminMapper.deleteAdminById(adminId);
    }

    /**
     * 修改用户信息
     * @param adminUserInfo
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean updateAdmin(AdminUserInfo adminUserInfo) {
        //参数校验
        if(StringUtils.isBlank(adminUserInfo.getName())){
            return RespBean.error("用户名不能为空");
        }
        if(StringUtils.isBlank(adminUserInfo.getTelephone())){
            return RespBean.error("电话号码不能为空");
        }
        if(StringUtils.isBlank(adminUserInfo.getPhone())){
            return RespBean.error("手机号码不能为空");
        }
        if(StringUtils.isBlank(adminUserInfo.getAddress())){
            return RespBean.error("用户地址不能为空");
        }
        //执行修改操作
        if(adminMapper.updateByInfo(adminUserInfo) < 1){
            return RespBean.error("修改失败");
        }
        return RespBean.success("修改成功");
    }

    /**
     * 修改密码
     * @param passwordInfo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean updatePass(PasswordInfo passwordInfo) {
        //参数校验
        if(StringUtils.isBlank(passwordInfo.getOldPass())){
            return RespBean.error("旧密码不能为空");
        }
        if(StringUtils.isBlank(passwordInfo.getPass())){
            return RespBean.error("新密码不能为空");
        }
        if(StringUtils.isBlank(passwordInfo.getCheckPass())){
            return RespBean.error("确认密码不能为空");
        }
        //核对旧密码是否正确
        String oldPassword = passwordInfo.getOldPass();
        if(!(passwordEncoder.matches(oldPassword,adminMapper.selectPasswordById(passwordInfo.getAdminId())))){
            return RespBean.error("旧密码不一致");
        }
        //新密码与旧密码不能相同
        String newPassword = passwordInfo.getPass();
        if(newPassword.equals(oldPassword)){
            return RespBean.error("新密码与旧密码不能相同");
        }
        //确认密码不一致
        String checkPassword = passwordInfo.getCheckPass();
        if(!(checkPassword.equals(newPassword))){
            return RespBean.error("确认密码不一致");
        }
        checkPassword = passwordEncoder.encode(checkPassword);
        passwordInfo.setCheckPass(checkPassword);
        if(adminMapper.updatePassword(passwordInfo) < 1){
            return RespBean.error("修改失败");
        }
        return RespBean.success("修改成功");
    }

    /**
     *  启用状态
     *      关闭用户
     * @param admin
     * @return
     */
    @Override
    public Integer enabledChangeClose(Map<String,Object> admin) {
        return adminMapper.enabledChangeCloseById((Integer) admin.get("id"));
    }

    /**
     * 获得除当前管理员以外的所有管理员
     * @return
     */
    @Override
    public List<Admin> getAllAdminsExceptCurrentAdmin() {
        //获得除当前管理员以外的所有管理员
        List<Admin> listAdmin = adminMapper.getAllAdminsExceptCurrentAdmin(AdminUtils.getCurrentAdmin().getId());;
        for (Admin admins: listAdmin){
            admins.setPassword(null);
            admins.setRoles(getRoles(admins.getId()));
        }
        return listAdmin;
    }


    /**
     * 上传头像
     *
     *
     */

    private Log log= LogFactory.getLog(UploadService.class);

    @Resource
    private FastFileStorageClient storageClient;

    @Resource
    private UploadProperties uploadProperties;

    /**
     * 功能描述:     文件上传
     * @param multipartFile  要上传的文件
     * @return {@link String}    返回文件路径
     * @author hxxiapgy
     * @data 2020/7/9 22:05
     */
    public String upload(MultipartFile multipartFile, Integer id){
        // 1.校验上传文件是否为空
        if (multipartFile == null){
            log.error("文件不存在");
            throw new RuntimeException("文件为空！");
        }

        // 1.校验文件类型
        //获取上传文件的类型
        String contentType = multipartFile.getContentType();
        if (!uploadProperties.getAllowTypes().contains(contentType)){
            log.info("文件类型不支持！");
            throw new RuntimeException("文件类型不支持！");
        }

        try {
            // 2.校验文件内容
            // 读取文件
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            if (bufferedImage == null || bufferedImage.getWidth() == 0 || bufferedImage.getHeight() == 0){
                log.error("上传文件有问题");
                throw new RuntimeException("上传文件有问题！");
            }
        } catch (IOException e) {
            log.error("检验文件内容失败...{}",e);
            throw new RuntimeException("检验文件内容失败...{}" + e.getMessage());
        }

        // 3.获取扩展名
        String extension = org.apache.commons.lang3.StringUtils.substringAfterLast(multipartFile.getOriginalFilename(),".");

        try {
            // 4.上传文件
            StorePath storePath  = storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), extension, null);
            //更新图片地址
            String url = uploadProperties.getBaseUrl() + storePath.getFullPath();
            System.out.println(url);

            adminMapper.updateUserFace(id,url);
            // 5.返回路径
            return url;
        } catch (Exception e) {
            log.error("文件上传失败！...{}",e);
            throw new RuntimeException("文件上传失败...{}" + e.getMessage());
        }

    }
}
