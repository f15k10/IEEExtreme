import java.lang.*;
import java.uti

class Main {
public static void main (String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        long N = in.nextLong();
        long x = in.nextLong();
        int N_int = (int) N;
        
        long[] bricks = new long[N_int];
        
        for(int i = 0; i<N_int;i++)
        {
            bricks[i] = in.nextInt();
        }
        
        Arrays.sort(bricks);
        long[] sortedBricks = new long[N_int];
        for (int i = 0; i < N_int; i++) {
            sortedBricks[i] = bricks[N_int - 1 - i]; 
        }
   
        List<Stack<Long>> stacks = new ArrayList<>();

        for (long brick : sortedBricks) {
            boolean placed = false;
            for (Stack<Long> stack : stacks) {
                if (brick + x <= stack.peek()) {
                    stack.push(brick);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                Stack<Long> newStack = new Stack<>();
                newStack.push(brick);
                stacks.add(newStack);
            }
        }
        System.out.println(stacks.size());
        
        for (Stack<Long> stack : stacks) {
            System.out.print(stack.size() + " ");
            for (long b : stack) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        
    }
}
