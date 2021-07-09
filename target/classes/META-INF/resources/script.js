// Copyright 2020 Google LLC
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

<<<<<<< HEAD
//fetching data and display then in the list-topics
async function topics() {
    const getTopics = await fetch('/list-topics');
    const allTopics = await getTopics.json();

    allTopics.forEach(function (o) {
        document.getElementById("allTopics").innerHTML += '<li>' + '<b>' + o.name + '</b>' + '   ' + o.description + '<br>' + '</li>';
    });
    console.log(allTopics);


}



async function oneTopic() {
    
    const OneTopic = await fetch('/one-topic?name=covid');
    const specificTopic = await OneTopic.json();
    
    specificTopic.forEach(function (o) {
        document.getElementById("oneTopicTitle").innerHTML +=  o.name;
        document.getElementById("oneTopicDescrip").innerHTML += o.description;

    });

    console.log(specificTopic);
    //window.location.href ="/topic.html";
} 

//display the element in the page topic etc....
//try https://docs.oracle.com/javaee/5/api/javax/servlet/RequestDispatcher.html
=======
function createMap() {
  const map = new google.maps.Map(
      document.getElementById('map'),
      {center: {lat: 37.422, lng: -122.084}, zoom: 16});
}
>>>>>>> 92fea2821e834823c5d1a5a57d7c5241764ec441
