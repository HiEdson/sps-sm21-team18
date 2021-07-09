
//fetching data and display then in the list-topics
async function topics() {
  const getTopics = await fetch('/list-topics');
  const allTopics = await getTopics.json();

  allTopics.forEach(function (o) {
    document.getElementById("allTopics").innerHTML += '<a href=\'/one-topic?name=escoliosis\'>' + '<li>' + '<b>' + o.name + '</b>' + '   ' + o.description + '<br>' + '</li>' + '</a>';
  });
  console.log(allTopics);
}

//display one topic in a new page /one-topic?name=topicname
async function oneTopic() {
  const OneTopic = await fetch('/one-topic?name=covid');
  const specificTopic = await OneTopic.json();

  specificTopic.forEach(function (o) {
    document.getElementById("oneTopicTitle").innerHTML += o.name;
    document.getElementById("oneTopicDescrip").innerHTML += o.description;
  });
  console.log(specificTopic);
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