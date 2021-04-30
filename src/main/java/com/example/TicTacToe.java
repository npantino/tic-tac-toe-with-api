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
    public String moveString;
    public int xCoordinate = 0;
    public int yCoordinate = 0;
    public int gameId = 0;

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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}

@Controller("/api/tic-tac-toe")
public class TicTacToe {

    private HashMap<Integer, String[][]> games = new HashMap<>();
    int gameCount = 0;

    private String[][] board = {{"", "", ""},
                              {"", "", ""},
                              {"", "", ""}};

    @Post
    public int createGame() {
        gameCount++;
        String gameBoard[][] = board;
        games.put(gameCount, gameBoard);
        return gameCount;
    }

    @Get
    public String[][] boardState(@Body Move move) {
        return games.get(move.gameId);
    }



    @Post("/game")
    public String[][] updateBoard(@Body Move move) {
        if (!games.get(move.gameId)[move.xCoordinate][move.yCoordinate].isEmpty()) {
            throw new RuntimeException("Move not valid");
        }

        games.get(move.gameId)[move.xCoordinate][move.yCoordinate] = move.moveString;
        return games.get(move.gameId);
    }

}
