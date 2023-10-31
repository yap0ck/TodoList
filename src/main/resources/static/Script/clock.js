function updateClock() {
    const now = new Date();
    const hour = now.getHours();
    const minute = now.getMinutes();
    const second = now.getSeconds();

    const hourHand = document.querySelector('.hour-hand');
    const minuteHand = document.querySelector('.minute-hand');
    const secondHand = document.querySelector('.second-hand');

    const hourDeg = (360 / 12) * hour + (360 / 12) * (minute / 60);
    const minuteDeg = (360 / 60) * minute + (360 / 60) * (second / 60);
    const secondDeg = (360 / 60) * second;

    hourHand.style.transform = `rotate(${hourDeg}deg)`;
    minuteHand.style.transform = `rotate(${minuteDeg}deg)`;
    secondHand.style.transform = `rotate(${secondDeg}deg`;
}

setInterval(updateClock, 1000);
updateClock();