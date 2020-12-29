public class TestClassLoader {
    public static void main(String[] args) {
        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        System.out.println(classLoader);
        if(classLoader!=null){
            System.out.println(classLoader.getParent());
            System.out.println(classLoader.getParent().getParent());
        }

    }
}
