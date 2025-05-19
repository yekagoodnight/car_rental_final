package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.annotation.SysLog;

import com.entity.BaoxianleixingEntity;
import com.entity.view.BaoxianleixingView;

import com.service.BaoxianleixingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 保险类型
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-12 16:12:13
 */
@RestController
@RequestMapping("/baoxianleixing")
public class BaoxianleixingController {
    @Autowired
    private BaoxianleixingService baoxianleixingService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BaoxianleixingEntity baoxianleixing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<BaoxianleixingEntity> ew = new EntityWrapper<BaoxianleixingEntity>();


        //查询结果
		PageUtils page = baoxianleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baoxianleixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,BaoxianleixingEntity baoxianleixing, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<BaoxianleixingEntity> ew = new EntityWrapper<BaoxianleixingEntity>();

        //查询结果
		PageUtils page = baoxianleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baoxianleixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BaoxianleixingEntity baoxianleixing){
       	EntityWrapper<BaoxianleixingEntity> ew = new EntityWrapper<BaoxianleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( baoxianleixing, "baoxianleixing")); 
        return R.ok().put("data", baoxianleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BaoxianleixingEntity baoxianleixing){
        EntityWrapper< BaoxianleixingEntity> ew = new EntityWrapper< BaoxianleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( baoxianleixing, "baoxianleixing")); 
		BaoxianleixingView baoxianleixingView =  baoxianleixingService.selectView(ew);
		return R.ok("查询保险类型成功").put("data", baoxianleixingView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BaoxianleixingEntity baoxianleixing = baoxianleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(baoxianleixing,deSens);
        return R.ok().put("data", baoxianleixing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BaoxianleixingEntity baoxianleixing = baoxianleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(baoxianleixing,deSens);
        return R.ok().put("data", baoxianleixing);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增保险类型") 
    public R save(@RequestBody BaoxianleixingEntity baoxianleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baoxianleixing);
        baoxianleixingService.insert(baoxianleixing);
        return R.ok().put("data",baoxianleixing.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增保险类型")
    @RequestMapping("/add")
    public R add(@RequestBody BaoxianleixingEntity baoxianleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baoxianleixing);
        baoxianleixingService.insert(baoxianleixing);
        return R.ok().put("data",baoxianleixing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改保险类型")
    public R update(@RequestBody BaoxianleixingEntity baoxianleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baoxianleixing);
        //全部更新
        baoxianleixingService.updateById(baoxianleixing);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除保险类型")
    public R delete(@RequestBody Long[] ids){
        baoxianleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    











}
