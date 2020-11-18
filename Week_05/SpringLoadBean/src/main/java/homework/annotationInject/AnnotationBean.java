package homework.annotationInject;

import org.springframework.stereotype.Component;

/**
 * @author wincher
 * <p> homework.vo <p>
 */
@Component
public class AnnotationBean {
	private String name;
	
	public AnnotationBean() {
		this.name = "Annotation";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
