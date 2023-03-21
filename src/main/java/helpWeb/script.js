const questionContainer = document.querySelectorAll(".question-container");

questionContainer.forEach((el) => {
  el.addEventListener("click", function (e) {
    el.classList.toggle("active");
  });
});
