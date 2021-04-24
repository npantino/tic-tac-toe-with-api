package com.example;

import io.micronaut.http.annotation.*;
import java.util.HashMap;
/*
@Controller("/api")
public class ApiController {
    ApiResponse data = new ApiResponse();
    @Get
    public ApiResponse getHello() {
        return new ApiResponse();
    }

    @Post
    public void updateData(@Body ApiResponse apiResponse) {
        data = apiResponse;
    }

    @Get("login:{username}")
    public String getCredentials(@Body ApiResponse apiResponse, String username) {
        data = apiResponse;
        return username; + "/n" + "Password:  " + password;
    }

    @Get("/npantino")
    public String username(String username) {
        return username;
    }

    @Get("/path/{id}")
    public int pathParam(int id) {
        return id;
    }
}
*/

class Move {
    public String moveString = "";
    public int xCoordinate = 0;
    public int yCoordinate = 0;
    public int gameCount = 0;

    public String getMoveString() {
        return moveString;
    }

    public void setMoveString(String moveString) {
        this.moveString = moveString;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getGameCount() {
        return gameCount;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }
}

@Controller("/api/tic-tac-toe")
public class TicTacToe {

    private HashMap<Integer, String[][]> games = new HashMap<>();

    private String[][] board = {{"", "", ""},
                              {"", "", ""},
                              {"", "", ""}};

    @Get
    public String[][] getBoard() {
        return board;
    }

    @Post
    public int createGame(@Body Move move) {
        move.gameCount++;
        String gameBoard[][] = board;
        games.put(move.gameCount, gameBoard);
        return move.gameCount;
    }

    @Get
    public String[][] boardState(int gameId) {
        return games.get(gameId);
    }



    @Post
    public String[][] updateBoard(@Body Move move, int gameId) {
        if (!games.get(gameId)[move.xCoordinate][move.yCoordinate].isEmpty()) {
            throw new RuntimeException("Move not valid");
        }

        games.get(gameId)[move.xCoordinate][move.yCoordinate] = move.moveString;
        return games.get(gameId);
    }


}
