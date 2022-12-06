# book-shelf

## Introduction

Over the past weeks I spent time creating this book-shelf app, I employed a github workflow as follow: Issue - branch - pull request - merge - close issue - delete branch.
Everytime I intended to add new functionality a new issue was added.
<br><br>
This book-shelf app was based off previously completed notes app but enforced 2 models Author and Book. The relationship between Author and Book are 1 to many. Each author can hold an array of books.
<br><br>
JUnit testing was of huge importance for this assignment making sure all functionality was passing tests.
<br><br>
This app is built using Kotlin and is only my third attempt at coding in Kotlin. This app is also the first console Kotlin app I completed from scratch.

## Requirements
I built it as a console application in IntelliJ

## Features
- Add an author
- Delete an author
- Update an author
- Add a book
- Update a book
- Delete a book
- Mark book as owned
- Search by author first name
- Search by author email
- Search by book title
- Search by book genre
- Search by book length
- List all authors
- List authors books
- List authors by publisher
- List authors by surname
- List owned books
- List books by length
- List books by rating
- Count all authors
- Count authors by publisher
- Count authors by surname
- Return an author dashboard
- Save data
- Load data


## References
A lot of this project was reconfiguring code we were already given however there were a few references I used
- To help me add colour to the console output I used: [https://discuss.kotlinlang.org/t/printing-in-colors/22492](https://discuss.kotlinlang.org/t/printing-in-colors/22492)
  and to get more ANSI colour variables I used: [https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html](https://discuss.kotlinlang.org/t/printing-in-colors/22492)
- I used an ascii text generator to create the entry screen [https://patorjk.com/software/taag/#p=display&f=Slant&t=Notes%20App](https://patorjk.com/software/taag/#p=display&f=Slant&t=Notes%20App)
- I used an ascii art archive to get the book artwork [https://www.asciiart.eu/books/books](https://www.asciiart.eu/books/books)
- To set up the biography length validator: [https://www.educative.io/answers/how-to-find-the-length-of-a-string-in-java](https://www.educative.io/answers/how-to-find-the-length-of-a-string-in-java)
- Email validation regex: [https://www.baeldung.com/java-email-validation-regex](https://www.baeldung.com/java-email-validation-regex)
- Various functions: [https://www.cosmiclearn.com/kotlin/arraylist.php](https://www.cosmiclearn.com/kotlin/arraylist.php)
<br><br>
  As I mentioned, a lot of work for this assignment was done purely off lab work and reconfiguring it and the relevant testing.

