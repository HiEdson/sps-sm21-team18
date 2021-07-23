//navbar
const hamburger = document.querySelector(".hamburger");
const navLinks = document.querySelector(".nav-links");
const links = document.querySelectorAll(".nav-links li");
hamburger.addEventListener('click', () => {
  //Animate Links
  navLinks.classList.toggle("open");
  links.forEach(link => {
    link.classList.toggle("fade");
  });

  //Hamburger Animation
  hamburger.classList.toggle("toggle");
});

//fetching data and display then in the list-topics
async function topics() {
  const getTopics = await fetch('/list-topics');
  const allTopics = await getTopics.json();

  //https://picsum.photos/500/300/?image=5
  allTopics.forEach(function (o) {
    document.getElementById("allTopics").innerHTML +=
      '<li class="cards_item">' +
      '<a id="linkCard" href="/topic.html?name=' + o.name + '">' +
      '<div class="card">' +
      '<div class="card_image"><img id="imgCard" src=" https://previews.123rf.com/images/captainvector/captainvector1705/captainvector170512089/79214826-set-of-illness-icons.jpg">' +
      '<div class="card_content">' +
      '<h2 class="card_title">' + o.name + '</h2>' +
      '<p class="card_text">' + o.description.substring(0, 80) + ' ...more</p>' +
      '<p class="card_text"> Created at: ' + o.date + '</p>'
    '</div>' +
      '</div>' +
      '</div>' +
      '</a>' +
      '</li>'
  });
  console.log(allTopics);
}

/*function createMap() {
  const map = new google.maps.Map(
    document.getElementById('map'),
    { center: { lat: 37.422, lng: -122.084 }, zoom: 16 });
}*/
//display form
//call both function when load initAll
function initAll() {
  //createMap();
  topics();
}