package cn.uctimes.luckyrui.babyname.entity;

import cn.uctimes.commons.base.BaseEntity;
import lombok.Data;

/**
 * @author chenrui
 */
@Data
public class User extends BaseEntity {

	private static final long serialVersionUID = 5756836700941408695L;

	private Integer id;

	private String name;

	private String phone;

	private Integer schedule;


}