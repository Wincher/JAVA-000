package cn.wincher.bean;

/**
 * @author wincher
 * <p> cn.wincher <p>
 */
public class Klass {
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Klass{" +
			"name='" + name + '\'' +
			'}';
	}
}
