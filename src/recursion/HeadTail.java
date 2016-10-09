package recursion;

public class HeadTail {

    public static void tail(int n) {
        if (n == 0)
            return;
        else
            System.out.print(n + ", ");

        tail(n - 1);
    }

    public static void head(int n) {
        if (n == 0)
            return;
        else
            head(n - 1);

        System.out.print(n + ", ");
    }

    public static void main(String[] args) {
        tail(5);
        System.out.println();
        head(5);

    }
}
