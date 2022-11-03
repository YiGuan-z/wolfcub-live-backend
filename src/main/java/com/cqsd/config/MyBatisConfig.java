package com.cqsd.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author caseycheng
 * @date 2022/11/3-20:24
 **/
@Configuration
public class MyBatisConfig {
	@Bean
	public Interceptor mybatisPlusInterceptor() {
		final var interceptor = new PaginationInnerInterceptor(DbType.MYSQL);
		interceptor.setOverflow(true);
		final var mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(interceptor);
		return mybatisPlusInterceptor;
	}
}
