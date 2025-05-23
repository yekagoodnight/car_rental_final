package com.entity.view;

import com.entity.ShiguleixingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 事故类型
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-02-12 16:12:13
 */
@TableName("shiguleixing")
public class ShiguleixingView  extends ShiguleixingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ShiguleixingView(){
	}
 
 	public ShiguleixingView(ShiguleixingEntity shiguleixingEntity){
 	try {
			BeanUtils.copyProperties(this, shiguleixingEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
