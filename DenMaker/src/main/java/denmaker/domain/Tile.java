/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import java.util.Objects;

/**
 * Makes a new Tile. Content (first int, before printing changed to string): 0
 * or "Unicode \u2588"=solid, 1 or " "=corridor or room, 2 or "w"=wall (of a
 * room)
 *
 * @author apndx
 */
public class Tile {

    public int content;
    public int y;
    public int x;
    public Tile parent;
    public int region;

    public Tile(int content, int y, int x, Tile parent) {
        this.content = content;
        this.y = y;
        this.x = x;
        this.parent = parent;
        this.region = 0; // 0: no region, negative: maze corridor
    }

    public void setParent(Tile parent) {
        this.parent = parent;
    }

    /**
     * Finds the next tile to add
     *
     * @param dungeonArea area that is under construction
     * @return returns the next Tile candidate to be added
     */
    public Tile checkOpposite(Area dungeonArea) {

        if (this.parent == null) {
            return null;
        }

        int differenceY = this.y - parent.y;
        int differenceX = this.x - parent.x;

        if (differenceY != 0) {
            try {
                return dungeonArea.tiles[this.y + differenceY][this.x];

            } catch (Exception e) {
                return null;
            }

        } else if (differenceX != 0) {
            try {
                return dungeonArea.tiles[this.y][this.x + differenceX];
            } catch (Exception e) {
                return null;
            }

        } else {
            return null;
        }
    }

    public void setContent(int content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.content;
        hash = 83 * hash + this.y;
        hash = 83 * hash + this.x;
        hash = 83 * hash + Objects.hashCode(this.parent);
        hash = 83 * hash + this.region;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tile other = (Tile) obj;
        if (this.content != other.content) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        if (this.region != other.region) {
            return false;
        }
        if (!Objects.equals(this.parent, other.parent)) {
            return false;
        }
        return true;
    }

}
