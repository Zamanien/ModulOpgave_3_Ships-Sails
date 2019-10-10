
// TODO: Programatically insert all ships from the array, rather than adding them manually in HTML and binding to them here
// Ships need an ID as Java needs to be able to refer to it uniquely, and it cannot do so based off direction or position as several ships of the same type may occupy the same space (during a collision).
class Ship {
  constructor(el, id, row, col, direction, type, nationality) {
    this.el = el
    this.id = id
    this.row = row
    this.col = col
    this.type = type

    this.direction = direction // Scenario determined: x, y, type, direction, nationality. More?
    this.nationality = nationality

    // Stats
    
    // Hardcoded rather than passed in by the constructor?
    this.sailors     = 100  // In Java this should simply be derived off ship type. Unlike Java it's a percentage here, since it's represented by a progress bar. That could be changed if need be, and then the value must simply be converted to percent for that progress bar.
    this.hullHealth = 100
    this.sailHealth = 100
    this.currentAmmunitionType = 0
    this.load = 0
    this.speed = 0 // The current speed of the ship

    // TODO: Add turns and moves max as well as the number currently made here? Really such values, and many others, should be passed by and thus set by Java.
  }
}

// Type stats:
// max speed, max sail, number_of_sailors_per_sail

class Tile {
  constructor(el, row, col) {
    this.el = el
    this.row = row // These are coordinates, not pixel positions.
    this.col = col 
  }
}

// Reference to the tiles element
tiles_elem = document.getElementById('tiles')
ships_elem = document.getElementById('ships')

// Just a few test ships
// This is supposed to be what the ships look like when we receive them from Java. The element reference is missing at this point as it's generated in JS, say in place_ships.
const ship1 = new Ship( null, 1, 1, 5, 90, "Ship of the Line", "Aztec")
const ship2 = new Ship( null, 2, 1, 3, 30, "Brig", "Aztec" )
const ship3 = new Ship( null, 3, 3, 4, 330, "Man at War", "Aztec" )
const ship4 = new Ship( null, 5, 1, 7, 330, "Man at War", "Celts" ) // The enemy!

// Comment this line to load ships from Java instead (which only partially works)
ships = [ship1, ship2, ship3, ship4]

// TODO: Hardcoded which nationality the player is for now, just for testing
player = "Aztec"
enemy = "Celts"

attack_mode = false



/* SETTINGS */

turn_number = 1

// Music

/* Auto-start background music */

// const audio = new Audio('audio/music/5.opus')
const audio = new Audio('audio/music/7.opus')
// audio.play() // Auto-play music
var sfx_muted = false
var sfx_volume = 0.7

/* Tiles / World settings */

const width = 10
const height = 6

/* Ship settings */

// Direction settings. Interval: 60, resulting in 6 different directions. Hexagons! */
o_offset = 30 // Depends on the sprite, determining its origin from which it should be rotated. Fx. if facing straight left it needs to be turned say 30 degrees up or down. Could opt for always rotating clockwise to the nearest valid direction.
o_degrees = 60


// The relative offset based off the direction. If changing to tip topped hexagons, just change the offset!
// TODO: Actually not used for anything at the moment?
// 0 = 30, 1 = 90, 2 = 150, 3 = 210, 4 = 270, 5 = 330
// directions = [o_offset, o_offset + (o_degrees*1), o_offset + (o_degrees*2), o_offset + (o_degrees*3), o_offset + (o_degrees*4), o_offset + (o_degrees*5)]


/* SFX settings */

const base_path               = 'audio/sfx/'
const base_path_brig          = 'audio/sfx/brig/'
const base_path_sotl          = 'audio/sfx/ship-of-the-line/'
const base_path_maw           = 'audio/sfx/man-at-war/'

const brig_sounds_ready       = ['Kuwhat1.wav', 'Kuwhat2.wav', 'Kuwhat3.wav']
const brig_sounds_affirmative = ['Kuyessr1.wav', 'Kuyessr2.wav', 'Kuyessr3.wav']
const sotl_sounds_ready       = ['Hshpredy.wav', 'Hshpwht1.wav', 'Hshpwht2.wav', 'Hshpwht3.wav']
const sotl_sounds_affirmative = ['Hshpyes1.wav', 'Hshpyes2.wav', 'Hshpyes3.wav']
const maw_sounds_ready       = ['Alwhat1.wav', 'Alwhat2.wav', 'Alwhat3.wav']
const maw_sounds_affirmative = ['Alyessr1.wav', 'Alyessr2.wav', 'Alyessr3.wav']

