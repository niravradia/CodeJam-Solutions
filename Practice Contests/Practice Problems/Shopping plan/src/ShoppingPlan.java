import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Aditya on 7/13/2015.
 */
public class ShoppingPlan {
    public static void main(String s[]) {
        File fin = new File("input.in");
        File fout = new File("output.out");

        Scanner scan = null;
        PrintWriter writer = null;
        try {
            scan = new Scanner(fin);
            writer = new PrintWriter(fout);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int T;
        T = scan.nextInt();

        int numItems, numStores;
        double priceGas;

        Store stores[];

        for (int cT = 1; cT <= T; cT++) {
            numItems = scan.nextInt();
            numStores = scan.nextInt();
            priceGas = scan.nextInt();
            stores = new Store[numStores];

            String item[] = new String[numItems];
            boolean perishable[] = new boolean[numItems];

            for (int i = 0; i < numItems; i++)
                perishable[i] = false;

            for (int i = 0; i < numItems; i++) {
                item[i] = scan.next();
                if (item[i].charAt(item[i].length() - 1) == '!') {
                    perishable[i] = true;
                    item[i] = item[i].substring(0, item[i].length() - 1);
                }
            }

            int x, y, midIndex;
            int price[] = new int[numItems];

            String pair, cItem, cPrice;
            Scanner ls = null;
            String t;
            for (int i = 0; i < numStores; i++) {
                ls = new Scanner(t=scan.nextLine());
                System.out.println(t);

                x = ls.nextInt();
                y = ls.nextInt();

                for(int j=0;j<numItems;j++)
                    price[j]=-1;

                while (ls.hasNext()) {
                    pair = ls.next();
                    midIndex = pair.lastIndexOf(':');
                    cItem = pair.substring(0, midIndex);
                    cPrice = pair.substring(midIndex + 1, pair.length());

                    for(int it=0;it<numItems;it++)
                        if(cItem.equals(item[it]))
                            price[it]=Integer.parseInt(cPrice);
                }

                stores[i] = new Store(x,y,numItems,price);
            }

            for(int i=0;i<numStores;i++)
                System.out.println(stores[i].toString());
        }

        writer.close();
    }
}
