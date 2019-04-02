// AUDIO

/* Auto-start background music */

const audio = new Audio('audio/music/5.opus')
// const audio = new Audio('audio/music/7.opus')
audio.play()
var sfxs_muted = false
var sfx_volume = 0.6

// SHIP INITIALIZATION. Interval: 60, resulting in 6 different directions. Hexagons!
orientations = [30, 90, 150, 210, 270, 330]

ship = {el: document.getElementById('testship'), position: 0, orientation_offset: 45, orientation: "ne", type: "dreadnaught" }

// Ship defaults, for testing
ship.el.style.left="1000px"
ship.el.style.top="150px"
ship_move_distance = 150
ship.el.style.transform = "rotate(+30deg)";


function play_SFX_affirmative() {
  const base_path = 'audio/sfx/wc2v3/'
  //const sounds = ['human1.wav', 'human2.wav', 'human3.wav', 'acknowledge1.wav', 'acknowledge2.wav', 'acknowledge3.wav']
  const sounds = ['Hshpyes1.wav', 'Hshpyes2.wav', 'Hshpyes3.wav']

  if (!sfxs_muted) {

    sfx = new Audio(base_path + sounds[Math.floor(Math.random()*sounds.length)])
    sfx.volume = sfx_volume
    sfx.play()
  }
}

function play_SFX_ready() {
  const base_path = 'audio/sfx/wc2v3/'
  //const sounds = ['human1.wav', 'human2.wav', 'human3.wav', 'acknowledge1.wav', 'acknowledge2.wav', 'acknowledge3.wav']
  const sounds = ['Hshpredy.wav', 'Hshpwht1.wav', 'Hshpwht2.wav', 'Hshpwht3.wav']

  if (!sfxs_muted) {

    sfx = new Audio(base_path + sounds[Math.floor(Math.random()*sounds.length)])
    sfx.volume = sfx_volume
    sfx.play()
  }
}

// Mute/Unmute all sounds
// May not work as one sound may be muted while another is not?
// function toggleMute(elem) {
//   if (elem.muted == false) {
//     elem.muted = true;
//     elem.pause();
//   }
//   else 
//     elem.muted = false;
// }

// Try to mute all video and audio elements on the page
function sfx_toggle() {
    // document.querySelectorAll("audio").forEach( elem => toggleMute(elem) );
  sfxs_muted = !sfxs_muted
}

/* Main game buttons */

function music_toggle() {
  if (audio.paused) audio.play()
  else audio.pause()
}

  

function end_turn() {
  alert('Ending turn...')
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

  if (orientation == "rotate(30deg)") {
    ship.el.style.left = (curPosLeft - ship_move_distance) + "px"
    ship.el.style.top = (curPosTop - ship_move_distance) + "px"
  }
  else if (orientation == "rotate(-30deg)") {
    ship.el.style.left = (curPosLeft - ship_move_distance) + "px"
    ship.el.style.top = (curPosTop + ship_move_distance) + "px"
  }

  play_SFX_affirmative()
}

function turn_clockwise() {
  orientation = ship.el.style.transform
  if (orientation != "rotate(-30deg)") {
    ship.el.style.transform = "rotate(-30deg)";
  }
  else {
    // Handle reflect and changing the image here.
    ship.el.classList.add('ship-right')
  }
}

function turn_counter_clockwise() {
  orientation = ship.el.style.transform
  if (orientation != "rotate(30deg)") {
    ship.el.style.transform = "rotate(30deg)";
  }
  else {
    // Handle reflect and changing the image here.
    ship.el.classList.remove('ship-right')
  }
}



/* Event listeners */

ship.el.onclick = play_SFX_ready

document.getElementById('music-button').onclick = music_toggle
document.getElementById('sfx-button').onclick = sfx_toggle

document.getElementById('end-turn').onclick = end_turn

document.getElementById('move-arrow').onclick = move
document.getElementById('turn-clockwise').onclick = turn_clockwise
document.getElementById('turn-counter-clockwise').onclick = turn_counter_clockwise
