async function loadData() {
    try {
        // 1. Make the request to your controller endpoint
        const response = await fetch('/api/message');

        // 2. Parse the JSON body
        const data = await response.json();

        // 3. Update your HTML with the data
        document.getElementById("output").innerText = data.content;

    } catch (error) {
        console.error("Error fetching data:", error);
    }
}

// Call the function when the page loads
window.onload = loadData;