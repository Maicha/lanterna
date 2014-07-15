/*
 * This file is part of lanterna (http://code.google.com/p/lanterna/).
 *
 * lanterna is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2010-2014 Martin
 */
package com.googlecode.lanterna.terminal;

/**
 * A 2-d position in 'terminal space'. Please note that the coordinates are 0-indexed, meaning 0x0 is the top left
 * corner of the terminal. This object is immutable so you cannot change it after it has been created. Instead, you
 * can easily create modified 'clones' by using the 'with' methods.
 *
 * @author Martin
 */
public class TerminalPosition {

    /**
     * Constant for the top-left corner (0x0)
     */
    public static final TerminalPosition TOP_LEFT_CORNER = new TerminalPosition(0, 0);

    private final int row;
    private final int column;

    /**
     * Creates a new TerminalPosition object, which represents a location on the screen. There is no check to verify
     * that the position you specified is within the size of the current terminal and you can specify negative positions
     * as well.
     *
     * @param column Column of the location, or the "x" coordinate, zero indexed (the first column is 0)
     * @param row Row of the location, or the "y" coordinate, zero indexed (the first row is 0)
     */
    public TerminalPosition(int column, int row) {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the index of the column this position is representing, zero indexed (the first column has index 0).
     * @return Index of the column this position has
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the index of the row this position is representing, zero indexed (the first row has index 0)
     * @return Index of the row this position has
     */
    public int getRow() {
        return row;
    }

    /**
     * Creates a new TerminalPosition object representing a position with the same column index as this but with a
     * supplied row index.
     * @param row Index of the row for the new position
     * @return A TerminalPosition object with the same column as this but with a specified row index
     */
    public TerminalPosition withRow(int row) {
        if(row == 0 && this.column == 0) {
            return TOP_LEFT_CORNER;
        }
        return new TerminalPosition(this.column, row);
    }

    /**
     * Creates a new TerminalPosition object representing a position with the same row index as this but with a
     * supplied column index.
     * @param column Index of the column for the new position
     * @return A TerminalPosition object with the same row as this but with a specified column index
     */
    public TerminalPosition withColumn(int column) {
        if(column == 0 && this.row == 0) {
            return TOP_LEFT_CORNER;
        }
        return new TerminalPosition(column, this.row);
    }

    /**
     * Creates a new TerminalPosition object representing a position on the same row, but with a column offset by a
     * supplied value. Calling this method with delta 0 will return this, calling it with a positive delta will return
     * a terminal position <i>delta</i> number of columns to the right and for negative numbers the same to the left.
     * @param delta Column offset
     * @return New terminal position based off this one but with an applied offset
     */
    public TerminalPosition withRelativeColumn(int delta) {
        if(delta == 0) {
            return this;
        }
        return withColumn(column + delta);
    }

    /**
     * Creates a new TerminalPosition object representing a position on the same column, but with a row offset by a
     * supplied value. Calling this method with delta 0 will return this, calling it with a positive delta will return
     * a terminal position <i>delta</i> number of rows to the down and for negative numbers the same up.
     * @param delta Column offset
     * @return New terminal position based off this one but with an applied offset
     */
    public TerminalPosition withRelativeRow(int delta) {
        if(delta == 0) {
            return this;
        }
        return withRow(row + delta);
    }

    @Override
    public String toString() {
        return "[" + column + ":" + row + "]";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.row;
        hash = 23 * hash + this.column;
        return hash;
    }

    public boolean equals(int columnIndex, int rowIndex) {
        return this.column == columnIndex &&
                this.row == rowIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TerminalPosition other = (TerminalPosition) obj;
        return this.row == other.row && this.column == other.column;
    }
}
