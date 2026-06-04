import { fetchMathTask, startMathGame, verifyMathTask } from './mathApi.js';

let currentTaskId = null;
let onScoreCallback = null;

export async function init(containerElement, scoreCallback, sessionData) {
    onScoreCallback = scoreCallback;

    try {
        await startMathGame({
            sessionId: sessionData.sessionId,
            username: sessionData.username
        });
    } catch (error) {
        console.error("Błąd startu gry:", error);
    }

    containerElement.innerHTML = `
        <h1 id="equation-display" style="font-size: 48px; margin-bottom: 30px;">Loading equation...</h1>
        <input type="number" id="math-answer" style="font-size: 24px; padding: 10px; width: 100%; text-align: center; border-radius: 8px; border: 2px solid #ccc; margin-bottom: 20px;" placeholder="Your input">
        <button id="submit-btn" style="width: 100%; padding: 15px; font-size: 18px; background: #2b2d42; color: white; border: none; border-radius: 8px; cursor: pointer;">Check</button>
    `;

    document.getElementById('submit-btn').addEventListener('click', checkAnswer);
    document.getElementById('math-answer').addEventListener('keypress', (e) => {
        if (e.key === 'Enter') checkAnswer();
    });

    loadNewTask();
}

export function cleanup(containerElement) {
    containerElement.innerHTML = '';
    onScoreCallback = null;
    currentTaskId = null;
}

async function loadNewTask() {
    const display = document.getElementById('equation-display');
    const input = document.getElementById('math-answer');

    display.innerText = "Loading equation...";
    input.value = "";
    input.disabled = true;

    try {
        const task = await fetchMathTask();
        display.innerText = `${task.equation} = ?`;
        currentTaskId = task.taskId;
        input.disabled = false;
        input.focus();
    } catch (error) {
        display.innerText = "Server error";
    }
}

async function checkAnswer() {
    const input = document.getElementById('math-answer');
    const submitBtn = document.getElementById('submit-btn');
    const userAnswer = parseInt(input.value, 10);

    if (isNaN(userAnswer)) return;

    submitBtn.disabled = true;

    try {
        const result = await verifyMathTask({
            userInput: userAnswer,
            taskId: currentTaskId
        });

        if (result.isCorrect) {
            onScoreCallback(result.pointsGained || 10);
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
        alert("Error connecting to the server.");
    } finally {
        submitBtn.disabled = false;
    }
}