package com.battlesnake.game.snake;

import java.util.List;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import com.battlesnake.game.Board;
import com.battlesnake.game.Move;
import com.battlesnake.game.math.Point;

@Log4j2
@Accessors(fluent = true)
public class Snake {
    private static final int MAX_HEALTH = 100;
    private static final int MIN_HEALTH = 0;

    @Getter private List<Point> body;
    @Getter private int health;
    @Getter private String id;
    @Getter private String name;

    private Snake() {
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Snake) return equals((Snake) other);
        return false;
    }

    public boolean equals(Snake other) {
        return id().equals(other.id());
    }

    public Point head() {
        return body.get(0);
    }

    public boolean isDead() {
        return health() < MIN_HEALTH;
    }

    public boolean isLongest(Board board) {
        return longerThan(board.longestSnakeLength());
    }

    public boolean justAte() {
        return health() == MAX_HEALTH;
    }

    public int length() {
        return body.size();
    }

    private boolean longerThan(int length) {
        return length() > length;
    }

    public boolean longerThan(Snake snake) {
        return longerThan(snake.length());
    }

    public Move move(Board board) {
        // Move to right by default
        Move move = Move.RIGHT;
        // Get all possible safe moves
        List<Move> moves = board.getPossibleMoves(head());
        // If not safe moves exist. try to find any possible move
        if (moves.isEmpty()) {
            moves = board.getPossibleDangerousMoves(head());
        }
        // If some move exists, take it
        if (!moves.isEmpty()) move = moves.get(0);
        log.info("Moving {}", move);
        return move;
    }
}
