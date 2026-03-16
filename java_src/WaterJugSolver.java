package java_src;

import java.util.*;

/**
 * AI Production System for Water Jug Problem
 */
public class WaterJugSolver {
    private final int capA, capB, target;

    public WaterJugSolver(int capA, int capB, int target) {
        this.capA = capA;
        this.capB = capB;
        this.target = target;
    }

    /**
     * Breadth-First Search (BFS) for optimal solution path.
     */
    public List<State> solve() {
        Queue<State> open = new LinkedList<>();
        Set<State> closed = new HashSet<>();

        State initial = new State(0, 0, "Both jugs empty", null);
        open.add(initial);
        closed.add(initial);

        while (!open.isEmpty()) {
            State current = open.poll();

            // Check Goal
            if (current.a == target || current.b == target) {
                return reconstructPath(current);
            }

            // Generate Transitions (State Space Expansion)
            List<State> successors = getSuccessors(current);
            for (State next : successors) {
                if (!closed.contains(next)) {
                    closed.add(next);
                    open.add(next);
                }
            }
        }
        return null; // No solution
    }

    private List<State> getSuccessors(State s) {
        List<State> successors = new ArrayList<>();

        // 1. Fill A
        successors.add(new State(capA, s.b, "Fill Jug A", s));
        // 2. Fill B
        successors.add(new State(s.a, capB, "Fill Jug B", s));
        // 3. Empty A
        successors.add(new State(0, s.b, "Empty Jug A", s));
        // 4. Empty B
        successors.add(new State(s.a, 0, "Empty Jug B", s));
        // 5. Pour A into B
        int pourAtoB = Math.min(s.a, capB - s.b);
        successors.add(new State(s.a - pourAtoB, s.b + pourAtoB, "Pour A into B", s));
        // 6. Pour B into A
        int pourBtoA = Math.min(s.b, capA - s.a);
        successors.add(new State(s.a + pourBtoA, s.b - pourBtoA, "Pour B into A", s));

        return successors;
    }

    private List<State> reconstructPath(State goal) {
        List<State> path = new ArrayList<>();
        State curr = goal;
        while (curr != null) {
            path.add(curr);
            curr = curr.parent;
        }
        Collections.reverse(path);
        return path;
    }

    // Helper: GCD for solvability check
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public boolean isSolvable() {
        if (target > Math.max(capA, capB)) return false;
        return target % gcd(capA, capB) == 0;
    }
}
