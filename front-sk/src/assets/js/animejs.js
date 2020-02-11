import anime from 'animejs/lib/anime.es.js';

function normal(element) {
    anime({
        targets: element,
        translateX: 250,
        easing: 'easeInOutSine',
        duration: 2500,
        loop: true,
      });
}
function reverse(element) {
    anime({
      targets: element,
      translateX: 250,
      direction: 'reverse',
      easing: 'easeInOutSine',
      duration: 2500,
      loop: true,
    });
  }
function alternate(element) {
    anime({
        targets: element,
        translateX: 250,
        direction: 'alternate',
        easing: 'easeInOutSine',
        duration: 2500,
        loop: true,
      });
}
function jump(element) {
  anime({
    targets: element,
    translateY: 20,
    direction: 'alternate',
    loop: true,
    easing: 'linear'
  });
}
function ratings(element) {
  anime({
    targets: element,
  translateX: [
    { value: 250, duration: 1000, delay: 500 },
    { value: 0, duration: 1000, delay: 500 }
  ],
  translateY: [
    { value: -40, duration: 500 },
    { value: 40, duration: 500, delay: 1000 },
    { value: 0, duration: 500, delay: 1000 }
  ],
  scaleX: [
    { value: 4, duration: 100, delay: 500, easing: 'easeOutExpo' },
    { value: 1, duration: 900 },
    { value: 4, duration: 100, delay: 500, easing: 'easeOutExpo' },
    { value: 1, duration: 900 }
  ],
  scaleY: [
    { value: [1.75, 1], duration: 500 },
    { value: 2, duration: 50, delay: 1000, easing: 'easeOutExpo' },
    { value: 1, duration: 450 },
    { value: 1.75, duration: 50, delay: 1000, easing: 'easeOutExpo' },
    { value: 1, duration: 450 }
  ],
  easing: 'easeOutElastic(1, .8)',
  loop: true
  });
}



export {normal, reverse, alternate, jump, ratings}