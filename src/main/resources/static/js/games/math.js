import { fetchMathTask, verifyMathTask } from '../api.js';

let currentTask = null;
let onScoreCallback = null;
let currentTaskId = null;

// Game init
export function init(containerElement, scoreCallback) {
    onScoreCallback = scoreCallback;

    containerElement.innerHTML = `
        <h1 id="equation-display" style="font-size: 48px; margin-bottom: 30px;">Ładowanie...</h1>
        <input type="number" id="math-answer" style="font-size: 24px; padding: 10px; width: 100%; text-align: center; border-radius: 8px; border: 2px solid #ccc; margin-bottom: 20px;" placeholder="Wpisz wynik">
        <button id="submit-btn" style="width: 100%; padding: 15px; font-size: 18px; background: #2b2d42; color: white; border: none; border-radius: 8px; cursor: pointer;">Sprawdź</button>
    `;

    document.getElementById('submit-btn').addEventListener('click', checkAnswer);
    document.getElementById('math-answer').addEventListener('keypress', (e) => {
        if (e.key === 'Enter') checkAnswer();
    });

    loadNewTask();
}


export function cleanup(containerElement) {
    containerElement.innerHTML = '';
    currentTask = null;
    onScoreCallback = null;
}

async function loadNewTask() {
    const display = document.getElementById('equation-display');
    const input = document.getElementById('math-answer');

    display.innerText = "⏳ Ładowanie...";
    input.value = "";
    input.disabled = true;

    try {
        currentTask = await fetchMathTask();
        display.innerText = `${currentTask.equation} = ?`;
        currentTaskId = currentTask.taskId
        input.disabled = false;
        input.focus();
    } catch (error) {
        console.error(error);
        display.innerText = "Błąd serwera ❌";
    }
}

async function checkAnswer() {
    const input = document.getElementById('math-answer');
    const submitBtn = document.getElementById('submit-btn');
    const userAnswer = parseInt(input.value, 10);

    if (isNaN(userAnswer)) return;

    const payload = {
        userInput: userAnswer,
        taskId: currentTaskId
    };

    submitBtn.disabled = true;

    try {
        const result = await verifyMathTask(payload);

        if (result.isCorrect) {
            onScoreCallback(result.points || 10);
            input.style.backgroundColor = "#d4edda";
            setTimeout(() => {
                input.style.backgroundColor = "";
                loadNewTask();
            }, 300);
        } else {
            input.style.backgroundColor = "#f8d7da";
            input.value = "";
            setTimeout(() => {
                input.style.backgroundColor = "";
                input.focus();
            }, 500);
        }
    } catch (error) {
        console.error(error);
        alert("Error during connecting to the server.");
    } finally {
        submitBtn.disabled = false;
    }
}