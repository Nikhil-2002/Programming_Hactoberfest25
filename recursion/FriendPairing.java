package recursion;

public class FriendPairing {

    public static int pairs(int n) {

//		base case
        if (n==1 || n==2) {
            return n;
        }

        int singlePerson=pairs(n-1);

//
//		int pair=singlePerson * pairs(n-2);
//
//
//		int totalPairs=singlePerson +pair;
//		return totalPairs;



        return pairs(n-1)+ (n-1)*pairs(n-2);


    }

    public static void main(String[] args) {

        System.out.println(pairs(3));
    }

}
