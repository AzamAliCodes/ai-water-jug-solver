package java_src;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== AI Water Jug Solver ===");
        System.out.print("Capacity A: ");
        int a = scanner.nextInt();
        System.out.print("Capacity B: ");
        int b = scanner.nextInt();
        System.out.print("Target: ");
        int target = scanner.nextInt();

        System.out.println("\nSelect Algorithm:");
        System.out.println("1. BFS (Shortest Path)");
        System.out.println("2. DFS (Deep Search)");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        List<State> solution = null;

        if (choice == 1) {
            System.out.println("\n[INFO] BFS (Breadth-First Search) explores all states level-by-level.");
            System.out.println("It uses a Queue (FIFO) to ensure the first solution found is the SHORTEST path.");
            BFSSolver solver = new BFSSolver(a, b, target);
            solution = solver.solve();
        } else {
            System.out.println("\n[INFO] DFS (Depth-First Search) explores as deep as possible along each branch.");
            System.out.println("It uses a Stack (LIFO). It finds a solution quickly but it is often NOT the shortest.");
            DFSSolver solver = new DFSSolver(a, b, target);
            solution = solver.solve();
        }

        if (solution != null) {
            System.out.println("\n--- Solution (" + (solution.size() - 1) + " steps) ---");
            for (int i = 0; i < solution.size(); i++) {
                System.out.printf("Step %d: %s\n", i, solution.get(i));
            }
        } else {
            System.out.println("No solution found.");
        }
        scanner.close();
    }
}
