package com.barleybreak.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Иван on 04.06.2015.
 */
public class World {
    public Square square;
    public boolean gameOver = false;
    public int score = 0;
    int[][] field;
    List<Square> squares;

    public World() {
        square = new Square(0, 64, 3);
        squares = new ArrayList<>();
        generateList();
    }

    private void generateList() {
        int size = 64;
        int startX = 30;
        int startY = 100;

        generateField();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Square newSquare = new Square(i * size + startX, j * size + startY, field[i][j]);
                squares.add(newSquare);
            }
        }
    }

    private void generateField() {
        /**
         лгоритм я такой знаю:
         1. мысленно выстраиваем нашу доску в одну линию (пустая ячейка имеет номер 16)
         к примеру 1 5 2 4 3 (остальные отброшу для простоты)
         2. для каждого числа смотрим, сколько справа чисел, меньших его:
         для 1 — 0
         для 5 — 3
         для 2 — 0
         для 4 — 1
         для 3 — 1
         3. Суммируем получившиеся значения: 0+3+0+1+1 = 5
         4. Проверяем четность полученного значения, если четное, значит можно выиграть, иначе нет.
         Известные примеры: 1 2 3 … 14 15 16-
         здесь сумма равна нулю, т.к. все числа на своих местах.
         Ноль число четное, значит можно выиграть (что собственно уже сделано), и
         аналогично 1 2 3 … 13 15 14 16 — сумма перестановок равна 1, выиграть невозможно.
         */
        field = new int[4][4];
        for (int i = 0; i < 16; i++) {
            int x = (int) (Math.random() * 4);
            int y = (int) (Math.random() * 4);
            while (!(field[x][y] == 0)) {
                x = (int) (Math.random() * 4);
                y = (int) (Math.random() * 4);
            }
            field[x][y] = i;
        }

        List<Integer> row = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                row.add(field[i][j]);
            }
        }

        Map<Integer, Integer> checkMap = new HashMap<>();
        for(int i = 0; i < row.size(); i++) {
            int count = 0;
            int value = row.get(i);

            if (i + 1 < row.size()) {
                for (int j = i + 1; j < row.size(); j++) {
                    if (row.get(j) < value) {
                        count++;
                    }
                }
            }

            checkMap.put(value, count);
        }

        int sum = 0;
        for (Integer value : checkMap.keySet()) {
            sum = sum + value;
        }

        if (sum != 0 && sum % 2 == 1) {
            generateField();
        }
    }

    public Square getClickedSquare(int x, int y) {
        Square findSquare = null;

        StringBuilder builder = new StringBuilder();
        int size = 64;

        for (Square square : squares) {
            if (square.getX() <= x && square.getY() <= y && square.getX() + size >= x && square.getY() + size >= y) {
                findSquare = square;
                builder.append("Find element: " + String.valueOf(findSquare.getValue()) + " ");
            }
        }

        if (findSquare != null) {
            for (Square square : squares) {
                if (square.getX() + 64 == findSquare.getX() && square.getY() == findSquare.getY() && square.getValue() == 0) {
                    builder.append("lS " + String.valueOf(square.getValue()) + " ");
                    replacePos(square, findSquare);
                }

                if (square.getX() - 64 == findSquare.getX() && square.getY() == findSquare.getY() && square.getValue() == 0) {
                    builder.append("rS " + String.valueOf(square.getValue()) + " ");
                    replacePos(square, findSquare);
                }

                if (square.getX() == findSquare.getX() && square.getY() - 64 == findSquare.getY() && square.getValue() == 0) {
                    builder.append("bS " + String.valueOf(square.getValue()) + " ");
                    replacePos(square, findSquare);
                }

                if (square.getX() == findSquare.getX() && square.getY() + 64 == findSquare.getY() && square.getValue() == 0) {
                    builder.append("tS " + String.valueOf(square.getValue()) + " ");
                    replacePos(square, findSquare);
                }
            }
            //Log.d("myLogs", builder.toString());
        } else {
            //Log.d("myLogs", "empty");
        }

        return findSquare;
    }

    public void replacePos(Square zeroS, Square findS) {
        try {
            Square temp = findS.clone();
            findS.setValue(zeroS.getValue());
            zeroS.setValue(temp.getValue());
        } catch (CloneNotSupportedException e) {
        }
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int[][] getField() {
        return field;
    }

    public void setField(int[][] field) {
        this.field = field;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public void setSquares(List<Square> squares) {
        this.squares = squares;
    }

    public List<Integer> getNums() {
        List<Integer> nums = new ArrayList<Integer>();
        for(Square square:squares) {
            nums.add(square.getValue());
        }
        return nums;
    }
}
