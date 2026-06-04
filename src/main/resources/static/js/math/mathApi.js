import { apiClient } from '../core/apiClient.js';

const BASE_URL = '/api/v1/math';

export async function startMathGame(payload) {
    return apiClient(`${BASE_URL}/start`, {
        method: 'POST',
        body: JSON.stringify(payload)
    });
}

export async function fetchMathTask() {
    return apiClient(`${BASE_URL}/task`, { method: 'GET' });
}

export async function verifyMathTask(payload) {
    return apiClient(`${BASE_URL}/task`, {
        method: 'POST',
        body: JSON.stringify(payload)
    });
}