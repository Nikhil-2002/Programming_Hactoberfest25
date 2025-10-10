package recursion;

public class DigitToString {

    public static void  digitToString(int key) {

        String arr[]= {"zero","one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        if(key== 0) {

            return;
        }

        int rem=key %10;

        digitToString( key/10);

        System.out.print(arr[rem]+" ");






    }

    static public void main(String[] args) {

        digitToString(4567);


    }
}

