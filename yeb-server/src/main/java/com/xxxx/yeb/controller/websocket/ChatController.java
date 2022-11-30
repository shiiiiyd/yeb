package com.xxxx.yeb.controller.websocket;

import com.xxxx.yeb.pojo.Admin;
import com.xxxx.yeb.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 聊天控制器
 * @author NeXT
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Resource
    IAdminService adminService;

    @ApiOperation(value = "获得除当前管理员以外的所有管理员")
    @GetMapping("/admin")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdminsExceptCurrentAdmin();
    }
}
