
//fetching data and display then in the list-topics
async function topics() {
  const getTopics = await fetch('/list-topics');
  const allTopics = await getTopics.json();

  allTopics.forEach(function (o) {
    document.getElementById("allTopics").innerHTML += '<a href="/topic.html?name=' + o.name + '">' + '<li>' + '<b>' + o.name + '</b>' + '   ' + o.description + '<br>' + '</li>' + '</a>';
    //document.getElementById("allTopics").innerHTML += '<a href="/one-topic?name='+o.name + '" + "onclick="return getPar();" +>' + '<li>' + '<b>' + o.name + '</b>' + '   ' + o.description + '<br>' + '</li>' + '</a>';
  });
  console.log(allTopics);
}

function createMap() {
  const map = new google.maps.Map(
    document.getElementById('map'),
    { center: { lat: 37.422, lng: -122.084 }, zoom: 16 });
}

//call both function when load initAll
function initAll() {
  createMap();
  topics();
}