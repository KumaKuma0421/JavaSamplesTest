package RuntimeLoader;

public class Sample02 {
    private int number = 0;

    public Sample02() {
        System.out.println("This is a Sample02 constructor.");
    }

    public Sample02(int initialValue) {
        this.number = initialValue;
        System.out.println("This is a Sample02 constructor(" + initialValue + ")");
    }

    public boolean Test01(int number) {
        this.number = number;
        System.out.println("Sample02.Test01() this.number=" + this.number);

        return (number % 2) == 0;
    }

    public int Test02(int number, String message) {
        this.number = number;
        System.out.println("Sample02.Test02() this.number=" + this.number + " message=" + message);

        return (number + 1);
    }
}