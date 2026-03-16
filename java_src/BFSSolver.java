package java_src;

import java.util.*;

public class BFSSolver {
    private final int capA, capB, target;

    public BFSSolver(int capA, int capB, int target) {
        this.capA = capA;
        this.capB = capB;
        this.target = target;
    }

    public List<State> solve() {
        Queue<State> open = new LinkedList<>(); // FIFO Queue for BFS
        Set<State> closed = new HashSet<>();

        State initial = new State(0, 0, "Both jugs empty", null);
        open.add(initial);
        closed.add(initial);

        while (!open.isEmpty()) {
            State current = open.poll();

            if (current.a == target || current.b == target) {
                return reconstructPath(current);
            }

            for (State next : getSuccessors(current)) {
                if (!closed.contains(next)) {
                    closed.add(next);
                    open.add(next);
                }
            }
        }
        return null;
    }

    private List<State> getSuccessors(State s) {
        List<State> successors = new ArrayList<>();
        successors.add(new State(capA, s.b, "Fill Jug A", s));
        successors.add(new State(s.a, capB, "Fill Jug B", s));
        successors.add(new State(0, s.b, "Empty Jug A", s));
        successors.add(new State(s.a, 0, "Empty Jug B", s));
        int p1 = Math.min(s.a, capB - s.b);
        successors.add(new State(s.a - p1, s.b + p1, "Pour A into B", s));
        int p2 = Math.min(s.b, capA - s.a);
        successors.add(new State(s.a + p2, s.b - p2, "Pour B into A", s));
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
}
