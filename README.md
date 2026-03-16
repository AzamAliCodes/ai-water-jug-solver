# AI Water Jug Solver - State Space Search

A comprehensive implementation of the classic **Water Jug Problem** using AI search algorithms (**BFS** and **DFS**). This project features both a visual web-based solver and a robust Java command-line application.

## 🚀 Features
- **Visual Web Demo**: Interactive 2-panel layout with animated water levels.
- **Dual-Algorithm Support**: Compare **Breadth-First Search** (Shortest Path) and **Depth-First Search** (Deep Exploration).
- **Step-by-Step Playback**: Navigate through the solution path at your own pace.
- **Java CLI Engine**: Independent, modular Java classes for core AI logic.
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
- **BFS (Queue/FIFO)**: Guaranteed to find the **Shortest Path**.
- **DFS (Stack/LIFO)**: Explores deep branches first; may find non-optimal paths.

---

## 🛠️ How to Run

### Web Version (Visual)
Simply open the `index.html` file in any modern web browser.
1.  Enter jug capacities and the target volume.
2.  Select the desired algorithm (BFS or DFS).
3.  Click **Find Solution** and use the `←` and `→` buttons to step through.

### Java Version (CLI)
1.  Open a terminal in the project root folder.
2.  **Compile**:
    ```bash
    javac java_src/Main.java
    ```
3.  **Run**:
    ```bash
    java java_src.Main
    ```

---

## 📂 Project Structure
- `index.html`: Main layout (2-panel interactive UI).
- `style.css`: Modern, responsive styling with animations.
- `script.js`: Web implementation of the BFS/DFS logic.
- `java_src/`:
    - `State.java`: Encapsulates (A, B) configurations.
    - `BFSSolver.java`: BFS implementation (Queue-based).
    - `DFSSolver.java`: DFS implementation (Stack-based).
    - `Main.java`: CLI entry point with algorithm menu.

---

## 📜 References
- [GeeksforGeeks: Water Jug Problem in AI](https://www.geeksforgeeks.org/water-jug-problem-in-ai/)
