const BASE_URL = '/api/v1';

export async function fetchMathTask() {
    const response = await fetch(`${BASE_URL}/math/task`);
    if (!response.ok) throw new Error('Error fetching the task');
    return response.json();
}

export async function verifyMathTask(payload) {
    const response = await fetch(`${BASE_URL}/math/task`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });
    if (!response.ok) throw new Error('Verification error');
    return response.json();
}


export async function startGameSession(payload) {
    const response = await fetch(`${BASE_URL}/math/start`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });
    if (!response.ok) throw new Error('Starting session error');
    return response.json();
}