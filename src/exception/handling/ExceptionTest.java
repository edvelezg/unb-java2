package exception.handling;
class ReThrowTest {
    void f() throws Exception {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Handled paritally in f()");
            throw e;
        }
    }

    void g() {
        try {
            f();
        } catch (Exception e) {
            System.out.println("Handled completely in g()");
        }
    }

    public static void main(String[] args) {
        new ReThrowTest().g();
    }
}