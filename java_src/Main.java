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
        System.out.println("3. UCS (Cost-based Expansion)");
        System.out.println("4. GBFS (Heuristic-based Search)");
        System.out.println("5. A* (Optimal Heuristic Search)");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        List<State> solution = null;

        if (choice == 1) {
            System.out.println("\n[INFO] BFS (Breadth-First Search) explores all states level-by-level.");
            System.out.println("It uses a Queue (FIFO) to ensure the first solution found is the SHORTEST path.");
            BFSSolver solver = new BFSSolver(a, b, target);
            solution = solver.solve();
        } else if (choice == 2) {
            System.out.println("\n[INFO] DFS (Depth-First Search) explores as deep as possible along each branch.");
            System.out.println("It uses a Stack (LIFO). It finds a solution quickly but it is often NOT the shortest.");
            DFSSolver solver = new DFSSolver(a, b, target);
            solution = solver.solve();
        } else if (choice == 3) {
            System.out.println("\n[INFO] UCS (Uniform Cost Search) expands nodes with lowest path cost.");
            System.out.println("It behaves identically to BFS here since step cost is uniformly 1.");
            UCSSolver solver = new UCSSolver(a, b, target);
            solution = solver.solve();
        } else if (choice == 4) {
            System.out.println("\n[INFO] GBFS (Greedy Best-First Search) expands nodes closest to the target using a heuristic.");
            System.out.println("It is fast but not guaranteed to find the shortest path.");
            GBFSSolver solver = new GBFSSolver(a, b, target);
            solution = solver.solve();
        } else if (choice == 5) {
            System.out.println("\n[INFO] A* Algorithm expands nodes considering both optimal path cost so far and heuristic remaining cost.");
            System.out.println("It ensures an optimal path (shortest) while focusing search effectively.");
            AStarSolver solver = new AStarSolver(a, b, target);
            solution = solver.solve();
        } else {
            System.out.println("\nInvalid choice. Defaulting to BFS.");
            BFSSolver solver = new BFSSolver(a, b, target);
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
