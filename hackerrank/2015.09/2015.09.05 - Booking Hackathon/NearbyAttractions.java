package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class NearbyAttractions {
    static final double PI = 3.14159265359;
    static final double MIN_PER_HOUR = 60;
    static final double EARTH_RADIUS = 6371;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Attraction[] a = new Attraction[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Attraction(in.nextInt(), in.nextDouble(), in.nextDouble());
        }

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            Point src = new Point(in.nextDouble(), in.nextDouble());
            String transport = in.next();
            double time = in.nextDouble();
            double speed;
            if (transport.equals("metro")) speed = 20.0;
            else if (transport.equals("bike")) speed = 15.0;
            else speed = 5.0;

            for (int j = 0; j < n; j++) {
                a[j].distance = findDistance(src, a[j]);
            }
            Arrays.sort(a);
            for (int j = 0; j < n; j++) {
                double taken = MIN_PER_HOUR * a[j].distance / speed;
                if (taken > time) break;
                out.print(a[j].id + " ");
            }
            out.println();
        }
    }

    private static double findDistance(Point p1, Point p2) {
        double lat1 = Math.toRadians(p1.latitude);
        double lat2 = Math.toRadians(p2.latitude);
        double long1 = Math.toRadians(p1.longitude);
        double long2 = Math.toRadians(p2.longitude);

        double res = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) *
        Math.cos(lat2) * Math.cos(long2 - long1)) * EARTH_RADIUS;

        return Math.round(res * 100.0) / 100.0;
    }
}

class Point {
    double latitude;
    double longitude;

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

class Attraction extends Point implements Comparable<Attraction> {
    int id;
    double distance;

    public Attraction(int id, double latitude, double logitutde) {
        super(latitude, logitutde);
        this.id = id;
    }

    @Override
    public int compareTo(Attraction attraction) {
        if (distance < attraction.distance)
            return -1;
        if (distance > attraction.distance)
            return 1;
        return id - attraction.id;
    }
}