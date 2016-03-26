[![Circle CI](https://circleci.com/gh/alexec/web-lang.svg?style=svg)](https://circleci.com/gh/selenium-webdriver-in-practice/source)
# Web Lang

A domain specific language for automating and testing web applications.

Inspired by Cucumber, but tailed completely for automating the testing of web applications.

You can run a group of tests to those just containing a string:

~~~
mvn test-compile failsafe:integration-test failsafe:verify -Dwl.journey=radio
~~~

## Road-map

* Frames.
* Windows.
* Clicking by link text.
* Tags.
* First-class page objects.
* Full support for changing driver at runtime.

## Resources

* https://github.com/sirthias/parboiled/wiki
* http://www.hascode.com/2014/01/creating-grammar-parsers-in-java-and-scala-with-parboiled/
* http://www.alexecollins.com/antlr4-and-maven-tutorial/
* https://github.com/SeleniumHQ/fluent-selenium
* http://selenide.org
* http://www.gebish.org
* http://www.scalatest.org/user_guide/using_selenium