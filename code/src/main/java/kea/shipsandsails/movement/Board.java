package movement;

public class Board {
  final short HEIGHT = 16;
  final short WIDTH = 16;
  final Hex[][] hexes = new Hex[WIDTH][HEIGHT]; 

  // Used to tell JS if it's possible to move forward or not, activating or inactivating the button
  // Called when an arrow is clicked OR a ship is selected. It may also be out of moves this turn.
  // These two values are stored in the Ship and received and passed on to here by the Controller
  public boolean possibleForward(String orientation, short xPos, short yPos, int remainingMoves) {
    if (remainingMoves == 0) return false;
    else {
      if (! (xPos == 0 || xPos ==  16 || yPos == 0 || yPos == 16) ) {
        return true;
      }
      else {
        if (orientation == "N") {}
        if (orientation == "NE") {}
        if (orientation == "SE") {}
        if (orientation == "S") {}
        if (orientation == "SW") {}
        if (orientation == "NW") {}
      }
    }
    // TODO: Just a temporary placeholder until this method is finished, so it compiles
    return false;
  }

  // Called when an arrow is clicked OR a ship is selected
  public boolean possibleTurn(int remainingTurns) {
    return (remainingTurns != 0);
  }
}
