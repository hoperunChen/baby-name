package cn.uctimes.luckyrui.babyname.dto;

import cn.uctimes.commons.base.BaseDto;
import cn.uctimes.commons.base.BaseEntity;
import cn.uctimes.commons.exception.BusinessException;
import lombok.Data;

/**
 * 选择名字页面数据
 *
 * @author chenrui
 * @date 2019-06-15 20:52
 */
@Data
public class ChoosePageDataDTO extends BaseDto {
	private static final long serialVersionUID = 3872745594231879268L;

	// TODO: 2019-06-15 修改DTO

	Integer id;

	String name;

	private String descr;

	@Override
	public BaseEntity convertTo() {
		throw new BusinessException("not supper");
	}

	@Override
	public ChoosePageDataDTO convertFrom(BaseEntity fromEntity) {
		throw new BusinessException("not supper");
	}

}