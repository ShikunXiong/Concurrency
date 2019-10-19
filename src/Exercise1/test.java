package Exercise1;

public class test {

    public static void main(String [] args){
        StringBuilder s = new StringBuilder("abc");
        StringBuilder a = new StringBuilder("d");
        s.append(a).append("e");
        System.out.println(s);
        System.out.println(a);
    }
}
