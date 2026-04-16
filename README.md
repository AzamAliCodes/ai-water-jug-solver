# AI Water Jug Solver - Dual Implementation

A comprehensive implementation of the classic **Water Jug Problem** using AI search algorithms (**BFS**, **DFS**, **UCS**, **GBFS**, and **A***). This project features a **Dual Implementation** that works both as an interactive web-based visualizer and an independent Java command-line application.

## 🚀 Features
- **Dual Engine**: Independent logic in both **JavaScript** (for the web) and **Java** (for the CLI).
- **Visual Web Demo**: Interactive 2-panel layout with animated water levels.
- **Multi-Algorithm Support**: Compare **Breadth-First Search** (BFS), **Depth-First Search** (DFS), **Uniform Cost Search** (UCS), **Greedy Best-First Search** (GBFS), and the **A* Search Algorithm**.
- **Step-by-Step Playback**: Navigate through the solution path at your own pace.
- **Java CLI Engine**: Robust, modular Java classes that work independently of any frontend.
- **Solvability Validation**: Built-in GCD-based logic to detect impossible states.

## 🧠 Core Logic (State Space Search)
The problem is modeled as a state-space tree where each node represents `(Jug A, Jug B)`.

### Production Rules:
1.  **Fill A**: `(CapacityA, y)`
2.  **Fill B**: `(x, CapacityB)`
3.  **Empty A**: `(0, y)`
4.  **Empty B**: `(x, 0)`
5.  **Pour A -> B**: Move water until B is full or A is empty.
6.  **Pour B -> A**: Move water until A is full or B is empty.

### Search Strategies:
- **BFS (Queue/FIFO)**: Guaranteed to find the **Shortest Path** (Uninformed).
- **DFS (Stack/LIFO)**: Explores deep branches first; may find non-optimal paths (Uninformed).
- **UCS (Priority Queue)**: Cost-based expansion (Uninformed).
- **GBFS (Priority Queue + Heuristic)**: Heuristic-based, fast but not necessarily optimal (Informed).
- **A\* (Priority Queue + Cost + Heuristic)**: Optimal and efficient (Informed).

---

## 🛠️ How to Run

### Web Version (Visual)
Open the `index.html` file in any modern web browser or visit the **GitHub Pages** deployment.
1.  Enter jug capacities and the target volume.
2.  Select the desired algorithm (BFS, DFS, UCS, GBFS, or A*).
3.  Click **Find Solution** and use the `←` and `→` buttons to step through.
4.  Change the algorithm dynamically after solving to instantly visualize the different paths and steps.

### Java Version (CLI)
The Java implementation is fully independent and can be run from the terminal.
1.  Open a terminal in the project root folder.
2.  **Compile**:
    ```bash
    javac java_src/*.java
    ```
3.  **Run**:
    ```bash
    java java_src.Main
    ```

---

## 📂 Project Structure
- `index.html`: Main layout (2-panel interactive UI).
- `style.css`: Modern, responsive styling with animations.
- `script.js`: Web implementation of the search algorithms and UI logic.
- `java_src/`: Core AI Search Logic (Independent Java Implementation)
    - `State.java`: Encapsulates (A, B) configurations.
    - `BFSSolver.java`: Breadth-First Search implementation (Queue-based).
    - `DFSSolver.java`: Depth-First Search implementation (Stack-based).
    - `UCSSolver.java`: Uniform Cost Search implementation.
    - `GBFSSolver.java`: Greedy Best-First Search implementation.
    - `AStarSolver.java`: A* Search implementation.
    - `WaterJugSolver.java`: Base/Unified solver class mapping out state production rules.
    - `Main.java`: CLI entry point with interactive algorithm selection menu.

---

## 📜 References
- [GeeksforGeeks: Water Jug Problem in AI](https://www.geeksforgeeks.org/water-jug-problem-in-ai/)
