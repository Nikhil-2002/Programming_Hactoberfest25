package recursion;

public class PrintBinaryString {

    private static void binary( int n,int lastIndex, String str) {

        if (n==0) {

            System.out.println(str);
            return;
        }


        binary(n-1, 0, str+"0");
        if (lastIndex ==0) {

            binary(n-1, 1, str+"1");
        }


    }


    public static void main(String[] args) {

        binary(3, 0, "");
    }

}
