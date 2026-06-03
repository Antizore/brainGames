import * as mathGame from './games/math.js';
import {initSession} from './session.js';

document.addEventListener('DOMContentLoaded', async () => {
    const sessionToken = await initSession();
})


let globalScore = 0;
let currentGame = null;

const views = {
    menu: document.getElementById('view-menu'),
    game: document.getElementById('view-game')
};
const gameContent = document.getElementById('game-content');
const scoreDisplay = document.getElementById('score');
const gameTitle = document.getElementById('game-title');


document.querySelectorAll('.game-btn').forEach(btn => {
    btn.addEventListener('click', (e) => {
        if (btn.classList.contains('disabled')) return;
        const gameId = e.currentTarget.dataset.game;
        openGame(gameId);
    });
});


document.getElementById('btn-back').addEventListener('click', goHome);

// --- NAV LOGIC ---

function openGame(gameId) {
    views.menu.classList.remove('active');
    views.game.classList.add('active');
    globalScore = 0;
    updateScoreDisplay();

    if (gameId === 'math-addition') {
        gameTitle.innerText = "Dodawanie";
        currentGame = mathGame;
        currentGame.init(gameContent, addPoints);
    }
}

function goHome() {
    if (currentGame) {
        currentGame.cleanup(gameContent);
        currentGame = null;
    }
    views.game.classList.remove('active');
    views.menu.classList.add('active');
}

function addPoints(points) {
    globalScore += points;
    updateScoreDisplay();
}

function updateScoreDisplay() {
    scoreDisplay.innerText = globalScore;
}