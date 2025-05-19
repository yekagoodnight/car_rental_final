package com.entity.view;

import com.entity.DingdanquxiaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 订单取消
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
@TableName("dingdanquxiao")
public class DingdanquxiaoView  extends DingdanquxiaoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DingdanquxiaoView(){
	}
 
 	public DingdanquxiaoView(DingdanquxiaoEntity dingdanquxiaoEntity){
 	try {
			BeanUtils.copyProperties(this, dingdanquxiaoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
