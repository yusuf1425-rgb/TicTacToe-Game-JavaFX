package ticTacToe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class TicTacToeApplication extends Application {

    private String currentPlayer = "X";
    private Button[][] board = new Button[3][3];

    public boolean hasWinner() {
        for (int row = 0; row < 3; row++) {
            if (!board[row][0].getText().equals(" ") && board[row][0].getText().equals(board[row][1].getText()) && board[row][0].getText().equals(board[row][2].getText())) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (!board[0][col].getText().equals(" ") && board[0][col].getText().equals(board[1][col].getText()) && board[0][col].getText().equals(board[2][col].getText())) {
                return true;
            }
        }

        // Check diagonals
        if (!board[0][0].getText().equals(" ")
                && board[0][0].getText().equals(board[1][1].getText())
                && board[0][0].getText().equals(board[2][2].getText())) {
            return true;
        }

        if (!board[0][2].getText().equals(" ")
                && board[0][2].getText().equals(board[1][1].getText())
                && board[0][2].getText().equals(board[2][0].getText())) {
            return true;
        }

        return false;
    }

    public void endBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setDisable(true);
            }
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane layout = new BorderPane();

        Label turnLabel = new Label("Turn: " + this.currentPlayer);
        layout.setTop(turnLabel);

        GridPane buttonsGrid = new GridPane();
        layout.setCenter(buttonsGrid);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button(" ");
                button.setFont(Font.font("Monospaced", 40));
                button.setMinSize(80, 80);
                board[row][col] = button;
                buttonsGrid.add(button, col, row);

                button.setOnAction((event) -> {
                    if (!button.getText().equals(" ")) {
                        return;
                    }
                    button.setText(currentPlayer);
                    if (currentPlayer.equals("X")) {
                        currentPlayer = "O";
                    } else {
                        currentPlayer = "X";
                    }
                    turnLabel.setText("Turn: " + currentPlayer);

                    if (hasWinner()) {
                        turnLabel.setText("The end!");
                        endBoard();
                    }
                });
            }
        }

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }

    public static void main(String[] args) {
        launch(TicTacToeApplication.class
        );
    }

}