const sounds_sink             = ['ship-sinking.wav'] // Currently unused.
const sounds_edge             = ['edge-scream.wav']



/* INIT */

ship = null  // Default selected ship is no ship. Defined out here to have the proper scope.
tiles = []   // No tiles to begin with, populated by place_tiles

place_tiles()
place_ships()



/* METHODS */

function place_tiles() {
  const tile_offset_height = 121 // Pretty much = the height of the tile
  const tile_offset_width = 210  // Pretty much = the width of the tile

  for (var w = 0; w < width; w++) {

    for (var h = 0; h < height; h++) {
      
      let div = document.createElement('div')
      div.classList.add('tile', 'basic-tile')  // Add tile and basic-tile CSS classes to the element
      tiles_elem.appendChild(div)               // Add HTML element to the page
      tiles.push( new Tile(div, h, w) )        // Add tile to the tile array: 0,0 - 0,2 - 0,4

      var val = h * tile_offset_height
      div.style.top  += val + "px"
      div.style.left += (w * tile_offset_width/2)  + "px"
      // Different offsets for every second tile
      if ( w % 2 == 0) { 
        new_h = Math.floor(tile_offset_height / 2) // Negative to move the tile upwards by half the tile height
        div.style.top  = val + new_h + "px"
      // place_tile_coordinates((h * tile_offset_height), (w * tile_offset_width), h, w*2)
      }
    }
  }

}

// Maybe just for debugging, doesn't quite position the numbers correctly, but it's almost good enough for debug
function place_tile_coordinates(tile_h, tile_w, row, col) {
  let div = document.createElement('div')
  div.innerHTML =  row + ", " + col
  div.classList.add('tile-coordinate')
  tiles_elem.appendChild(div)

  let cb = document.getElementById("tiles").getBoundingClientRect();
  div.style.top  = (tile_h - cb.top) + "px"
  div.style.left = (tile_w - cb.left) + "px"
}

// For setting up the match
function place_ships() {
  for (aShip of ships) {
    let div = document.createElement('div')
    div.classList.add('ship')       // Adding the styling all ships have in common
    shipTypeClass = aShip.type.toLowerCase().replace(/ /g, '-')
    if (aShip.nationality == player) shipTypeClass += "-player"
    else if (aShip.nationality == enemy) shipTypeClass += "-enemy"
    div.classList.add( shipTypeClass )   // I need to add a class so CSS knows which ship model it for selecting an image to show. Replace replaces all whitespaces with - to turn them into valid CSS class names, and lowercasing to match our own CSS class naming convention.
    ships_elem.appendChild(div)
    aShip.el = div // A reference to this particular ship is saved.

    // Rotate the ship properly 
    aShip.el.style.transform = "rotate(+" + aShip.direction + "deg)";

    // Put the ship in its proper position on the map
    for (aTile of tiles) {
      if (aTile.row == aShip.row && aTile.col == aShip.col) {
        let cb = document.getElementById("tiles").getBoundingClientRect();
        let coords = aTile.el.getBoundingClientRect();
        // aShip.el.style.left = aTile.el.style.left
        // aShip.el.style.top  = aTile.el.style.top
        aShip.el.style.left = (coords.left - cb.left + 10) + "px" // 40 was adjusted manually to move the ship from the edge of the tile to the center
        aShip.el.style.top  = (coords.top - cb.top) + "px"
        break
      }
    }
  }
}


function play_sfx_ready() {
  if (ship.type == 'Brig') play_sfx(base_path_brig, brig_sounds_ready)
  else if (ship.type == 'Ship of the Line') play_sfx(base_path_sotl, sotl_sounds_ready)
  else if (ship.type == 'Man at War') play_sfx(base_path_maw, maw_sounds_ready)
}

function play_sfx_affirmative() {
  if (ship.type == 'Brig') play_sfx(base_path_brig, brig_sounds_affirmative)
  else if (ship.type == 'Ship of the Line') play_sfx(base_path_sotl, sotl_sounds_affirmative)
  else if (ship.type == 'Man at War') play_sfx(base_path_maw, maw_sounds_affirmative)
}

