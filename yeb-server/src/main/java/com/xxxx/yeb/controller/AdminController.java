package com.xxxx.yeb.controller;

import com.xxxx.yeb.pojo.AdminUserInfo;
import com.xxxx.yeb.pojo.PasswordInfo;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.service.impl.AdminServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminServiceImpl adminService;


    @ApiOperation(value = "修改信息")
    @PutMapping("/info")
    public RespBean updateAdmin(@RequestBody AdminUserInfo adminUserInfo){
        return adminService.updateAdmin(adminUserInfo);
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/pass")
    public RespBean updatePass(@RequestBody PasswordInfo passwordInfo){

        return adminService.updatePass(passwordInfo);
    }



    @PostMapping("/userface")
    @ResponseBody
    public Map<String,Object> upload(@RequestParam("id") Integer id,@RequestParam("file") MultipartFile multipartFile){

        String filePath = adminService.upload(multipartFile,id);

        Map<String,Object> map = new HashMap<>();
        map.put("code","200");
        map.put("msg", "文件上传成功");
        map.put("filePath", filePath);

        return map;
    }
}
