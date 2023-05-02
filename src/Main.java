import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //multi-dimensional array to store the values as a table
        int[][] sudoku = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
//        System.out.println(isValidGrid(sudoku));
//        System.out.println((findEmptyCell(sudoku)));
        System.out.println(solveSudoku(sudoku));
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                System.out.print("value "+sudoku[i][j]+" i"+i+" j"+j+"   ");
            }
            System.out.println();
            }

    }
    public static boolean solveSudoku(int[][] grid){
        Set<String> empty = findEmptyCell(grid);
        if(empty.isEmpty()){
            return true;
        }
//        System.out.println(findEmptyCell(grid));
        int row = -1;
        int col = -1;
        for (String coord : empty) {
            row = Character.getNumericValue(coord.charAt(1));//row num 2nd place
            col = Character.getNumericValue(coord.charAt(3));//col number at 4th
            break;
        }
        for (int num = 1; num <= 9; num++) {
            if (isValidGrid(grid)) {
                grid[row][col] = num;
                if (solveSudoku(grid)) {
                    return true;
                }
                grid[row][col] = 0;
            }
        }
        return false;
    }

    // check if the grid is valid. No duplicates in row,columns, box.
    public static boolean isValidGrid(int[][] grid) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int num = grid[i][j];
                if (num > 0 && num < 10) {
                    String row = "row" + i + num;
                    String col = "col" + j + num;
                    String box = "box" + (i / 3) * 3 + (j / 3) + num;
                    if (!set.add(row) || !set.add(col) || !set.add(box)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static Set<String> findEmptyCell(int[][] grid) {
        Set<String> coordinates = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int num = grid[i][j];
                if (num == 0) {
//                    System.out.println("i" + i + "j" + j);
                    coordinates.add("i" + i + "j" + j);
//                    return "i" + i + "j" + j;
                }
            }

        }
        return coordinates;
    }

}