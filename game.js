var buttonColor = ["red", "blue", "green", "yellow"];
var gamePattern = [];

function nextSequence() {
  var randomNum = Math.random();
  var abc = Math.floor(randomNum * 4);
  var randomChosenColor = buttonColor[abc];
  gamePattern.push(randomChosenColor);

  $("#" + randomChosenColor)
    .fadeIn(100)
    .fadeOut(100)
    .fadeIn(100);

  var audio = new Audio("sounds/" + randomChosenColor + ".mp3");
  audio.play();
}
$(".btn").click(function () {
  console.log(this.button);
});
