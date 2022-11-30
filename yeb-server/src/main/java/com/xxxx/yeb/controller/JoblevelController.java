package com.xxxx.yeb.controller;


import com.xxxx.yeb.mapper.JoblevelMapper;
import com.xxxx.yeb.pojo.Joblevel;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.service.IJoblevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/system")
@Api(tags = "JoblevelController")
public class JoblevelController {

    @Resource
    private IJoblevelService joblevelService;
    @Resource
    private JoblevelMapper joblevelMapper;

    @ApiOperation(value = "")
    @GetMapping("/basic/joblevel")
    public List<Joblevel> listJoblevel(){
        return joblevelService.listJoblevel();
    }


    @ApiOperation(value = "添加数据")
    @PostMapping("/basic/joblevel/")
    public RespBean addJoblevel(@RequestBody Joblevel joblevel){

        return joblevelService.addJoblevel(joblevel);
    }

    @ApiOperation(value = "更新数据")
    @PutMapping("/basic/joblevel")
    public RespBean updateJoblevel(@RequestBody Joblevel joblevel){

        return joblevelService.updateJoblevel(joblevel);
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping("/basic/joblevel/{id}")
    public RespBean delJoblevel(@PathVariable("id") Integer id){

        return joblevelService.delJoblevel(id);
    }

    @ApiOperation(value = "批量删除数据")
    @DeleteMapping("/basic/joblevel")
    public RespBean deleteJoblevels(Integer[] ids){

        return joblevelService.delJoblevels(ids);
    }
}
