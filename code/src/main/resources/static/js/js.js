
// TODO: Programatically insert all ships from the array, rather than adding them manually in HTML and binding to them here
// Ships need an ID as Java needs to be able to refer to it uniquely, and it cannot do so based off orientation or position as several ships of the same type may occupy the same space (after a collision).
class Ship {
  constructor(el, id, x, y, orientation, type) {
    this.el = el
    this.id = id
    this.x = x
    this.y = y
    this.orientation = orientation
    this.type = type
  }
}

class Tile {
  constructor(el, x, y) {
    this.el = el
    this.x = x // These are coordinates, not pixel positions.
    this.y = y
  }
}

// Just a few test ships
// This is supposed to be what the ships look like when we receive them from Java. The element reference is missing at this point as it's generated in JS, say in place_ships.
const ship1 = new Ship( null, 1, 1, 5, 30, "ship-of-the-line" )
const ship2 = new Ship( null, 2, 1, 3, 30, "ship-of-the-line" )

ships = [ship1, ship2]



/* SETTINGS */

// Music

/* Auto-start background music */

// const audio = new Audio('audio/music/5.opus')
const audio = new Audio('audio/music/7.opus')
audio.play()
var sfx_muted = false
var sfx_volume = 0.6

/* Tiles / World settings */

const width = 5                // 4 lower than the number of columns to make The number of columns to make
const height = 3               // 1 lower than the total number of tiles from top to bottom
const t_width = width + 3      // Total width and heights, starting from 0

/* Ship settings */

ship_move_distance = 90

// Orientation settings. Interval: 60, resulting in 6 different directions. Hexagons! */
o_offset = 30 // Depends on the sprite, determining its origin from which it should be rotated. Fx. if facing straight left it needs to be turned say 30 degrees up or down. Could opt for always rotating clockwise to the nearest valid orientation.
o_degrees = 60


// The relative offset based off the orientation_offset. If changing to tip topped hexagons, just change the offset!
// TODO: Actually not used for anything at the moment?
orientations = [o_offset, o_offset + (o_degrees*1), o_offset + (o_degrees*2), o_offset + (o_degrees*3), o_offset + (o_degrees*4), o_offset + (o_degrees*5)]


/* SFX settings */

const base_path = 'audio/sfx/wc2v3/'
//const sounds = ['human1.wav', 'human2.wav', 'human3.wav', 'acknowledge1.wav', 'acknowledge2.wav', 'acknowledge3.wav']
const sounds_ready = ['Hshpredy.wav', 'Hshpwht1.wav', 'Hshpwht2.wav', 'Hshpwht3.wav']
const sounds_affirmative = ['Hshpyes1.wav', 'Hshpyes2.wav', 'Hshpyes3.wav']



/* INIT */

// Default selected ship is no ship. Defined out here to have the proper scope.
ship = null
tiles = [] // No tiles to begin with, populated by place_tiles

place_tiles()
place_ships()



/* Methods */
function place_tiles() {
  let tilesHTML = document.getElementById('tiles') // Get a reference to what will be the element surrounding all the tiles
  const tile_offset_height = 121 // Pretty much = the height of the tile
  const tile_offset_width = 210  // Pretty much = the width of the tile

  // Add the first set of tiles
  for (var w = 0; w < width; w++) {

    for (var h = 0; h < height+1; h++) {
      
      let div = document.createElement('div')
      div.classList.add('basic-tile')  // Add basic-tile class to the element
      tilesHTML.appendChild(div)       // Add HTML element to the page
      tiles.push( new Tile(div, h, w*2) ) // Add tile to the tile array: 0,0 - 0,2 - 0,4
      div.style.top  += (h * tile_offset_height) + "px"
      div.style.left += (w * tile_offset_width)  + "px"
      // place_tile_coordinates((h * tile_offset_height), (w * tile_offset_width), h, w*2)
    }

  }


  for (var w = 0; w < (width-1); w++) {

    for (var h = 0; h < (height+1); h++) {
      
      let div = document.createElement('div')
      div.classList.add('basic-tile')

      new_h = -60 // Basically tile height / 2
      new_w = 105 // Basically tile width / 2

      tilesHTML.appendChild(div)
      tiles.push( new Tile(div, h, (w+1)*2-1) ) // Add tile to the tile array: 0,1 - 0,3 - 0,5
      
      div.style.top  += new_h + (h * tile_offset_height) + "px"
      div.style.left += new_w + (w * tile_offset_width)  + "px"
      // place_tile_coordinates(new_h + (h * tile_offset_height), new_w + (w * tile_offset_width), h, (w+1)*2-1)
    }

  }

  // All these position: absolute element break out of the regular positioning flow and thus the parent element is much smaller than its children.
  // We can then either hardcode a height for the container that doesn't work if we change the number of rows of tiles, OR we can set the parent container's height via JS:
  custom_adjustment = -80
  tilesHTML.style.height =  (height + 2) * tile_offset_height + custom_adjustment + "px"
}

