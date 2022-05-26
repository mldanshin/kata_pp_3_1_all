"use strict";

let container = document.getElementById("container");
if (container) {
    container.addEventListener("click", handleEventsContainer);
}

document.addEventListener("submit", handleEventsForm);

let modal = null;

function handleEventsContainer(event) {
    if (!event) {
        return;
    }

    let element = event.target;

    switch (element.id) {
        case "list":
            list(element.dataset.url);
            event.preventDefault();
            break;
        case "create":
            create(element.dataset.url);
            event.preventDefault();
            break;
        case "edit":
            edit(element.dataset.url);
            event.preventDefault();
            break;
        case "link-delete":
            getFormDelete(element.dataset.url);
            event.preventDefault();
            break;
    }
}

function handleEventsForm(event) {
    let form = document.getElementById("form");
    if (!form) {
        return;
    }

    switch (form.dataset.mode) {
        case "create":
            store(form);
            event.preventDefault();
            break;
        case "edit":
            update(form);
            event.preventDefault();
            break;
        case "delete":
            deleteUser(form);
            event.preventDefault();
            break;
    }
}

async function list(url) {
    let response = await fetch(url);
    if (response.ok) {
        container.innerHTML = await response.text();
    }
}

async function create(url) {
    let response = await fetch(url);
    if (response.ok) {
        container.innerHTML = await response.text();
    }
}

async function edit(url) {
    let response = await fetch(url);
    if (response.ok) {
        document.getElementById('container-modal').innerHTML = await response.text();

        modal = new bootstrap.Modal(document.getElementById('modal'), null);
        modal.show();
    }
}

async function getFormDelete(url) {
    let response = await fetch(url);
    if (response.ok) {
        document.getElementById('container-modal').innerHTML = await response.text();

        modal = new bootstrap.Modal(document.getElementById('modal'), null);
        modal.show();
    }
}

async function store(form) {
    let response = await fetch(form.action, {method: "POST", body: new FormData(form)});
    if (response.ok) {
        list(document.getElementById("list").dataset.url);
    }
}

async function update(form) {
    let response = await fetch(form.action, {method: "PUT", body: new FormData(form)});
    if (response.ok) {
        list(document.getElementById("list").dataset.url);
        closeModal();
    }
}

async function deleteUser(form) {
    let response = await fetch(form.action, {method: "DELETE", body: new FormData(form)});
    if (response.ok) {
        list(document.getElementById("list").dataset.url);
        closeModal();
    }
}

function closeModal() {
    if (modal) {
        modal.hide();
    }

    document.getElementById('container-modal').innerHTML = "";
}
