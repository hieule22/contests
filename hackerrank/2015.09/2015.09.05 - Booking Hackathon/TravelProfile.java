package taskdirectory;

import java.util.*;
import java.io.PrintWriter;

public class TravelProfile {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        Hotel[] hotel = new Hotel[n];
        for (int i = 0; i < n; i++) {
            hotel[i] = new Hotel(in.nextInt(), in.nextInt());
            String line = in.nextLine();
            line = line.substring(1);
            for (String f : line.split(" ")) {
                hotel[i].addFacility(f);
            }
        }

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int budget = in.nextInt();
            String line = in.nextLine();
            List<String> list = new LinkedList<String>();
            line = line.substring(1);
            for (String s : line.split(" ")) list.add(s);
            List<Hotel> hotelList = new ArrayList<Hotel>(n);
            for (int j = 0; j < n; j++) {
                if (hotel[j].price > budget) continue;
                boolean isOk = true;
                for (String f : list) {
//                    System.out.println(f);
                    if (!hotel[j].facility.contains(f)) {
                        isOk = false;
                        break;
                    }
                }
//                System.out.println(hotel[j].id + " " + isOk);
                if (isOk) hotelList.add(hotel[j]);
            }
            Collections.sort(hotelList);
            for (int j = 0; j < hotelList.size(); j++)
                out.print(hotelList.get(j).id + " ");
            out.println();
//            System.out.println();
        }
    }
}

class Hotel implements Comparable<Hotel> {
    int id;
    int price;
    Set<String> facility;

    public Hotel(int id, int price) {
        this.id = id;
        this.price = price;
        facility = new HashSet<String>();
    }

    public void addFacility(String f) {
        facility.add(f);
    }

    @Override
    public int compareTo(Hotel hotel) {
        if (facility.size() != hotel.facility.size())
            return hotel.facility.size() - facility.size();
        if (price != hotel.price)
            return price - hotel.price;
        return id - hotel.id;
    }
}
