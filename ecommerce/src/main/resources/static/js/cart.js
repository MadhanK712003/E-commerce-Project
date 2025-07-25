
window.addEventListener("DOMContentLoaded", function () {
    const addedAlert = document.getElementById('itemAddedAlert');
    if (addedAlert) {
        const added = new bootstrap.Toast(addedAlert, { delay: 3000 });
        added.show();
    }
});

window.addEventListener("DOMContentLoaded", function () {
    const removedAlert = document.getElementById('itemRemovedAlert');
    if (removedAlert) {
        const removed = new bootstrap.Toast(removedAlert, { delay: 3000 });
        removed.show();
    }
});