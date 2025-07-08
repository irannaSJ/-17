package miniproject;

import java.util.Arrays;
import java.util.Scanner;

public class RSAencrypt {
    static int gcd(int m,int n){
        while (n!=0){
            int r= m%n;
            m=n;
            n=r;
        }
        return m;
    }

    public static String  token(String password){
        int p=17,q = 19,n=0,e=0,d=0,phi = 0;
        int nummes[] = new int[100];
        int encrypted[] = new int[100];

        int i=0,j=0,nofelem =0;

        Scanner sc = new Scanner(System.in);
        String pass = password;

        n=p*q;
        phi = (p-1)*(q-1);
        for(i=2;i<phi;i++){
            if(gcd(i,phi)==1)
                break;
            e=i;
        }
        for (i=2;i<phi;i++){
            if ((e*i -1)% phi ==0){
                break;
            }d=i;
        }

        for (i=0;i<pass.length();i++){
            char c = pass.charAt(i);
            int a = (int) c;
            nummes[i] = c-96;
        }

        nofelem = pass.length();

        for(i=0;i<nofelem;i++){
            encrypted[i] = 1;
            for(j=0;j<e;j++){
                encrypted[i] = (encrypted[i]*nummes[i]%n);
            }
        }
        StringBuilder encryptpass = new StringBuilder();
        for(i=0;i<nofelem;i++){
           encryptpass.append( encrypted[i]);
        }
        return encryptpass.toString();
    }
}
