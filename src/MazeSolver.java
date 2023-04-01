/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;
    private ArrayList<MazeCell> solution;
    private Stack<MazeCell> hasMoreNeighbors;
    private Queue<MazeCell> moreNeighbors;

    public MazeSolver() {
        solution = new ArrayList<MazeCell>();
        hasMoreNeighbors = new Stack<MazeCell>();
        this.maze = null;
        moreNeighbors = new LinkedList<MazeCell>();
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        Stack<MazeCell> stack = new Stack<MazeCell>();
        MazeCell cell = maze.getEndCell();
        stack.push(cell);
        // traverses through parents of each cell and adds to stack
        while(!maze.getStartCell().equals(cell)) {
            cell = cell.getParent();
            stack.push(cell);
        }
        int size = stack.size();
        // reverses order of cells in stack into solution and returns
        for (int i = 0; i < size; i++) {
            solution.add(stack.pop());
        }
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell firstCell = maze.getStartCell();
        // calls recursive depth search method
        depth(firstCell);
        // returns solution after depth search sets parents to correct cells
        return getSolution();
    }
    // recursive method implementing depth first search
    public void depth(MazeCell cell) {
        // sets cell as explored
        cell.setExplored(true);
        // creates new cell
        MazeCell newCell = cell;
        // the variable count is here to distinguish whether or not the next cell has already been found
        int count = 0;
        // ends method if last cell has been reached
        if (maze.getEndCell().equals(cell)) {
            return;
        }
        // checks north cell
        if (maze.isValidCell(cell.getRow() + 1, cell.getCol())) {
            // if north cell is valid it is set as the next cell and set as explored and is set as a parent
            newCell = maze.getCell(cell.getRow() + 1, cell.getCol());
            newCell.setExplored(true);
            newCell.setParent(cell);
            count++;
        }
        // checks east cell
        if (maze.isValidCell(cell.getRow(), cell.getCol() + 1)) {
            // if a new valid cell has not been found
            if (count == 0) {
                // cell is set as the next cell and set as explored and is set as a parent
                newCell = maze.getCell(cell.getRow(), cell.getCol() + 1);
                newCell.setExplored(true);
                newCell.setParent(cell);
            }
            // if a new valid cell has been found
            else {
                // cell is added to list of places needed to visit in a stack
                MazeCell nCell = maze.getCell(cell.getRow(), cell.getCol() + 1);
                hasMoreNeighbors.add(nCell);
            }
            count++;
        }
        // checks south cell
        if (maze.isValidCell(cell.getRow() - 1, cell.getCol())) {
            // if a new valid cell has not been found
            if (count == 0) {
                // cell is set as the next cell and set as explored and is set as a parent
                newCell = maze.getCell(cell.getRow() - 1, cell.getCol());
                newCell.setExplored(true);
                newCell.setParent(cell);
            }
            // if a new valid cell has been found
            else {
                // cell is added to list of places needed to visit in a stack
                MazeCell nCell = maze.getCell(cell.getRow() - 1, cell.getCol());
                hasMoreNeighbors.add(nCell);
            }
            count++;
        }
        // checks west cell
        if (maze.isValidCell(cell.getRow(), cell.getCol() - 1)) {
            // if a new valid cell has not been found
            if (count == 0) {
                // cell is set as the next cell and set as explored and is set as a parent
                newCell = maze.getCell(cell.getRow(), cell.getCol() - 1);
                newCell.setExplored(true);
                newCell.setParent(cell);
            }
            // if a new valid cell has been found
            else {
                // cell is added to list of places needed to visit in a stack
                MazeCell nCell = maze.getCell(cell.getRow(), cell.getCol() - 1);
                hasMoreNeighbors.add(nCell);
            }
            count++;
        }
        // if a new valid cell has not been found
        if (count == 0) {
            // depth is recalled with most recently added neighbor from stack
            depth(hasMoreNeighbors.pop());
        }
        // if a new valid cell has been found
        else {
            // depth is recalled with the new cell
            depth(maze.getCell(newCell.getRow(), newCell.getCol()));
        }
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell firstCell = maze.getStartCell();
        // calls recursive breadth search method
        breadth(firstCell);
        // returns solution after breadth search sets parents to correct cells
        return getSolution();
    }
    // recursive method implementing breadth first search
    public void breadth(MazeCell cell) {
        // sets cell as explored
        cell.setExplored(true);
        // creates new cell
        MazeCell newCell = cell;
        // the variable count is here to distinguish whether or not the next cell has already been found
        int count = 0;
        // ends method if last cell has been reached
        if (maze.getEndCell().equals(cell)) {
            return;
        }
        // checks north cell
        if (maze.isValidCell(cell.getRow() - 1, cell.getCol())) {
            // if north cell is valid it is set as the next cell and set as explored and is set as a parent
            newCell = maze.getCell(cell.getRow() - 1, cell.getCol());
            newCell.setExplored(true);
            newCell.setParent(cell);
            count++;
        }
        // checks east cell
        if (maze.isValidCell(cell.getRow(), cell.getCol() + 1)) {
            // if a new valid cell has not been found
            if (count == 0) {
                // cell is set as the next cell and set as explored and is set as a parent
                newCell = maze.getCell(cell.getRow(), cell.getCol() + 1);
                newCell.setExplored(true);
                newCell.setParent(cell);
            }
            // if a new valid cell has been found
            else {
                // cell is added to list of places needed to visit in a queue
                MazeCell nCell = maze.getCell(cell.getRow(), cell.getCol() + 1);
                moreNeighbors.add(nCell);
            }
            count++;
        }
        // checks south cell
        if (maze.isValidCell(cell.getRow() + 1, cell.getCol())) {
            // if a new valid cell has not been found
            if (count == 0) {
                // cell is set as the next cell and set as explored and is set as a parent
                newCell = maze.getCell(cell.getRow() + 1, cell.getCol());
                newCell.setExplored(true);
                newCell.setParent(cell);
            }
            // if a new valid cell has been found
            else {
                // cell is added to list of places needed to visit in a queue
                MazeCell nCell = maze.getCell(cell.getRow() + 1, cell.getCol());
                moreNeighbors.add(nCell);
            }
            count++;
        }
        // checks west cell
        if (maze.isValidCell(cell.getRow(), cell.getCol() - 1)) {
            // if a new valid cell has not been found
            if (count == 0) {
                // cell is set as the next cell and set as explored and is set as a parent
                newCell = maze.getCell(cell.getRow(), cell.getCol() - 1);
                newCell.setExplored(true);
                newCell.setParent(cell);
            }
            // if a new valid cell has been found
            else {
                // cell is added to list of places needed to visit in a queue
                MazeCell nCell = maze.getCell(cell.getRow(), cell.getCol() - 1);
                moreNeighbors.add(nCell);
            }
            count++;
        }
        // if a new valid cell has not been found
        if (count == 0) {
            // breadth is recalled with cell from queue
            breadth(moreNeighbors.remove());
        }
        // if a new valid cell has been found
        else {
            // breadth is recalled with new cell
            breadth(maze.getCell(newCell.getRow(), newCell.getCol()));
        }
    }
    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
