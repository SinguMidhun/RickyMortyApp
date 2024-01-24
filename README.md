<h1 align="center">Ricky Morty App</h1>
<p>
  The application has two activites. <br/> 
  1. Main Activity  <br/> 
  2. CharacterDetails Activity  <br/>  <br/> 

  The Main activity will show all the list of characters to the user in a Grid view. We show the image, name and status of the Character. At a time as per the api request, we get 
  20 characters, once the user scrolls to the bottom of the recycler view, we show a button, to load more characters. <br/>

  Upon clicking on the "Load More" buttom we will trigger a new api request to the surver and get the next 20 character details. I have used Card view to show details of the 
  character in the recycler veiw.

  <br/>

  By clicking on any character, the user will be redirected to the Character details page, in which we show more details about that character. Character data shown in the 
  second acitivy is passed from the main activy ( We are not triggering new api request ). I am using Room Database for the "Add to favourite" feature.
  
</p>

<h3>Architecture</h3>

<p>

  I followed MVVM architecture. <br/>
  
  <h4>Repository</h4> <br/>

  The Local Api interface is used to talk with the Room Database, while the Remote data base is used to talk to the Remote Server. I have used Retrofit for the Networking calls
  And i have created Network State object to keep track of the state of the network request.  <br/>

  <h4>View Model</h4> <br/>
  We have only one view model in this project which talks to the repository to get the fetched information. <br/>
  
</p>

</br></br>

<p> Because of the time restrictions i have focused more on implementing the features instead of the UI</p>
