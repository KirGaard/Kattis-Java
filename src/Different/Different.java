package Different;

import java.util.Scanner;

public class Different {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()){
            long val = sc.nextLong() - sc.nextLong();
            System.out.println(val < 0 ? -val : val);
        }
    }
}
