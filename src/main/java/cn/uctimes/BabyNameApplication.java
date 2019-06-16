package cn.uctimes;

import cn.uctimes.commons.base.BaseApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @author chenrui
 * @date 2019-06-14 11:10
 */
@EnableAutoConfiguration
@MapperScan({"cn.uctimes.luckyrui.babyname.dao"})
public class BabyNameApplication extends BaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BabyNameApplication.class, args);
	}


}
