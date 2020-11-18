package cn.wincher.autoConfiguration;

import cn.wincher.bean.Klass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author wincher
 * <p> cn.wincher.autoConfiguration <p>
 */
@Configuration
@ConditionalOnClass(Klass.class)
@EnableConfigurationProperties(KlassProperties.class)
@ConditionalOnProperty(prefix = "klass", value = "enabled", havingValue = "true")
@PropertySource("classpath:application.yml")
public class KlassAutoConfiguration {
	
	@Autowired
	KlassProperties klassProperties;
	
	@Bean
	public Klass klass() {
		Klass klass = new Klass();
		klass.setName(klassProperties.getName());
		return klass;
	}
}
