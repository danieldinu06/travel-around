const starsTotal = 5;

document.addEventListener('DOMContentLoaded', getRatings);

let ratings = document.querySelectorAll('.number-rating');

function getRatings() {
    ratings.forEach((rating) => {
        let starPercentage = (rating.innerHTML / starsTotal * 100);
        const starPercentageRounded = `${Math.round(starPercentage / 10) * 10}%`;

        console.log(starPercentageRounded);

        document.querySelector('.stars-inner').style.width = starPercentageRounded;
    })
}