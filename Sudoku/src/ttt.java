
public class ttt {
    public static void main(String[] args) {
        Integer[] a = {1, 2, 3};
        Integer[] b = a.clone();
        a[0] ++;
        a = b.clone();
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
            System.out.println(b[i]);
        }


    }
}