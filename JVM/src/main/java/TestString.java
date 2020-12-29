public class TestString {

    public static void main(String[] args) {


        String s1 = "123";
        //s2创建了四个对象，因为是final的
        String s2 = "1"+"2"+"3";
        String s3 = new String("123");
        String T = "1";
        String s4 = T+new String("23");


        System.out.println(s1==s2);
        System.out.println(s2==s3);
        System.out.println(s1==s3);
        System.out.println(s3==s4);
    }
}
