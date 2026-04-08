

async function loadData() {
    try {
        const response = await fetch('/api/v1/math/task');
        const data = await response.json();
        document.getElementById("firstNumber").innerText = data["firstNumber"];
        document.getElementById("secondNumber").innerText = data["secondNumber"];
        console.log(data);
    } catch (error) {
        console.error("Error fetching data:", error);
    }
}
