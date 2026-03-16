document.addEventListener('DOMContentLoaded', () => {
    // DOM Elements
    const capAInput = document.getElementById('capacityA');
    const capBInput = document.getElementById('capacityB');
    const targetInput = document.getElementById('target');
    const solveBtn = document.getElementById('solveBtn');
    const resetBtn = document.getElementById('resetBtn');
    
    const jugAWater = document.querySelector('#jugA .water');
    const jugBWater = document.querySelector('#jugB .water');
    const labelA = document.getElementById('labelA');
    const labelB = document.getElementById('labelB');
    
    const statusMsg = document.getElementById('status');
    const stepsList = document.getElementById('stepsList');
    const playbackControls = document.querySelector('.playback-controls');
    const stepCounter = document.getElementById('stepCounter');
    const prevBtn = document.getElementById('prevStep');
    const nextBtn = document.getElementById('nextStep');

    let solutionPath = [];
    let currentStepIndex = 0;

    // GCD function to check solvability
    function gcd(a, b) {
        while (b) {
            a %= b;
            [a, b] = [b, a];
        }
        return a;
    }

    function isSolvable(a, b, target) {
        if (target > Math.max(a, b)) return false;
        return target % gcd(a, b) === 0;
    }

    // BFS/DFS Solver
    function solveWaterJug(capA, capB, target, type = 'bfs') {
        const queue = [{ a: 0, b: 0, history: [{ a: 0, b: 0, action: "Both jugs empty" }] }]; // State objects
        const visited = new Set();
        const visitedKey = (a, b) => `${a},${b}`;
        
        if (type === 'bfs') visited.add("0,0");

        while (queue.length > 0) {
            const current = type === 'bfs' ? queue.shift() : queue.pop();
            const { a, b, history } = current;

            if (type === 'dfs') {
                if (visited.has(visitedKey(a, b))) continue;
                visited.add(visitedKey(a, b));
            }

            // Check if goal reached
            if (a === target || b === target) {
                return history;
            }

            const possibleStates = [
                { a: capA, b: b, action: `Fill Jug A (${capA}L)` },
                { a: a, b: capB, action: `Fill Jug B (${capB}L)` },
                { a: 0, b: b, action: "Empty Jug A" },
                { a: a, b: 0, action: "Empty Jug B" },
                { 
                    a: Math.max(0, a - (capB - b)), 
                    b: Math.min(capB, b + a), 
                    action: "Pour A into B" 
                },
                { 
                    a: Math.min(capA, a + b), 
                    b: Math.max(0, b - (capA - a)), 
                    action: "Pour B into A" 
                }
            ];

            for (const state of possibleStates) {
                const key = visitedKey(state.a, state.b);
                if (!visited.has(key)) {
                    if (type === 'bfs') visited.add(key); 
                    queue.push({ ...state, history: [...history, { a: state.a, b: state.b, action: state.action }] });
                }
            }
        }
        return null;
    }

    function updateUI(step) {
        const capA = parseInt(capAInput.value);
        const capB = parseInt(capBInput.value);

        // Update Water Levels (height %)
        const heightA = (step.a / capA) * 100;
        const heightB = (step.b / capB) * 100;

        jugAWater.style.height = `${heightA}%`;
        jugBWater.style.height = `${heightB}%`;

        // Update Labels
        labelA.textContent = `${step.a} / ${capA} L`;
        labelB.textContent = `${step.b} / ${capB} L`;

        // Update Steps List highlighting
        document.querySelectorAll('.step-item').forEach((item, index) => {
            if (index === currentStepIndex) {
                item.classList.add('active');
                item.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
            } else {
                item.classList.remove('active');
            }
        });

        stepCounter.textContent = `Step ${currentStepIndex} / ${solutionPath.length - 1}`;
    }

    function renderSteps(path) {
        stepsList.innerHTML = '';
        path.forEach((step, index) => {
            const div = document.createElement('div');
            div.className = 'step-item';
            div.innerHTML = `
                <span>${index}. ${step.action}</span>
                <span>(${step.a}, ${step.b})</span>
            `;
            stepsList.appendChild(div);
        });
    }

    solveBtn.addEventListener('click', () => {
        const capA = parseInt(capAInput.value);
        const capB = parseInt(capBInput.value);
        const target = parseInt(targetInput.value);
        const algoType = document.getElementById('algorithm').value;

        if (isNaN(capA) || isNaN(capB) || isNaN(target)) {
            statusMsg.textContent = "Please enter valid numbers.";
            return;
        }

        if (!isSolvable(capA, capB, target)) {
            statusMsg.textContent = "No solution possible for these values.";
            stepsList.innerHTML = '<p class="placeholder">Target must be a multiple of GCD(A, B) and ≤ max(A, B).</p>';
            playbackControls.style.display = 'none';
            return;
        }

        statusMsg.textContent = `Finding solution using ${algoType.toUpperCase()}...`;
        const result = solveWaterJug(capA, capB, target, algoType);

        if (result) {
            solutionPath = result;
            currentStepIndex = 0;
            statusMsg.textContent = `Solution found in ${result.length - 1} steps!`;
            renderSteps(result);
            updateUI(result[0]);
            playbackControls.style.display = 'flex';
        } else {
            statusMsg.textContent = "No solution found.";
        }
    });

    resetBtn.addEventListener('click', () => {
        capAInput.value = 4;
        capBInput.value = 3;
        targetInput.value = 2;
        jugAWater.style.height = '0%';
        jugBWater.style.height = '0%';
        labelA.textContent = '0 / 4 L';
        labelB.textContent = '0 / 3 L';
        statusMsg.textContent = '';
        stepsList.innerHTML = '<p class="placeholder">Click "Find Solution" to see the steps.</p>';
        playbackControls.style.display = 'none';
        solutionPath = [];
        currentStepIndex = 0;
    });

    prevBtn.addEventListener('click', () => {
        if (currentStepIndex > 0) {
            currentStepIndex--;
            updateUI(solutionPath[currentStepIndex]);
        }
    });

    nextBtn.addEventListener('click', () => {
        if (currentStepIndex < solutionPath.length - 1) {
            currentStepIndex++;
            updateUI(solutionPath[currentStepIndex]);
        }
    });
});