// Maybe just for debugging
function place_tile_coordinates(tile_h, tile_w, x, y) {
  let tilesHTML = document.getElementById('tiles') // Get a reference to what will be the element surrounding all the tiles
  let div = document.createElement('div')
  div.innerHTML = x + ", " + y
  div.classList.add('tile-coordinate')
  tilesHTML.appendChild(div)

  let cb = document.getElementById("tiles").getBoundingClientRect();
  div.style.top  = (tile_h - cb.top) + "px"
  div.style.left = (tile_w - cb.left) + "px"
}

// For setting up the match
function place_ships() {
  let shipsHTML = document.getElementById('ships')
  for (aShip of ships) {
    let div = document.createElement('div')
    div.classList.add(aShip.type)   // I need to add a class so CSS knows which ship model it for selecting an image to show
    shipsHTML.appendChild(div)
    aShip.el = div // A reference to this particular ship is saved. TODO: Is this even possible?

    // Rotate the ship properly 
    aShip.el.style.transform = "rotate(+" + o_offset + "deg)"; // TODO: The default rotation may vary per ship? Randomize it based off the orientations array? Basically this should be default_rotation + aShip.orientation

    // Put the ship in its proper position on the map
    for (aTile of tiles) {
      if (aTile.x == aShip.x && aTile.y == aShip.y) {
        let cb = document.getElementById("tiles").getBoundingClientRect();
        let coords = aTile.el.getBoundingClientRect();
        // aShip.el.style.left = aTile.el.style.left
        // aShip.el.style.top  = aTile.el.style.top
        aShip.el.style.left = (coords.left - cb.left + 40) + "px" // 40 was adjusted manually to move the ship from the edge of the tile to the center
        aShip.el.style.top  = (coords.top - cb.top) + "px"
        break
      }
    }
  }
}


function play_sfx_ready() {
  play_sfx(sounds_ready)
}

function play_sfx(sounds) {

  if (!sfx_muted) {

    sfx = new Audio(base_path + sounds[ Math.floor(Math.random()*sounds.length) ])
    sfx.volume = sfx_volume
    sfx.play()
  }
}

// Try to mute all video and audio elements on the page
function sfx_toggle() {
  sfx_muted = !sfx_muted
}

/* Main game buttons */

function music_toggle() {
  if (audio.paused) audio.play()
  else audio.pause()
}

  
function end_turn() {
  alert('Ending turn...')
}

function attack() {
  alert('Attacking...')
}

// Adding an event listener on the Ships div, used for ship selection below 
document.getElementById('ships').addEventListener('click', function(event) {
  ship_selection(event.target);
});

// TODO: Ideally this border should not rotate with the ship. Tried adding a surrounding div and putting a border on that but it was somewhere else (so i guess it would have to be moved as well, just not rotated?)
// Also call functions for updating the turn/move buttons based off remaining moves/turns (request the info from Java)
function ship_selection(clicked_ship) {
  // Now we have the ship element. Find the related ship object:
  for (aShip of ships) {
    if (aShip.el == clicked_ship) { 
      ship = aShip
      aShip.el.classList.add('ship-selected')
    }
    else {
      aShip.el.classList.remove('ship-selected')
    }
  }
  play_sfx_ready()
  // Maybe I can just delete the "selected" attribute and simply do one thing: assign the global ship variable to whichever ship is the current one?
}


/* Ship controls */

