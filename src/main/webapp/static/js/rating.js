const starsTotal = 5;

document.addEventListener('DOMContentLoaded', getRatings);

let ratings = document.querySelectorAll('.number-rating');

function getRatings() {
    ratings.forEach((rating, index) => {
        let starPercentage = (rating.innerHTML / starsTotal * 100);
        const starPercentageRounded = `${Math.round(starPercentage / 10) * 10}%`;

        document.querySelector(`.stars-inner-${index + 1}`).style.width = starPercentageRounded;
    })
}