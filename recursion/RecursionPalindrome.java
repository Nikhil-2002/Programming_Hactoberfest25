package recursion;

public class RecursionPalindrome {
    public static boolean palindrome(String s,int i,int j) {




        if (i>=j) {
            return true;

        }

        if (s.charAt(i) !=s.charAt(j)) {

            return false;
        }

        i=i+1;
        j=j-1;
        return palindrome(s, i, j);

    }

    public static void main(String[] args) {

        String str="MADAM";

        int j=str.length()-1;
        System.out.println(palindrome(str,0,j));

    }
}
