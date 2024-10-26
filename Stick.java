// Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

// Please name your class Main
class Main {
        static class Event {
        int x; 
        int type; 
        int y1, y2; 

        Event(int x, int type, int y1, int y2) {
            this.x = x;
            this.type = type;
            this.y1 = y1;
            this.y2 = y2;
        }
    }
    
    
    static long calculateUnionArea(List<Event> events) {
        events.sort(Comparator.comparingInt(a -> a.x));

        TreeMap<Integer, Integer> activeIntervals = new TreeMap<>();
        long totalArea = 0;
        int prevX = events.get(0).x;

        for (Event event : events) {
            int currentX = event.x;
            if (currentX != prevX) {
                int height = calculateActiveHeight(activeIntervals);
                totalArea += (long)(currentX - prevX) * height;
                prevX = currentX;
            }
if (event.type == 1) { 
    activeIntervals.put(event.y1, activeIntervals.getOrDefault(event.y1, 0) + 1);
    activeIntervals.put(event.y2, activeIntervals.getOrDefault(event.y2, 0) - 1);
} else {
    activeIntervals.put(event.y1, activeIntervals.getOrDefault(event.y1, 0) - 1);
    activeIntervals.put(event.y2, activeIntervals.getOrDefault(event.y2, 0) + 1);
}
if (activeIntervals.get(event.y1) == 0) {
    activeIntervals.remove(event.y1);
}
if (activeIntervals.get(event.y2) == 0) {
    activeIntervals.remove(event.y2);
}
        }

        return totalArea;
    }

    static int calculateActiveHeight(TreeMap<Integer, Integer> activeIntervals) {
        int height = 0;
        int count = 0; 
        int lastY = -1;

        for (Map.Entry<Integer, Integer> entry : activeIntervals.entrySet()) {
            if (count > 0 && lastY != -1) {
                height += entry.getKey() - lastY; 
            }
            count += entry.getValue(); 
            lastY = entry.getKey(); 
        }


        activeIntervals.entrySet().removeIf(e -> e.getValue() == 0);
        
        return height;
    }
    
    
    
	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		int L = in.nextInt();

        List<Event> events = new ArrayList<>();
		
		for(int i = 0;i<N;i++)
		{
		    int x1 = i * K - L;
            int x2 = i * K + L;
            int y1 = i * K - L;
            int y2 = i * K + L;

 
            events.add(new Event(x1, 1, y1, y2));
            events.add(new Event(x2, -1, y1, y2)); 
		}
		long area = calculateUnionArea(events);
        System.out.println(area);
	}
}
