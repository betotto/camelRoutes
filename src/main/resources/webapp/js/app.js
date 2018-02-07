document.getElementById('helloDiv').innerHTML = 'Hello from javascript loaded from I don\'t know';

var xhr = new XMLHttpRequest();
xhr.open("GET", '/wizard/question', true);
xhr.onreadystatechange = () => {
  if(xhr.readyState == 4) {
    if(xhr.status == 200) {
      var response = JSON.parse(xhr.responseText);
      var content = document.getElementById('questions').innerHTML;
      response.forEach(function(ele) {
        content = content + '<li>' + ele.text + '</li>';
        document.getElementById('questions').innerHTML = content;
      });
    }
  }
}
xhr.send();