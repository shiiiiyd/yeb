package com.xxxx.yeb.utils;

import com.xxxx.yeb.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 得到当前的管理
 *      定义为发送人员
 * @author NeXT
 */
public class AdminUtils {
    public static Admin getCurrentAdmin() {
        return ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
