// js/session.js

const SESSION_KEY = 'brain_games_token';
const USERNAME_KEY = 'brain_games_username';

export async function initSession() {

    let token = localStorage.getItem(SESSION_KEY);
    let username = localStorage.getItem(USERNAME_KEY);


    if (!token) {
        try {
            const response = await fetch('/api/v1/auth/session', { method: 'POST' });
            const data = await response.json();


            localStorage.setItem(SESSION_KEY, data.sessionId);
            localStorage.setItem(USERNAME_KEY, data.username);



            token = data.sessionId;
            username = data.username;
        } catch (error) {
            console.error("Error during session init:", error);
        }
    }


    const welcomeEl = document.getElementById('welcome-user');
    if (welcomeEl) welcomeEl.innerText = `Hi, ${username}!`;

    return token;
}


function getToken() {
    return localStorage.getItem(SESSION_KEY);
}

export function getSessionData() {
    return {
        sessionId: localStorage.getItem(SESSION_KEY),
        username: localStorage.getItem(USERNAME_KEY)
    };
}