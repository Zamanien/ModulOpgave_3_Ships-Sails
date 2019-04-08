// MUSIC

/* Auto-start background music */

// const audio = new Audio('audio/music/5.opus')
const audio = new Audio('audio/music/7.opus')
audio.play()
var sfx_muted = false
var sfx_volume = 0.6


// TODO: Programatically insert all ships from the array, rather than adding them manually in HTML and binding to them here
// Ships need an ID as Java needs to be able to refer to it uniquely, and it cannot do so based off orientation or position as several ships of the same type may occupy the same space (after a collision).
class Ship {
  constructor(el, id, selected, x, y, position_left, position_top, orientation, type) {
    this.el = el
    this.id = id
    this.selected = selected
    // New location system
    // Old location system
    this.x = x
    this.y = y
    this.position_left = position_left
    this.position_top = position_top
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

// This is supposed to be what the ships look like when we receive them from Java. The element reference is missing at this point as it's generated in JS, say in place_ships.
// TODO: Update to instead have/use a set of coordinates to the tile they're on. OR update to reference the tile they're on, fx. by array index
const ship1 = new Ship( null, 1, false, 0, 2, 875, 0, 30, "ship-of-the-line" )
const ship2 = new Ship( null, 2, false, 1, 3, 250, 120, 30, "ship-of-the-line" )

ships = [ship1, ship2]

tiles = []

// Default selected ship
ship = null


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

/* Init */
place_tiles()
place_ships()
//new_place_ships()




/* Methods */
function place_tiles() {
  let tilesHTML = document.getElementById('tiles') // Get a reference to what will be the element surrounding all the tiles
  const width = 5                // 4 lower than the number of columns to make The number of columns to make
  const height = 3               // 2 lower than the total number of rows made
  const tile_offset_width = 210  // Pretty much = the width of the tile
  const tile_offset_height = 121 // Pretty much = the height of the tile

  // Add the first set of tiles
  for (var i = 0; i < width; i++) {

    for (var j = 0; j < height; j++) {
      
      let div = document.createElement('div')
      div.classList.add('basic-tile')  // Add basic-tile class to the element
      tilesHTML.appendChild(div)       // Add HTML element to the page
      tiles.push( new Tile(div, i,j) ) // Add tile to the tile array
      div.style.top  += (j * tile_offset_height) + "px"
      div.style.left += (i * tile_offset_width) + "px"
    }

  }


  for (var i = 0; i < (width-1); i++) {

    for (var j = 0; j < (height+1); j++) {
      
      let div = document.createElement('div')
      div.classList.add('basic-tile')

      new_h = -60 
      new_w = 105

      tilesHTML.appendChild(div)
      tiles.push( new Tile(div, i,j) ) // Add tile to the tile array
      
      div.style.top  += new_h + (j * tile_offset_height) + "px"
      div.style.left += new_w + (i * tile_offset_width) + "px"
      // console.log(div.style.top)
      // console.log(div.style.left)
    }

  }

  // All these position: absolute element break out of the regular positioning flow and thus the parent element is much smaller than its children.
  // We can then either hardcode a height for the container that doesn't work if we change the number of rows of tiles, OR we can set the parent container's height via JS:
  custom_adjustment = -50
  tilesHTML.style.height =  (height + 1) * tile_offset_height + custom_adjustment + "px"

}

// For setting up the match
function place_ships() {
  let shipsHTML = document.getElementById('ships')
  //<div id='testship1' class='ship'/></div> 
  for (aShip of ships) {
    let div = document.createElement('div')
    div.classList.add(aShip.type)   // I need to add a class so CSS knows which ship model it for selecting an image to show
    shipsHTML.appendChild(div)
    aShip.el = div // A reference to this particular ship is saved. TODO: Is this even possible?

    // Rotate the ship properly and put it in its proper position on the map
    aShip.el.style.transform = "rotate(+" + o_offset + "deg)"; // TODO: The default rotation may vary per ship? Randomize it based off the orientations array? Basically this should be default_rotation + aShip.orientation

    aShip.el.style.left = aShip.position_left + "px"
    aShip.el.style.top  = aShip.position_top + "px"
  }
}

function new_place_ships() {
  let shipsHTML = document.getElementById('ships')
  for (aShip of ships) {
    let div = document.createElement('div')
    div.classList.add(aShip.type)   // I need to add a class so CSS knows which ship model it for selecting an image to show
    shipsHTML.appendChild(div)
    aShip.el = div // A reference to this particular ship is saved. TODO: Is this even possible?

    // Rotate the ship properly and put it in its proper position on the map
    aShip.el.style.transform = "rotate(+" + o_offset + "deg)"; // TODO: The default rotation may vary per ship? Randomize it based off the orientations array? Basically this should be default_rotation + aShip.orientation

    for (aTile of tiles) {
      if (aTile.x == aShip.x && aTile.y == aShip.y) {
        aShip.el.style.left = aTile.position_left + "px"
        aShip.el.style.top  = aTile.position_top + "px"
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

// TODO: Ideally this border should not rotate with the ship. Tried adding a surrounding div but it was somewhere else
// TODO: Add event handler to all ships and set selected to true on the selected ship, and all others to false. Also add CSS border to selected ship to visually indicate which ship is selected?
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
  // Maybe I can just deleted the "selected" attribute and simply do one thing: assign the global ship variable to whichever ship is the current one?
}


/* Ship controls */

// TODO: Direction N OR S -> Only update top 
// Direction NW: -Up, -Left, 
// Direction SW: +Up, -Left
// Direction NE: -Up, +Left
// Direction NW: +Up, +Left
function move() {
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

  console.log(cur_orientation)
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



/* Event handlers */

// TODO: This should fire regardless of which ship is clicked? May be handled in method further up
// ship.el.onclick = play_sfx_ready

document.getElementById('music-button').onclick = music_toggle
document.getElementById('sfx-button').onclick = sfx_toggle

document.getElementById('end-turn').onclick = end_turn

document.getElementById('move-arrow').onclick = move
document.getElementById('turn-clockwise').onclick = turn_clockwise
document.getElementById('turn-counter-clockwise').onclick = turn_counter_clockwise
document.getElementById('attack').onclick = attack
