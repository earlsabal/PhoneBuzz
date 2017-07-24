# PhoneBuzz

Java Application using Play Framework that can receive calls from a user or call a user to play fizz buzz over the phone.  Once connected, the user will be greeted and asked to enter a number within a range, the app will then respond by saying the results of fizz buzz for that number.  The user is thanked after for playing and the phone call ends.

## Setup

Clone application and navigate to the project.

run `sbt`

Afterward run `playGenerateSecret`

    [phone-buzz] $ playGenerateSecret
    [info] Generated new secret: ThisIsYourSecretKeylR6CTfG3gf90Latabg5241ABR5W1uDFNIkn
    [success] Total time: 0 s, completed 28/03/2014 2:26:09 PM
    
This will be your `APP_TOKEN` config for when you deploy.

You will also need your Twilio credentials

- ACCOUNT_SID 
- AUTH_TOKEN
- FROM_NUMBER (Twilio Phone Number)
- APP_URL (linkToApp.com/play)

Once deployed or ran locally, a user can view the home page and enter a phone number and set an amount of time for the call to be delayed.
