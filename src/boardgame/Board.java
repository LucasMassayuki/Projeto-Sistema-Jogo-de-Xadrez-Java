package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {// programação defensiva
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {// Programação defensiva
			throw new BoardException("position not on the board");
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		if (!positionExists(position)) {// Programação defensiva
			throw new BoardException("position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {// Programação defensiva
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	public Piece removePiece(Position position) {
		if(!positionExists(position)) {//quando essa posição não existe
			throw new BoardException("position not on the board");
		}
		if(piece(position) == null) {//se não tem nada nessa posição volta null
			return null;
		}
		Piece aux = piece(position);//quando a peça for retirado volta null
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}

	public boolean positionExists(int row, int column) {// tem que ser maior que zero e menos que rows & columns
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(Position position) {// quando já tem a peça no lugar
		if (!positionExists(position)) {// Programação defensiva
			throw new BoardException("position not on the board");
		}
		return piece(position) != null;
	}
}
