package com.xxxx.yeb.service;

import com.xxxx.yeb.pojo.Joblevel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.yeb.pojo.RespBean;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
public interface IJoblevelService extends IService<Joblevel> {

    public List<Joblevel> listJoblevel();

    RespBean delJoblevel(Integer id);

    RespBean addJoblevel(Joblevel joblevel);

    RespBean updateJoblevel(Joblevel joblevel);

    RespBean delJoblevels(Integer[] ids);
}
