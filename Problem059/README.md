# [XOR decryption](http://projecteuler.net/problem=59)
Scott Wiedemann

01/02/2017

## Compile It
ant


## Run It
java -jar ./target/xordecryption.jar p059_cipher.txt MobyDick.txt 3

## Thoughts
I used the free English book 'Moby Dick' to find a character frequency distribution of the English language.  This gave me a good approximation as to which characters are the most frequent in the written English language.  The most common character from Moby Dick (a space character) is statistical significance as it is more common that the next most common character by more than 2 standard deviations.  Read more about frequency distribution here: https://en.wikipedia.org/wiki/Frequency_analysis.

Using the known key length, three, I created another frequency distribution of the values combined with there 'key position' in the cipher text.  I then assumed that the space character from Moby Dick would align with the three most frequent values from the cipher text value frequency distribution.  The key is then retrieved and the plaintext can be reconstructed.

## Run-time
Unknown.
