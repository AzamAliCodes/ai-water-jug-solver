package java_src;

import java.util.*;

public class GBFSSolver {
    private final int capA, capB, target;

    public GBFSSolver(int capA, int capB, int target) {
        this.capA = capA;
        this.capB = capB;
        this.target = target;
    }

    private class Node implements Comparable<Node> {
        State state;
        int h;
        public Node(State state) {
            this.state = state;
            this.h = heuristic(state);
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.h, other.h);
        }
    }

    private int heuristic(State s) {
        return Math.min(Math.abs(s.a - target), Math.abs(s.b - target));
    }

    public List<State> solve() {
        PriorityQueue<Node> open = new PriorityQueue<>();
        Set<State> closed = new HashSet<>();

        State initial = new State(0, 0, "Both jugs empty", null);
        open.add(new Node(initial));

        while (!open.isEmpty()) {
            Node current = open.poll();

            if (current.state.a == target || current.state.b == target) {
                return reconstructPath(current.state);
            }

            if (closed.contains(current.state)) continue;
            closed.add(current.state);

            for (State next : getSuccessors(current.state)) {
                if (!closed.contains(next)) {
                    open.add(new Node(next));
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
