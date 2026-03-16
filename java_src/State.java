package java_src;

import java.util.Objects;

/**
 * Represents a state in the Water Jug Problem: (jugA, jugB)
 */
public class State {
    public int a; // Current volume in Jug A
    public int b; // Current volume in Jug B
    public String action; // Action taken to reach this state
    public State parent; // Link to previous state (for path reconstruction)

    public State(int a, int b, String action, State parent) {
        this.a = a;
        this.b = b;
        this.action = action;
        this.parent = parent;
    }

    // Crucial for Set/Map to recognize visited states
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return a == state.a && b == state.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return String.format("%-25s -> (%d, %d)", action, a, b);
    }
}