function play_sfx(bp, sounds) {

  if (!sfx_muted) {

    sfx = new Audio(bp + sounds[ Math.floor(Math.random()*sounds.length) ])
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

  
// TODO: Prevent ending the turn if there's moves you have to make left, due to speed
function end_turn() {
  for (aShip of ships) if (aShip.load != 0) aShip.load--
  if (ship) display_current_ammunition() // The ammunition might be loaded now, so the UI should show it if any ship is selected

  turn_number++
  document.getElementById('turn-number').childNodes[1].childNodes[1].innerHTML = turn_number
}


// Adding an event listener on the Ships div, used for ship selection below 
ships_elem.addEventListener('click', function(event) {
  ship_selection(event.target);
})

// TODO: Ideally this border should not rotate with the ship. Tried adding a surrounding div and putting a border on that but it was somewhere else (so i guess it would have to be moved as well, just not rotated?)
// Also call functions for updating the turn/move buttons based off remaining moves/turns (request the info from Java)
function ship_selection(clicked_ship) {
  // Now we have the ship element. Find the related ship object:
  for (aShip of ships) {
    // Only allow the user to select the ships of their own nationality
    if (aShip.el == clicked_ship && aShip.nationality == player) { 
      ship = aShip

      /* Update health bars */
      document.getElementById('sailors').style.width = ship.sailors + "%"
      document.getElementById('hull-health').style.width = ship.hullHealth + "%"
      document.getElementById('sails-health').style.width = ship.sailHealth + "%"

      display_current_ammunition() // Update the UI to show the currently loaded ammunition for the current ship

      update_selected_tile()

      play_sfx_ready()
    }
  }
}

function update_selected_tile() {

  for (aTile of tiles) {
    if (ship != null && aTile.row === ship.row && aTile.col === ship.col) {
      aTile.el.classList.add('selected-ship')
    }
    else {
      aTile.el.classList.remove('selected-ship') // Remove selected from all other ships
    }
  }
}


/* Ship controls */

function move() {
    ships_elem.classList.add('disable-pointer-events') // Disable ship selection while the move is ongoing to prevent a bug where one selects a ship, tells it to move/rotate and then select another ship and then that ship moves instead

    // It's fx. not always +1 on both axes going south east, as x will be 0 going from an even to an odd column as it's currently tiled. Hence we treat odd and even cols differently.
    even = (ship.col % 2)? true : false

  // 30, 90, 150, 210, 270, 330

  // if (on any border and facing the border):
  // Over the edge checks
  // If at the top or bottom of the map and facing one of those two directions respectively: Over the edge.
  // For left and right there are 4 directions to check
  // Moving within the board
    // Updating coordinates
    switch (ship.direction) {
      case 30:
        if (even) ship.row--
        ship.col--
        break
      case 90:
        ship.row--
        break
      case 150:
        if (even) ship.row--
        ship.col++
        break
      case 210:
        if (!even) ship.row++
        ship.col++
        break
      case 270:
        ship.row++
        break
      case 330:
        if (!even) ship.row++
        ship.col--
        break
    }

    if (ship.row < 0 || ship.col < 0 || ship.row > height-1 || ship.col > width-1) move_over_edge()
    else {
      update_selected_tile()
      play_sfx_affirmative()

      // Moving based off updated coordinates. Almost verbatim copy of the "placing ships algorithm" (changed aShip to ship), so if all this works I should extract this to a method
      for (aTile of tiles) {
        if (aTile.row == ship.row && aTile.col == ship.col) {
          let cb = document.getElementById("tiles").getBoundingClientRect();
          let coords = aTile.el.getBoundingClientRect();
          ship.el.style.left = (coords.left - cb.left + 10) + "px" // The last value was adjusted manually to move the ship from the edge of the tile to the center
          ship.el.style.top  = (coords.top - cb.top) + "px"
          break
        }
      }
    }
    ships_elem.classList.remove('disable-pointer-events') // Re-enable ship selection after the move has finished
}

// Old move method, useful for moving outside of tiles, such as over the edge.
function move_over_edge() {
  ship_move_distance = 90

  // We need to move it first for visuals' sake, but this is helpful for faster debugging
  destroy_ship(ship, false)
  update_selected_tile() // Remove the visual indicator (green outline) for what was previously the currently selected ship
  ship = null // Set currently selected ship to null


  // // Get current position
  // curPosLeft = parseInt(ship.el.style.left)
  // curPosTop = parseInt(ship.el.style.top)

  // // Currently it moves left and up equally, which makes sense at 45 degree angles, but we're using 30 degree angles so it should move further on the x-axis than the y-axis
  // // Calculate the move factoring in angles, like base_move * the distance from the nearest 90 degree angle, and base_distance could be 3 or something? Say (90-30) * 3 and (90-60) * 3?
  // if (ship.direction == 30) {
  //   ship.el.style.left = (curPosLeft - ship_move_distance) + "px"
  //   ship.el.style.top = (curPosTop - ship_move_distance) + "px"
  // }
  // else if (ship.direction == 330) {
  //   ship.el.style.left = (curPosLeft - ship_move_distance) + "px"
  //   ship.el.style.top = (curPosTop + ship_move_distance) + "px"
  // }

  //play_sfx(base_path, sounds_sink)
  play_sfx(base_path, sounds_edge)
}

// TURNING

function turn_clockwise() {
  // Honestly all these disable-pointer-events on turn and move (not attack!) are just error handling only needed because of the await way of turning. If we just switch fully to man-at-war's way of turning it wouldn't be needed.
  ships_elem.classList.add('disable-pointer-events') // Disable ship selection while the move is ongoing to prevent a bug where one selects a ship, tells it to move/rotate and then select another ship and then that ship moves instead
  ships_elem.classList.add('disable-pointer-events') // Disable pressing fx. the rotation buttons while the ship is rotating, to prevent a bug where the user clicks the rotate button twice

  if (ship.direction != 330) ship.direction += o_degrees
  else ship.direction = 30
  // array_offset = direction.indexOf(cur_direction)
  // if (array_offset == 5) offset = 0
  // else offset = array_offset + 1

  turn_visual("CW")
}


function turn_counter_clockwise() {
  ships_elem.classList.add('disable-pointer-events') // Disable ship selection while the move is ongoing to prevent a bug where one selects a ship, tells it to move/rotate and then select another ship and then that ship moves instead

  if (ship.direction != 30) ship.direction -= o_degrees
  else ship.direction = 330

  turn_visual("CCW")
}


// TODO: I think it would be easier to decouple the rotation as received from Java from the visual representation of the rotation, as chaging the latter might not mean changing the former
// It's async because we need to sleep while the ship rotates, before it moves and the best practice way of doing this is via a promise (in a thread)
async function turn_visual(direction) {

  cur_direction = parseInt( ship.el.style.transform.match(/-?\d+/)[0] ) // NOTE: This works as long as I don't use transform for setting anything besides rotation. The last part extracts the first number from the string

  if (direction == "CW") {
    ship.el.style.transform = 'rotate(' + (cur_direction + o_degrees) + 'deg)' // Originally I wanted to wrap back to zero once we got over 360 degrees, but then instead of rotating the shortest way round it rotates the long one...
  }
  else {
    ship.el.style.transform = 'rotate(' + (cur_direction - o_degrees) + 'deg)'
  }

  //TODO: BUG!: When await is called you can select a ship, press to rotate, then select another ship before sleep is over, and the newly selected ship moves instead.
  // Ideally we'd suspend all event handlers here until it's over. We can't lock up the UI with busy wait because then it stops the CSS rotation as well.
  if (ship.type != 'Man at War') await sleep(1700); // Set to whatever the transition duration is (ie. the time it takes to rotate).

  move() // Based on the rules: Turning within a single hex should not be possible?
}

// Helper method for one of our ways of visually turning a ship
function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}



/* ATTACK / WEAPONS */

const ball  = document.getElementById('change-ammunition-ball')
const chain = document.getElementById('change-ammunition-chain')
const grape = document.getElementById('change-ammunition-grape')
const CANNON = 0
const CHAIN  = 1
const GRAPE  = 2

// TODO: Implement attacking here. We also need support for the player clicking where to attack on the map/tiles, after clicking the attack button.
function attack_mode_toggle() {

  // If pressing the attack button a second time: Cancel attack mode
  if (attack_mode == true) clear_attack_mode() // Clears event listeners

  else {

    if (ship.load == 0) {
      attack_mode = true

      ships_elem.classList.add('disable-pointer-events')

      // Using named event listeners to be able to clear them again
      tiles_elem.addEventListener('mouseover', mover)
      tiles_elem.addEventListener('mouseout', mout)
      tiles_elem.addEventListener('mousedown', mdown)

    }
    else alert("Your current weapon isn't loaded. You have to wait at least " + ship.load + " turns.")
  }
}

function mover() { highlight_attack_tile(event.target, true) }
function mout() {  highlight_attack_tile(event.target, false) }
function mdown() { attack(event.target) }


function clear_attack_mode() {

  attack_mode = false
  ships_elem.classList.remove('disable-pointer-events')

  tiles_elem.removeEventListener('mouseover', mover)
  tiles_elem.removeEventListener('mouseout', mout)
  tiles_elem.removeEventListener('mousedown', mdown)

}

function attack(tile_attacked) {

  ship.load = 2 // It will be decremented by 1 at the start of the next round, making it reach 0 after two "end turns"
  display_current_ammunition() // Update this as ship.load is now above 0, no longer ready to fire
  clear_attack_mode()
  highlight_attack_tile(event.target, false)

  // Find the coordinates of the targeted tile
  targeted_tile = null
  for (aTile of tiles) {
    if (aTile.el == tile_attacked) targeted_tile = aTile 
  }

  for (aShip of ships) {
    if (targeted_tile.row == aShip.row && targeted_tile.col == aShip.col) damage_ship(ship, aShip)
  }
  
}

// TODO: This was just quickly implemented as a demonstration. Obviously > 10 is not a sufficient criterion as it will then become impervious to damage if it reaches fx. 9.
function damage_ship(current_ship, target_ship) {
  // Damage modifier based on the shiptype attacking
  if (current_ship.type == 'Brig') damage_modifier = 20
  else if (current_ship.type == 'Ship of the Line') damage_modifier = 30
  else if (current_ship.type == 'Man at War') damage_modifier = 40

    target_ship.hullHealth -= Math.random() * damage_modifier
    if (target_ship.sailors > 10) target_ship.sailors -= Math.random() * damage_modifier // Don't go to negative values
    if (target_ship.sailHealth > 10) target_ship.sailHealth -= Math.random() * damage_modifier

    // If hull health is less than 0, destroy the ship
    if (target_ship.hullHealth <= 0) destroy_ship(target_ship, true)
}

function destroy_ship(aShip, sink) {

  aShip.el.parentElement.removeChild(aShip.el) 

  if (sink) play_sfx(base_path, sounds_sink)
}

function update_ammunition(ammo_clicked) {

  if (ship.load == 0) {
    alert("The cannon is already loaded. Fire it to be able to reload.")
    return // Not really necessary as it stands, but...
  }
  else if (ship.currentAmmunitionType == ammo_clicked) return // Don't do anything if clicking the currently selected ammunition
  else {

    ship.load = 3 // It will be decremented by 1 at the start of the next round, making it reach 0 after three "end turns"

    ship.currentAmmunitionType = ammo_clicked
    display_current_ammunition() // Now display the updated current ammunition
  }
}

function display_current_ammunition() {
  switch (ship.currentAmmunitionType) {
    case CANNON: 
      current = ball
      ds1 = chain; ds2 = grape
      break
    case CHAIN: 
      current = chain
      ds1 = ball; ds2 = grape
      break
    case GRAPE: 
      current = grape
      ds1 = ball; ds2 = chain
      break
  }
  // Remove the current classes from all ammunition types:
  // Obviously only one can be selected at a time, and as for the active one maybe it already has red and green, where red was added last, and then it will stay red after trying to add the green class, even though the weapon has reloaded.
  ball.classList.remove('text-success', 'text-danger') 
  chain.classList.remove('text-success', 'text-danger')
  grape.classList.remove('text-success', 'text-danger')

  cl = (ship.load == 0)? 'text-success' : 'text-danger'
  current.classList.add(cl)
}

function highlight_attack_tile(e, over) {
  if (over) e.classList.add('target-tile')
  else e.classList.remove('target-tile')
}


ball.onclick  = () => { update_ammunition(CANNON) }

chain.onclick = () => { update_ammunition(CHAIN) }
grape.onclick = () => { update_ammunition(GRAPE) }

document.getElementById('attack').onclick = attack_mode_toggle


/* EVENT BINDINGS */

document.getElementById('music-button').onclick = music_toggle
document.getElementById('sfx-button').onclick = sfx_toggle

document.getElementById('end-turn').onclick = end_turn

document.getElementById('move-arrow').onclick = move
document.getElementById('turn-clockwise').onclick = turn_clockwise
document.getElementById('turn-counter-clockwise').onclick = turn_counter_clockwise
