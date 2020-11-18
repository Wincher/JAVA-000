package cn.wincher.autoConfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wincher
 * <p> cn.wincher.autoConfiguration <p>
 */
@ConfigurationProperties(prefix = "klass")
public class KlassProperties {

	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
