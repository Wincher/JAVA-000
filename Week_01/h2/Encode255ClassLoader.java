import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wincher
 * <p> PACKAGE_NAME <p>
 */
public class Encode255ClassLoader extends ClassLoader {
	
	private static final String CLASS_PATH = "./Week_01/resource/Hello.xlass";
	private static final int SALT = 255;
	
	public static void main(String[] args) throws IOException {
		try {
			Class<?> clz = new Encode255ClassLoader().findClass("Hello");
			Object o = clz.newInstance();
			Method m = clz.getMethod("hello");
			m.invoke(o);
		} catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	private void decode(byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) (SALT - bytes[i]);
		}
	}
	
	@Override
	protected Class<?> findClass(String name) {
		File rawClazz = new File(CLASS_PATH);
		int length = (int) rawClazz.length();
		byte[] classBytes = new byte[length];
		
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(rawClazz));
		     ByteArrayOutputStream bos = new ByteArrayOutputStream(length);
		) {
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int len;
			while (-1 != (len = bis.read(buffer, 0, bufferSize))) {
				decode(buffer);
				bos.write(buffer, 0, len);
			}
			classBytes = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return defineClass(name, classBytes, 0, classBytes.length);
	}
}
