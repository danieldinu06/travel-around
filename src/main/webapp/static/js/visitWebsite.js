let websiteBtn = document.querySelector(".website-btn");
let url = websiteBtn.getAttribute('url');

websiteBtn.addEventListener('click', () => {
    window.open(url, '_blank').focus();
});