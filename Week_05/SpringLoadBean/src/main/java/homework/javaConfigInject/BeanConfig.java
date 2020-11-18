package homework.javaConfigInject;

import homework.javaConfigInject.JavaConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * @author wincher
 * <p> homework.xmlInject <p>
 */
@Configuration
public class BeanConfig {
	
	@Bean
	@Scope(SCOPE_PROTOTYPE)
	public JavaConfigBean javaConfigBean() {
		return new JavaConfigBean();
	}
}
