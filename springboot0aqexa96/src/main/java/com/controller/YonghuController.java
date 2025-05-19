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
import com.entity.TokenEntity;
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

import com.entity.YonghuEntity;
import com.entity.view.YonghuView;

import com.service.YonghuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 用户
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-12 16:12:11
 */
@RestController
@RequestMapping("/yonghu")
public class YonghuController {
    @Autowired
    private YonghuService yonghuService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", username));
        // 当用户不存在或md5方式验证密码不通过时
        if(u==null || !u.getMima().equals(EncryptUtil.md5(password))) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"yonghu",  "用户" );
        //返回token
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody YonghuEntity yonghu){
    	//ValidatorUtils.validateEntity(yonghu);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同用户账号，否则返回错误信息
        if(yonghuService.selectCount(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
		Long uId = new Date().getTime();
		yonghu.setId(uId);
        //设置登录密码md5方式加密
        yonghu.setMima(EncryptUtil.md5(yonghu.getMima()));
        //保存用户
        yonghuService.insert(yonghu);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        YonghuEntity u = yonghuService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456，并使用md5方式加密
        u.setMima(EncryptUtil.md5("123456"));
        yonghuService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YonghuEntity yonghu,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();


        //查询结果
		PageUtils page = yonghuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,YonghuEntity yonghu, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();

        //查询结果
		PageUtils page = yonghuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YonghuEntity yonghu){
       	EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yonghu, "yonghu")); 
        return R.ok().put("data", yonghuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YonghuEntity yonghu){
        EntityWrapper< YonghuEntity> ew = new EntityWrapper< YonghuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yonghu, "yonghu")); 
		YonghuView yonghuView =  yonghuService.selectView(ew);
		return R.ok("查询用户成功").put("data", yonghuView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YonghuEntity yonghu = yonghuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yonghu,deSens);
        return R.ok().put("data", yonghu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YonghuEntity yonghu = yonghuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yonghu,deSens);
        return R.ok().put("data", yonghu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增用户") 
    public R save(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(yonghuService.selectCount(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
        //ValidatorUtils.validateEntity(yonghu);
        //验证账号唯一性，否则返回错误信息
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	yonghu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		yonghu.setId(new Date().getTime());
        //密码使用md5方式加密
        yonghu.setMima(EncryptUtil.md5(yonghu.getMima()));
        yonghuService.insert(yonghu);
        return R.ok().put("data",yonghu.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增用户")
    @RequestMapping("/add")
    public R add(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(yonghuService.selectCount(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
        //ValidatorUtils.validateEntity(yonghu);
        //验证账号唯一性，否则返回错误信息
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	yonghu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		yonghu.setId(new Date().getTime());
        //密码使用md5方式加密
        yonghu.setMima(EncryptUtil.md5(yonghu.getMima()));
        yonghuService.insert(yonghu);
        return R.ok().put("data",yonghu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改用户")
    public R update(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yonghu);
        //验证字段唯一性，否则返回错误信息
        if(yonghuService.selectCount(new EntityWrapper<YonghuEntity>().ne("id", yonghu.getId()).eq("yonghuzhanghao", yonghu.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
	    YonghuEntity yonghuEntity = yonghuService.selectById(yonghu.getId());
        //如果密码不为空，则判断是否和输入密码一致，不一致则重新设置
        if(StringUtils.isNotBlank(yonghu.getMima()) && !yonghu.getMima().equals(yonghuEntity.getMima())) {
            //密码使用md5方式加密
            yonghu.setMima(EncryptUtil.md5(yonghu.getMima()));
        }
        //全部更新
        yonghuService.updateById(yonghu);
        if(null!=yonghu.getYonghuzhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(yonghu.getYonghuzhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", yonghu.getId()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除用户")
    public R delete(@RequestBody Long[] ids){
        yonghuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    











}