function move() {

  // 30, 90, 150, 210, 270, 330

  // if (on any border and facing the border):
  // Over the edge checks
  // If at the top or bottom of the map and facing one of those two directions respectively: Over the edge.
  // For left and right there are 4 directions to check
  if ( 
    ( ship.orientation == 90 && ship.x == 0 || ship.orientation == 270 && ship.x == height)
    || ( ship.orientation == 30 && (ship.y == 0 || ship.x == 0) )
    || ( ship.orientation == 330 && (ship.y == 0) )
    || ( ship.orientation == 210 && (ship.x == height || ship.y == t_width) )
    || ( ship.orientation == 150 && (ship.y == 0 == t_width) )
  ) {
    move_over_edge()
  }
  // Moving within the board
  else { 
    // Updating orientations
    if (ship.orientation == 30) {
      ship.x--
      ship.y--
    }
    else if (ship.orientation == 90) ship.x--
    
    else if (ship.orientation == 150) ship.y++
    
    else if (ship.orientation == 210) {
      ship.x++
      ship.y++
    }
    else if (ship.orientation == 270) ship.x++
    else { // 330
      ship.y--
    }

    // Moving based off updated coordinates. Almost verbatim copy of the "placing ships algorithm" (changed aShip to ship), so if all this works I should extract this to a method
    for (aTile of tiles) {
      if (aTile.x == ship.x && aTile.y == ship.y) {
        let cb = document.getElementById("tiles").getBoundingClientRect();
        let coords = aTile.el.getBoundingClientRect();
        ship.el.style.left = (coords.left - cb.left + 40) + "px" // 40 was adjusted manually to move the ship from the edge of the tile to the center
        ship.el.style.top  = (coords.top - cb.top) + "px"
        break
      }
    }
    
  }

  play_sfx(sounds_affirmative)
}

// Old move method, useful for moving outside of tiles, such as over the edge. TODO on 4 of the directions, though
function move_over_edge() {
  // We need to move it first for visuals' sake, but this is helpful for faster debugging
  ship.el.parentElement.removeChild(ship.el) 
  ship = null

  // Get current position
  curPosLeft = parseInt(ship.el.style.left)
  curPosTop = parseInt(ship.el.style.top)

  orientation = ship.el.style.transform

  // TODO: Currently it moves left and up equally, which makes sense at 45 degree angles, but we're using 30 degree angles so it should move further on the x-axis than the y-axis
  // Calculate the move factoring in angles, like base_move * the distance from the nearest 90 degree angle, and base_distance could be 3 or something? Say (90-30) * 3 and (90-60) * 3?
  if (orientation == "rotate(30deg)") {
    ship.el.style.left = (curPosLeft - ship_move_distance) + "px"
    ship.el.style.top = (curPosTop - ship_move_distance) + "px"
  }
  else if (orientation == "rotate(-30deg)") {
    ship.el.style.left = (curPosLeft - ship_move_distance) + "px"
    ship.el.style.top = (curPosTop + ship_move_distance) + "px"
  }

  play_sfx(sounds_affirmative)
}


function turn_clockwise() {
  if (ship.orientation != 330) ship.orientation += o_degrees
  else ship.orientation = 30
  // array_offset = orientations.indexOf(cur_orientation)
  // if (array_offset == 5) offset = 0
  // else offset = array_offset + 1

  turn_visual("CW")
}


// TODO: I think it would be easier to decouple the rotation as received from Java from the visual representation of the rotation, as chaging the latter might not mean changing the former
function turn_visual(direction) {
  cur_orientation = parseInt( ship.el.style.transform.match(/-?\d+/)[0] ) // NOTE: This works as long as I don't use transform for setting anything besides rotation. The last part extracts the first number from the string

  if (direction == "CW") {
    ship.el.style.transform = 'rotate(' + (cur_orientation + o_degrees) + 'deg)' // Originally I wanted to wrap back to zero once we got over 360 degrees, but then instead of rotating the shortest way round it rotates the long one...
  }
  else {
    ship.el.style.transform = 'rotate(' + (cur_orientation - o_degrees) + 'deg)'
  }
  //   // Handle reflect and changing the image here.
  //   ship.el.classList.add('ship-right')
}


function turn_counter_clockwise() {
  // array_offset = orientations.indexOf(cur_orientation)
  // if (array_offset == 0) offset = 5
  // else offset = array_offset - 1

  if (ship.orientation != 30) ship.orientation -= o_degrees
  else ship.orientation = 330

  turn_visual("CCW")
  // Handle reflect and changing the image here.
  //ship.el.style.backgroundPosition = '0px -300px';
  //ship.el.classList.remove('ship-right')
}



/* EVENT BINDINGS */

// TODO: This should fire regardless of which ship is clicked? May be handled in method further up
// ship.el.onclick = play_sfx_ready

document.getElementById('music-button').onclick = music_toggle
document.getElementById('sfx-button').onclick = sfx_toggle

document.getElementById('end-turn').onclick = end_turn

document.getElementById('move-arrow').onclick = move
document.getElementById('turn-clockwise').onclick = turn_clockwise
document.getElementById('turn-counter-clockwise').onclick = turn_counter_clockwise
document.getElementById('attack').onclick = attack
