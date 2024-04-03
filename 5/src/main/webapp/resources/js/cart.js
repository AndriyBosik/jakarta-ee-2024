const calculatePayment = () => {
    const totals = document.querySelectorAll(".food-total .value");
    let sum = 0;
    for (let i = 0; i < totals.length; i++) {
        const item = totals[i];
        sum += item.textContent * 1;
    }
    document.querySelector(".to-pay").textContent = "" + sum.toFixed(2);
}

const refreshTotal = (dataTarget, quantity) => {
    const price = document.querySelector(`[target='${dataTarget}-price']`).getAttribute("value") * 1;
    const total = price * quantity;
    document.querySelector(`[target='${dataTarget}-total'] .value`).textContent = total.toFixed(2)
    calculatePayment();
}

const handleIncreaseClick = event => {
    const dataTarget = event.target.getAttribute("data-target");
    const currentQuantityElement = document.querySelector("[target='" + dataTarget + "']");
    const currentQuantity = currentQuantityElement.textContent * 1;
    const newQuantity = currentQuantity + 1;
    currentQuantityElement.textContent = newQuantity + "";
    document.querySelector("[target='" + dataTarget + "-quantity-value']").value = newQuantity + "";
    refreshTotal(dataTarget, newQuantity);
}

const handleDecreaseClick = event => {
    const dataTarget = event.target.getAttribute("data-target");
    const currentQuantityElement = document.querySelector("[target='" + dataTarget + "']");
    const currentQuantity = currentQuantityElement.textContent * 1;
    const newQuantity = currentQuantity - 1;
    if (newQuantity >= 0) {
        currentQuantityElement.textContent = newQuantity + "";
        document.querySelector("[target='" + dataTarget + "-quantity-value']").value = newQuantity + "";
        refreshTotal(dataTarget, newQuantity);
    }
}

document.addEventListener("DOMContentLoaded", () => {
    calculatePayment();
})

document.querySelectorAll(".quantity-control").forEach(control => {
    if (control.classList.contains("increase")) {
        control.addEventListener("click", handleIncreaseClick);
    } else if (control.classList.contains("decrease")) {
        control.addEventListener("click", handleDecreaseClick);
    }
});
