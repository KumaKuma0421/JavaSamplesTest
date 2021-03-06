package RuntimeLoader;

public class Sample01 {
    private int number = 0;

    public Sample01() {
        System.out.println("This is a Sample01 constructor.");
    }

    public Sample01(int initialValue) {
        this.number = initialValue;
        System.out.println("This is a Sample01 constructor(" + initialValue + ")");
    }

    public boolean Test01(int number) {
        this.number = number;
        System.out.println("Sample01.Test01() this.number=" + this.number);

        return (number % 2) == 0;
    }

    public int Test02(int number, String message) {
        this.number = number;
        System.out.println("Sample01.Test02() this.number=" + this.number + " message=" + message);

        return (number % 2);
    }
}