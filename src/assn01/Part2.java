package assn01;

public class Part2 {
    public Part2() {
    }

    public static void main(String[] args) {
        short sh = 2;
        method2();
    }

    public static void method2() {
        int n2 = 212;
        System.out.println(n2);
        method3();
    }

    public static void method3() {
        char[] a3 = new char[]{'a', 'z'};
        int[] b3 = new int[]{97, 122};
        System.out.println(a3[0] + " " + a3[1]);
        System.out.println(b3[0] + " " + b3[1]);
    }
}
