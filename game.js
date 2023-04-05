buttonColor = ["red", "blue", "green", "yellow"];
gamePattern = [];
randomChosenColor = buttonColor[abc];
//Step 6: added items to gamepattern
gamePattern.push(randomChosenColor);
function nextSequence() {
  var randomNum = Math.random();
  var abc = Math.floor(randomNum * 4);
}
