package homework;

import homework.annotationInject.AnnotationBean;
import homework.javaConfigInject.JavaConfigBean;
import homework.xmlInject.XMLBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wincher
 * <p> PACKAGE_NAME <p>
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		applicationContext.getBeansOfType(XMLBean.class);
		applicationContext.getBeansOfType(AnnotationBean.class);
		System.out.println(((XMLBean)applicationContext.getBean("xmlBean")).getName());
		System.out.println(((AnnotationBean)applicationContext.getBean("annotationBean")).getName());
		System.out.println(((JavaConfigBean)applicationContext.getBean("javaConfigBean")).getName());
	}
	
}
