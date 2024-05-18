import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class App extends Application {
    static int speed = 5;
    static int foodcolor = 0;
    static int width = 20;
    static int height = 20;
    static int foodX = 0;
    static int foodY = 0;
    static int cornersize = 25;
    static List<Corner> snake = new ArrayList<>();
    static Dir direction = Dir.left;
    static boolean gameOver = false;
    static Random rand = new Random();

    public enum Dir {
        left, right, up, down
    }

    public static class Corner {
        int x;
        int y;

        public Corner(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void start(Stage primaryStage) {
        try {
            newFood();

            VBox root = new VBox();
            Canvas c = new Canvas(width * cornersize, height * cornersize);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

            new AnimationTimer() {
                long lastTick = 0;

                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        tick(gc);
                        return;
                    }

                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        tick(gc);
                    }
                }
            }.start();

            Scene scene = new Scene(root, width * cornersize, height * cornersize);
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                switch (key.getCode()) {
                    case W, UP -> {
                        if (direction != Dir.down)
                            direction = Dir.up;
                    }
                    case A, LEFT -> {
                        if (direction != Dir.right)
                            direction = Dir.left;
                    }
                    case S, DOWN -> {
                        if (direction != Dir.up)
                            direction = Dir.down;
                    }
                    case D, RIGHT -> {
                        if (direction != Dir.left)
                            direction = Dir.right;
                    }
                    default -> throw new IllegalArgumentException("Unexpected value: " + key.getCode());
                }
            });

            snake.add(new Corner(width / 2, height / 2));
            snake.add(new Corner(width / 2, height / 2));
            snake.add(new Corner(width / 2, height / 2));

            primaryStage.setScene(scene);
            primaryStage.setTitle("SNAKE GAME");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tick(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
            return;
        }

        moveSnake();

        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            snake.add(new Corner(-1, -1));
            newFood();
        }

        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                gameOver = true;
            }
        }

        draw(gc);
    }

    private static void moveSnake() {
        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        switch (direction) {
            case up -> {
                snake.get(0).y--;
                if (snake.get(0).y < 0)
                    gameOver = true;
            }
            case down -> {
                snake.get(0).y++;
                if (snake.get(0).y >= height)
                    gameOver = true;
            }
            case left -> {
                snake.get(0).x--;
                if (snake.get(0).x < 0)
                    gameOver = true;
            }
            case right -> {
                snake.get(0).x++;
                if (snake.get(0).x >= width)
                    gameOver = true;
            }
        }
    }

    private static void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * cornersize, height * cornersize);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + (speed - 6), 10, 30);

        Color cc = switch (foodcolor) {
            case 0 -> Color.PURPLE;
            case 1 -> Color.LIGHTBLUE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.PINK;
            case 4 -> Color.ORANGE;
            default -> Color.WHITE;
        };
        gc.setFill(cc);
        gc.fillOval(foodX * cornersize, foodY * cornersize, cornersize, cornersize);

        for (Corner c : snake) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 1, cornersize - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 2, cornersize - 2);
        }
    }

    public static void newFood() {
        while (true) {
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);

            boolean onSnake = false;
            for (Corner c : snake) {
                if (c.x == foodX && c.y == foodY) {
                    onSnake = true;
                    break;
                }
            }

            if (!onSnake) {
                foodcolor = rand.nextInt(5);
                speed++;
                break;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}