package game;

import piece.Guard;
import piece.Leader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameBoard extends JFrame implements MouseListener {


    public final int tileSideCount = 5;


    private Object [][] pieceCollection;
    private Object selectedPiece;

    public GameBoard() {


        this.pieceCollection = new Object[tileSideCount][tileSideCount];

        //yellow
        this.pieceCollection[0][0] = (new Guard(0,0,Color.YELLOW));
        this.pieceCollection[0][1] = (new Guard(0,1,Color.YELLOW));
        this.pieceCollection[0][2] = (new Guard(0,2,Color.YELLOW));
        this.pieceCollection[0][3] = (new Guard(0,3,Color.YELLOW));
        this.pieceCollection[0][4] = (new Leader(0,4,Color.YELLOW));


        //green
        this.pieceCollection[4][0] = (new Leader(4,0,Color.GREEN));
        this.pieceCollection[4][1] = (new Guard(4,1,Color.GREEN));
        this.pieceCollection[4][2] = (new Guard(4,2,Color.GREEN));
        this.pieceCollection[4][3] = (new Guard(4,3,Color.GREEN));
        this.pieceCollection[4][4] = (new Guard(4,4,Color.GREEN));



        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int row = this.getRowBasedOnCoordinates(mouseEvent.getY());
        int col = this.getRowBasedOnCoordinates(mouseEvent.getX());

        if(this.selectedPiece != null){
           Guard gu=(Guard)this.selectedPiece;
           gu.move(row, col);
           this.selectedPiece = null;
           this.repaint();

        }

        if(this.hasBoardPiece(row, col)){
            this.selectedPiece = this.getBoardPiece(row, col);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    //@Override
    public void paint(Graphics g) {

        super.paint(g);

        for (int row = 0; row < 5; row++) {


            for (int col = 0; col < 5; col++) {

                this.renderGameTile(g, row, col);
                this.renderGamePiece(g, row, col);
            }

        }
    }

    private Color getTileColor(int row, int col) {
        boolean isRowEven = (row % 2 == 0);
        boolean isRowOdd = !isRowEven;
        boolean isColEven = (col % 2 == 0);
        boolean isColOdd = !isColEven;

        if(isRowEven && isColEven) return Color.BLACK;
        if(isRowEven && isColOdd) return Color.WHITE;
        if(isRowOdd && isColEven) return Color.WHITE;
        return Color.BLACK;

    }

    private void renderGameTile (Graphics g, int row, int col){
        Color tileColor = this.getTileColor(row, col);
        GameTile tile = new GameTile(row, col, tileColor);
        tile.render(g);

    }

    private Object getBoardPiece(int row, int col) {
        return this.pieceCollection[row][col];
    }

    private boolean hasBoardPiece(int row, int col){

        return this.getBoardPiece(row, col) != null;
    }

    private void renderGamePiece (Graphics g, int row, int col){

        if(this.hasBoardPiece(row, col)) {

            Guard gu = (Guard) this.getBoardPiece(row, col);
            gu.render(g);

        }
    }
    private int getRowBasedOnCoordinates(int coordinates){
        return coordinates / GameTile.TILE_SIZE;
    }

}
