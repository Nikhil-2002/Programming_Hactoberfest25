package recursion;

public class HalfPower {

    private static int power(int x,int n) {
        if(n==0) {
            return 1;
        }

        int halfpower=power(x, n/2);
        int fullpawer=halfpower* halfpower;

        if (n % 2 !=0) {

            fullpawer= x*fullpawer;
        }

        return fullpawer;
    }

    public static void main(String[] args) {

        System.out.println(power(2, 5));;
    }
}
