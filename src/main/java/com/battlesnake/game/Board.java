package com.battlesnake.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import com.battlesnake.game.math.Point;
import com.battlesnake.game.snake.Snake;

@Log4j2
@Accessors(fluent = true)
public class Board {

    public static enum Tile {
        // Empty spot
        EMPTY,
        // Body of any snake
        WALL,
        // Head of your snake
        ME,
        // Head of others snakes
        HEADS,
        // Food
        FOOD,
        // Fake walls placed where longer snakes
        // can potentially move
        FAKE_WALL,
        // Tail of any snake
        TAIL;

        public String toString() {
            return Integer.valueOf(this.ordinal()).toString();
        }
    }

    private transient Tile[][] board;

    private List<Point> food;

    @Getter private int height;
    private List<Snake> snakes;

    @Getter private int width;
    @Getter private transient Snake you;

    @Getter private transient int turn;
    private transient String gameId;

    public boolean exists(Point point) {
        if (point.x() < 0) return false;
        if (point.y() < 0) return false;
        if (point.x() > width() - 1) return false;
        if (point.y() > height() - 1) return false;
        return true;
    }

    private List<Point> findAdjacent(Point point) {
        return new ArrayList<>(Move.adjacent(point).values());
    }

    public List<Move> getPossibleDangerousMoves(Point point) {
        return getPossibleMoves(point, false);
    }

    public List<Move> getPossibleMoves(Point point) {
        return getPossibleMoves(point, true);
    }

    private List<Move> getPossibleMoves(Point point, boolean excludeDanger) {
        ArrayList<Move> moves = new ArrayList<>();
        for (Entry<Move, Point> move: Move.adjacent(point).entrySet()) {
            if (movable(move.getValue(), excludeDanger)) {
                moves.add(move.getKey());
            }
        }
        return moves;
    }

    private boolean movable(Point point, boolean excludeDanger) {
        return !isFilled(point)
            && (excludeDanger ? !isDangerousSpotFilled(point) : true);
    }

    public void init(Game state) {
        this.you = state.you();
        this.turn = state.turn();
        this.gameId = state.id();

        removeDead();
        toGrid();
    }

    public boolean isDangerousSpotFilled(Point point) {
        if (!exists(point)) return false;
        return board[point.x()][point.y()] == Tile.FAKE_WALL
            || board[point.x()][point.y()] == Tile.TAIL;
    }

    public boolean isFood(Point point) {
        if (!exists(point)) return false;
        return board[point.x()][point.y()] == Tile.FOOD;
    }

    private boolean isFilled(Point point) {
        if (!exists(point)) return true;
        return board[point.x()][point.y()] != Tile.EMPTY
            && board[point.x()][point.y()] != Tile.FOOD
            && board[point.x()][point.y()] != Tile.FAKE_WALL
            && board[point.x()][point.y()] != Tile.TAIL;
    }

    public int longestSnakeLength() {
        int max = Integer.MIN_VALUE;
        for (Snake snake : snakes) {
            if (!snake.equals(you()) && snake.length() > max) {
                max = snake.length();
            }
        }
        return max;
    }

    public Move move() {
        return you().move(this);
    }

    public void print() {
        log.info(toString());
    }

    private void removeDead() {
        for (int i = 0; i < snakes.size(); i++) {
            if (snakes.get(i).isDead()) {
                snakes.remove(i);
                i--;
            }
        }
    }

    private void toGrid() {
        this.board = new Tile[width()][height()];
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                board[x][y] = Tile.EMPTY;
            }
        }

        for (Point snack : food) {
            board[snack.x()][snack.y()] = Tile.FOOD;
        }

        for (Snake snake : snakes) {
            List<Point> body = snake.body();
            Point head = body.get(0);
            for (int i = 0; i < body.size(); i++) {
                if ((i == body.size() - 1)
                    && body.size() > 1
                    && !snake.justAte()) {
                    board[body.get(i).x()][body.get(i).y()] = Tile.TAIL;
                }
                else {
                    board[body.get(i).x()][body.get(i).y()] = Tile.WALL;
                }
            }

            if (snake.equals(you())) {
                board[head.x()][head.y()] = Tile.ME;
            }
            else {
                board[head.x()][head.y()] = Tile.HEADS;

                if (!you().longerThan(snake)) {
                    List<Point> around = findAdjacent(head);
                    for (Point point : around) {
                        if (exists(point)) {
                            if (board[point.x()][point.y()] == Tile.EMPTY
                             || board[point.x()][point.y()] == Tile.FOOD) {
                                board[point.x()][point.y()] = Tile.FAKE_WALL;
                            }
                        }
                    }
                }
            }
        }
    }

    public String toString() {
        String out = String.format("Turn %s\n", turn);
        int[] padding = new int[width()];
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                int length = String.valueOf(board[x][y]).length();
                if (length > padding[x]) padding[x] = length;
            }
        }
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                String value = String.valueOf(board[x][y]);
                int diff = padding[x] - value.length();
                if (diff > 0) {
                    for (int i = 0; i < diff; i ++) {
                        out += " ";
                    }
                }
                out += value;
                out += " ";
            }
            out += "\n";
        }
        return out;
    }
}
