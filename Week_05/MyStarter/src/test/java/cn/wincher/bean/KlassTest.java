package cn.wincher.bean;

import cn.wincher.autoConfiguration.KlassAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wincher
 * <p> cn.wincher.bean <p>
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = KlassAutoConfiguration.class)
class KlassTest {
	
	@Autowired
	Klass klass;
	
	@Test
	public void test() {
		System.out.println(klass);
	}

}