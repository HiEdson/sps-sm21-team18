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

//fetching data and display then in the list-topics
async function topics() {
    const getTopics = await fetch('/list-topics');
    const allTopics = await getTopics.json();
    //const myObj = await JSON.parse(allThoughs);

    //const domElement = document.getElementById("allTopics");
    allTopics.forEach(function (o) {
        document.getElementById("allTopics").innerHTML += '<li>' + '<b>' + o.name + '</b>' + '   ' + o.description + '<br>' + '</li>';
    });
        domElement.innerHTML = allTopics[Object.keys(allTopics)[0]];
    console.log(allTopics);
    //allThoughs[Object.keys(allThoughs)[t]];

    //console.log(allThoughs[Object.keys(allThoughs)[t]]);
    //console.log(myObj.though1); allThoughs.though1;
}
