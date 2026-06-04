export function init(containerElement, scoreCallback, sessionData) {
    containerElement.innerHTML = `
        <h1 style="font-size: 32px; margin-bottom: 20px;">Memory game</h1>
        <p>Work work work... ${sessionData.username}!</p>
    `;
}

export function cleanup(containerElement) {
    containerElement.innerHTML = '';
}