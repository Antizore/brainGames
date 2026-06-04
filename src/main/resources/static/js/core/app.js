import { initSession, getSessionData } from './session.js';
import * as mathGame from '../math/mathGame.js';
import * as memoryGame from '../memory/memoryGame.js';

// Game modules dict
const gamesRegistry = {
    'math-addition': mathGame,
    'memory': memoryGame
};

let globalScore = 0;
let currentGame = null;

const views = {
    menu: document.getElementById('view-menu'),
    game: document.getElementById('view-game')
};
const gameContent = document.getElementById('game-content');
const scoreDisplay = document.getElementById('score');
const gameTitle = document.getElementById('game-title');

document.addEventListener('DOMContentLoaded', async () => {
    await initSession();
    setupNavigation();
});

function setupNavigation() {
    document.querySelectorAll('.game-btn').forEach(btn => {
        btn.addEventListener('click', (e) => {
            if (btn.classList.contains('disabled')) return;
            const gameId = e.currentTarget.dataset.game;
            openGame(gameId);
        });
    });

    document.getElementById('btn-back').addEventListener('click', goHome);
}

function openGame(gameId) {
    const selectedGame = gamesRegistry[gameId];
    if (!selectedGame) return console.error(`No game: ${gameId}`);

    views.menu.classList.remove('active');
    views.game.classList.add('active');

    globalScore = 0;
    updateScoreDisplay();

    const btn = document.querySelector(`.game-btn[data-game="${gameId}"]`);
    gameTitle.innerText = btn ? btn.textContent.trim() : "Game";

    currentGame = selectedGame;


    currentGame.init(gameContent, addPoints, getSessionData());
}

function goHome() {
    if (currentGame && typeof currentGame.cleanup === 'function') {
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