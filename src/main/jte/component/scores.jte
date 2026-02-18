@param int highscore

<div id="scores">
    <button id="start-btn" onclick="startGame()">Start Game</button>
    <p>Time Left: <span id="timer">10</span>s</p>
    <!-- Score container is hidden by default -->
    <div id="final-score-container" style="display: none;">
        <p>Final Score: <span id="score">0</span></p>
        <form hx-post="/games/highscore"
              hx-target="#scores">
            <input id="score-field" type="hidden" name="score" value="0">
            <input type="submit" value="Submit Score">
        </form>
    </div>
    <div>High Score: ${highscore}</div>
</div>