package com.xxxx.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xxxx.yeb.pojo.Joblevel;
import com.xxxx.yeb.mapper.JoblevelMapper;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.service.IJoblevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cv工程师
 * @since 2020-07-17
 */
@Service
public class JoblevelServiceImpl extends ServiceImpl<JoblevelMapper, Joblevel> implements IJoblevelService {

    @Resource
    private JoblevelMapper joblevelMapper;

    public List<Joblevel> listJoblevel(){
        return joblevelMapper.selectList(new QueryWrapper<>());
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean delJoblevel(Integer id) {
        if(id==null){
            return RespBean.error("待删除记录不存在");
        }
        //判断是否存在子表
        if(joblevelMapper.selectEmployeeById(id) > 1){
            joblevelMapper.updateEmployeeById(id);
        }
        if(joblevelMapper.deleteById(id) < 1){
            return RespBean.error("删除失败");
        }
        return RespBean.success("删除成功");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean delJoblevels(Integer[] ids){
        if(ids==null){
            return RespBean.error("待删除记录不存在");
        }
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            idList.add(ids[i]);
            //判断是否存在子表
            if(joblevelMapper.selectEmployeeById(ids[i]) > 1){
                joblevelMapper.updateEmployeeById(ids[i]);
            }
            if(joblevelMapper.deleteById(ids[i]) < 1){
                return RespBean.error("删除失败");
            }
        }

        return RespBean.success("删除成功");
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean addJoblevel(Joblevel joblevel) {
        //参数校验
        if (joblevel.getName() == null){
            return RespBean.error("职称名称不能为空");
        }
        if (joblevel.getTitleLevel() == null){
            return RespBean.error("职称等级不能为空");
        }
        joblevel.setEnabled(true);
        //判断是否存在
        boolean flagTitle = joblevelMapper.selectTitleByName(joblevel.getName()).contains(joblevel.getTitleLevel());
        if(flagTitle){
            return RespBean.error("存在相同职称，添加失败");
        }
        if (joblevelMapper.insert(joblevel) < 1){
            return RespBean.error("添加失败");
        }
        return RespBean.success("添加成功");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean updateJoblevel(Joblevel joblevel){
        Integer id = joblevel.getId();
        //判断id是否为空
        if(id == null){
            return RespBean.error("更新失败");
        }
        //通过id查找对象
        Joblevel temp =joblevelMapper.selectById(id);
        if(temp == null){
            return RespBean.error("待更新记录不存在");
        }
        //参数校验
        if (StringUtils.isBlank(joblevel.getName())){
            return RespBean.error("职称名称不能为空");
        }
        if (StringUtils.isBlank(joblevel.getTitleLevel())){
            return RespBean.error("职称等级不能为空");
        }
        //判断是否存在
        boolean flagTitle = joblevelMapper.selectTitleByName(joblevel.getName()).contains(joblevel.getTitleLevel());
        if(flagTitle){
            if(joblevelMapper.selectNameById(id).equals(joblevel.getName()) &&
                    joblevelMapper.selectTitleById(id).equals(joblevel.getTitleLevel())){
                joblevelMapper.updateById(joblevel);
                return RespBean.success("更新成功");
            }
            return RespBean.error("存在相同职称，更新失败");
        }
        //设置默认值
        joblevel.setCreateDate(temp.getCreateDate());

        if (joblevelMapper.updateById(joblevel) < 1){
            return RespBean.error("更新失败");
        }
        return RespBean.success("更新成功");
    }
}
