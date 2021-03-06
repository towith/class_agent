import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * -javaagent:plugin-agent.jar
 */
public class HandleAgent {
	static String outputDir = "D:\\x_tmp\\wordl\\debug2\\";
	private static String contained = "XXXX";

	public static void premain(String agentArgs, Instrumentation ins) {
		System.out.println("--------------javaagent-----------------");
		ins.addTransformer(new ClassFileTransformer() {
			@Override
			public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
									ProtectionDomain protectionDomain, byte[] classfileBuffer)
					throws IllegalClassFormatException {
				if (className.contains(contained)) {
					File file = new File(outputDir);
					File file1 = new File(file, className.substring(0, className.lastIndexOf("/")));
					if (!file1.exists()) {
						file1.mkdirs();
					}
					File file2 = new File(file1,
							(className.lastIndexOf("/") >= 0 ?
									className.substring(className.lastIndexOf("/")).substring(1) :
									className) + ".class");
					FileOutputStream fileOutputStream = null;
					try {
						fileOutputStream = new FileOutputStream(file2);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					try {
						fileOutputStream.write(classfileBuffer);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return classfileBuffer;
			}
		});
	}
}
