// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Make a left tab longer.
 */
function longer(x) {
  x.style.width = "150px";
}

/**
 * Restore the tab.
 */
function shorter(x) {
  x.style.width = "120px";
}

/**
 * Initialize the content of textarea.
 */
function load() {
  var t = document.getElementById("text");
  t.innerText = "This is my portfolio.\nI'm Xingyu Liu from Beijing Jiaotong University. I'll enter my 4th year studying computer science this September. Nice to meet you!";
}

/**
 * Change the content of textarea.
 */
function click1() {
  var text = document.getElementById("text");
  var gallery = document.getElementsByClassName("gallery");
  text.innerText = "Some of My Projects:\r\n1.Front end for the game Splendor(in Google Girl Hackathon V)\n2.A gomoku game by HTML";
  for (var i = 0; i < gallery.length; i++)
  {
    gallery[i].style.display = "none";
  }
}
function click2() {
  var text = document.getElementById("text");
  var gallery = document.getElementsByClassName("gallery");
  text.innerText = "Image Gallery:"
  for (var i = 0; i < gallery.length; i++)
  {
    gallery[i].style.display = "block";
  }
}

/**
 * Fetches a message from the server and adds it to the DOM.
 */
function getMessage() {
  console.log('Fetching a message.');
  const responsePromise = fetch('/data');
  responsePromise.then(handleResponse);
}

/**
 * Handles response by converting it to text and passing the result to
 * addMessageToDom().
 */
function handleResponse(response) {
  console.log('Handling the response.');
  const textPromise = response.text();
  textPromise.then(addMessageToDom);
}

/** Adds a random message to the DOM. */
function addMessageToDom(message) {
  console.log('Adding message to dom: ' + message);

  const messageContainer = document.getElementById('text');
  messageContainer.innerText = message;
}
