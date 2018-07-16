# Flink Twitter Analysis

This project consists of a basic example in which a Flink CEP enine ingests the streams from twitter and then raise an alarm based on a given keyword, "fire", "football" for example.
 
![flink](images/twitter.png)


## Getting Started

Download the project and replace `keys.CONSUMER_KEY`, `keys.CONSUMER_SECRET`, `keys.TOKEN` and `keys.TOKEN_SECRET` of your twitter app. 

```
props.setProperty(TwitterSource.CONSUMER_KEY, keys.CONSUMER_KEY);
props.setProperty(TwitterSource.CONSUMER_SECRET, keys.CONSUMER_SECRET);
props.setProperty(TwitterSource.TOKEN, keys.TOKEN);
props.setProperty(TwitterSource.TOKEN_SECRET, keys.TOKEN_SECRET);
```
If you dont have twitter app at Twitter Application Management, then generate a new app at https://apps.twitter.com/. Then execute the `run.java` file

## Sample Output

The sample output against keyword "movie" are shown below.
```
Alert !!!! RT @jhanetweets: Dahil sa movie posters, EZ MONEY👌

Alert !!!! RT @davidehrlich: this movie is so fucking good that people in the theater just started spontaneously laughing during the second half, beca…

Alert !!!! RT @aaa_46_movie: 初披露
♪#ジコチューで行こう！

#音楽の日
#齋藤飛鳥
#乃木坂46 https://t.co/mG5MFxd5Ar

Alert !!!! @NEFamilyFun @Tesco @sainsburys My son would love this! We saw the movie yesterday and loved it!! https://t.co/96LcgPP0jh

Alert !!!! RT @heylianarose: This movie is something, kau memang perlukan kemaaafan dari orang yang kau dah buat silap untuk menjadikan kau ‘A better…

Alert !!!! Does anyone know about a comprehensive article about "how Shazam was Capt Marvel but Shazam is a DC movie and Capt… https://t.co/nOF5Ep8YS5

Alert !!!! RT @DamarisRobless: This movie scene makes me cry every single time #bajolamismaluna https://t.co/PMTyPzkYqU

Alert !!!! RT @suckonmydolan: fandom: lol are the twins writing a book? making a movie? gonna host a tv show? go on tour?

ethan and grayson: we got a…

Alert !!!! RT @GagaTourNews: “I think absolutely that I need Lady Gaga in one of the Marvel movies. I’ve got to make her a heroine” - Stan Lee (creato…

Alert !!!! RT @aaa_46_movie: 初披露
♪#ジコチューで行こう！

#音楽の日
#齋藤飛鳥
#乃木坂46 https://t.co/mG5MFxd5Ar

Alert !!!! *watching a movie* 

```
## Author

*Amarjit Singh Dhillon*


## License

This project is licensed under the MIT License.
