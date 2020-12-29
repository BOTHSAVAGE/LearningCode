import java.io.IOException;
import java.io.InputStream;

public class NewClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream stream = getClass().getResourceAsStream(fileName);
                    if (stream == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[stream.available()];
                    stream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            }
        };

        //自己的class
        String className = "TestClassLoader";

        Object o1 = NewClassLoader.class.getClassLoader().loadClass(className).newInstance();
        Object o2 = classLoader.loadClass(className).newInstance();

        System.out.println(o1==o2);
        System.out.println(o1.equals(o2));


        System.out.println(o1 instanceof TestClassLoader);
        System.out.println(o2 instanceof TestClassLoader);


    }


}
