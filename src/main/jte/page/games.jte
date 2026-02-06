@param int highscore

@template.layouts.main(
title = "Game",
content = @`
    <!-- Game Controls and Stats -->
    <div>
        <button id="start-btn" onclick="startGame()">Start Game</button>
        <p>Time Left: <span id="timer">10</span>s</p>
        <!-- Score container is hidden by default -->
        <div id="final-score-container" style="display: none;">
            <p>Final Score: <span id="score">0</span></p>
            <form hx-post="/games/highscore">
                <input id="score-field" type="hidden" name="score" value="0">
                <input type="submit" value="Submit Score">
            </form>
        </div>
        <div>High Score: ${highscore}</div>
    </div>

    <!-- Game Area: Boxes will appear inside here -->
    <div id="game-container"
         style="position: relative; width: 100%; height: 400px; border: 1px solid black; margin-top: 10px;">
    </div>

    <script>
        // Game Configuration
        const GAME_DURATION = 5; // seconds
        const BOX_VISIBLE_TIME = 800; // milliseconds

        // State variables
        let score = 0;
        let timeLeft = GAME_DURATION;
        let gameActive = false;
        let gameTimerInterval;

        // DOM Elements
        const startBtn = document.getElementById('start-btn');
        const timerDisplay = document.getElementById('timer');
        const scoreDisplay = document.getElementById('score');
        const scoreField = document.getElementById('score-field');
        const gameContainer = document.getElementById('game-container');
        const finalScoreContainer = document.getElementById('final-score-container');

        /**
         * Starts the game logic, resets state, and begins the countdown.
         */
        function startGame() {
            if (gameActive) return;

            // Reset state
            score = 0;
            timeLeft = GAME_DURATION;
            gameActive = true;

            // UI Reset
            scoreDisplay.textContent = score;
            scoreField.value = score;
            timerDisplay.textContent = timeLeft;
            startBtn.disabled = true;
            finalScoreContainer.style.display = 'none'; // Hide score while playing
            gameContainer.innerHTML = '';

            // Start the 10s countdown
            gameTimerInterval = setInterval(() => {
                timeLeft--;
                timerDisplay.textContent = timeLeft;

                if (timeLeft <= 0) {
                    endGame();
                }
            }, 1000);

            // Start spawning boxes
            spawnBox();
        }

        /**
         * Creates a box at a random position and handles the disappearance.
         */
        function spawnBox() {
            if (!gameActive) return;

            const box = document.createElement('div');

            const size = 100;
            box.style.width = size + 'px';
            box.style.height = size + 'px';
            box.style.backgroundColor = 'red';
            box.style.position = 'absolute';
            box.style.cursor = 'pointer';

            const maxX = gameContainer.clientWidth - size;
            const maxY = gameContainer.clientHeight - size;
            const randomX = Math.floor(Math.random() * maxX);
            const randomY = Math.floor(Math.random() * maxY);

            box.style.left = randomX + 'px';
            box.style.top = randomY + 'px';

            box.onclick = function () {
                if (gameActive) {
                    score++;
                    // Note: No UI update here as per request
                    gameContainer.removeChild(box);
                }
            };

            gameContainer.appendChild(box);

            setTimeout(() => {
                if (box.parentNode === gameContainer) {
                    gameContainer.removeChild(box);
                }

                if (gameActive) {
                    spawnBox();
                }
            }, BOX_VISIBLE_TIME);
        }

        /**
         * Cleans up intervals and displays final score and submit button.
         */
        function endGame() {
            gameActive = false;
            clearInterval(gameTimerInterval);
            startBtn.disabled = false;

            gameContainer.innerHTML = '';

            // Show the final score and the submit button
            scoreDisplay.textContent = score;
            scoreField.value = score;
            finalScoreContainer.style.display = 'block';

            const gameOverText = document.createElement('h2');
            gameOverText.textContent = "Game Over!";
            gameContainer.appendChild(gameOverText);
        }
    </script>
`)
