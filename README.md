## Will write a README before lab on monday. 


## Testing with adb

![Subtask b,  adb testing ](https://cdn.discordapp.com/attachments/657297914879672330/1482857442223198218/image.png?ex=69b87a1c&is=69b7289c&hm=77c1d8b4c5862803fe890e44fc91c063926d039bc46ef5ec0c106e233eea1b1b&)

# Tests


### clicking a button in the main-menu takes you to the right sub-activity (i.e. to the Quiz or the Gallery; testing one button is enough);

## Method name: testNavBarNavigation()
## We first check if the home screen is diplayed, then we navigate to the descirption that has "Gallery" with performClick. Then we check if we are in the correct screen by looking for a certain tag in that particular acitvity which is "GALLERY_SCREEN". 


### is the score updated correctly in the quiz (the test submits at least one right/wrong answer each and you check if the score is correct afterwards);

## Method name: testScroringSystemOfQuizApp()

## We first click on the play button, select a button that has "Zubat" contained (just to simulate  a button press), we then check if correct ? Score should be "Score 1 / 1" : Score should stay : "Score 0 / 1"

### Screenshot of all the tests

![screenshot of all the tests](https://cdn.discordapp.com/attachments/657297914879672330/1482859731176525864/image.png?ex=69b87c3e&is=69b72abe&hm=0d49a6bb6a91182d22df8ac303c2c447ffdadabc88b1ec592b98b2014cfae295&)

