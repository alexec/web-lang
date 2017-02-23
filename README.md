[![Build Status](https://travis-ci.org/alexec/web-lang.svg?branch=master)](https://travis-ci.org/alexec/web-lang)
# Web Application Testing Lang

A domain specific language for automating and testing web applications.

## Build

To build this:

~~~shell
mvn install
~~~

## Command Line Usage

You need to create journey files, for example:

~~~ruby
Journey: "Searching on Google"

    # the standard Google search

    go to "http://www.google.com"
    click on "input[name=q]"
    type "Cheese!" into "input[name=q]"
    submit
    title should be "Cheese! - Google Search"
    text of "input[name=q]" should be "Cheese!"
~~~

Then, to run this test:

~~~shell
~/web-lang $ java -Dwl.path=google-search.journey -jar ~/.m2/repository/web-lang/web-lang/1.0.0-SNAPSHOT/web-lang-1.0.0-SNAPSHOT-jar-with-dependencies.jar
Journey: Searching on Google
	go to "http://www.google.com" ... PASS
	click on "input[name='q']" ... PASS
	type "Cheese!" into "input[name='q']" ... PASS
	submit ... PASS
	title should be "Cheese! - Google Search" ... PASS
	text of "input[name='q']" should be "Cheese!" ... PASS
~~~

If you want to use another browser add these options:

~~~
-Dwebdriver.chrome.driver=/path/to/chromedriver -Dwl.browser=chrome
~~~

~~~
-Dwebdriver.gecko.driver=/path/to/geckodriver -Dwl.browser=firefox
~~~

The **root path** can be set using `wl.root`, and they URLs can be relative to that path:

~~~
-Dwl.root=http://localhost:8080
~~~

~~~shell
    go to "/search" 
~~~


**Page Objects** are a first-class concept, the above journey could be written as follows:

~~~ruby
Page: "Search"

    url is "http://www.google.com"

    # you can define elements that are on the page for resuse

    element "query" is "input[name='q']"

Page: "Results"

    # you can have rules that are used to check the page is what you expect

    title should contain "Google Search"

Journey: "Searching on Google"

    go to "Search"
    # if a selector starts with "@" then it is an alias to an element on the current page
    click on "@query"
    type "Cheese!" into "@query"
    submit
    # this checks the pages is the page you expect
    page should be "Results"
    title should be "Cheese! - Google Search"
    text of "@query" should be "Cheese!"
~~~

If you only want to run some journeys:

~~~ruby
mvn test-compile failsafe:integration-test failsafe:verify -Dwl.journey=radio
~~~

# Examples

| Example                                                                                          | Description                                             |
|--------------------------------------------------------------------------------------------------|---------------------------------------------------------|
| `capture to "filename.png"`                                                                      | Capture a screenshot                                    |
| `attribute "id" of "#footer" should be "footer"`                                                 | Check an attribute's value.                             |
| `check "input[type='checkbox']:nth-of-type(1)"`                                                  | Make sure a check-box is checked.                       |
| `"input[type='checkbox']:nth-of-type(1)" should be checked`                                      | Verify a check-box .                                    |
| `uncheck "input[type='checkbox']:nth-of-type(2)"`                                                | Make sure a check-box is not checked.                   |
| `"input[type='checkbox']:nth-of-type(2)" should not be checked`                                  | Verify that a check-box is not checked.                 |
| `click on "input[type='radio'][value='No']" "#radioId" should be checked`                        | Check a radio-button, then make sure it was checked.    |
| `select "Olives" in "select[name='ingredients[]']"`                                              | Select a list option.                                   |
| `attribute "selected" of "select[name='ingredients[]'] option[value='Olives']" should be "true"` | Make sure a option is selected.                         |
| `execute script "alert('hello');"`                                                               | Execute JavaScript.                                     |
| `switch to frame "frame-top"`                                                                    | Switch to a frame by name.                              |
| `switch to frame 0`                                                                              | Switch to a frame based on index.                       |
| `switch to default content`                                                                      | Switch back to default content after switch to a frame. |
| `dismiss alert`                                                                                  | Dismiss an alert.                                       |
| `page should be "page"`                                                                          | Page should match.                                      |
| `text of "p" should be "text"`                                                                   | Text should be equal.                                   |
| `text of "p" should match "regexp"`                                                              | Text should be match.                                   |

You can find more examples under [src/test/resources/it](src/test/resources/it).

## Selectors

By default, selectors are CSS (as these are fast and versatile). If you want to use a specific selector:

| Example               | As CSS             | Description           |
|-----------------------|--------------------|-----------------------|
| `id:foo`              | `#myId`            | By ID.                |
| `linkText:foo`        | -                  | By link text.         |
| `partialLinkText:foo` | -                  | By partial link text. |
| `name:foo`            | `*[name='foo']`    | By name.              |
| `tagName:foo`         | `foo`              | By tag name.          |
| `xpath:foo`           | -                  | By xpath.             |
| `className:foo`       | `.foo`             | By class name.        |
| `foo`                 | `foo`              | By CSS, no prefix.    |

## Java Usage

To run tests, you'll need to have a test within your test sources:

~~~java
package mytests;

@RunWith(JourneysRunner.class)
public class JourneyIT {
}
~~~

This will run journeys in the class path of that class. In the above can it would look in `src/test/resources/mytests`.


## Road-map

* Windows.
* Prompts.
* Confirm.
* Multi-select.
* Tags.
* Table support. First-class?

## Resources

* https://github.com/sirthias/parboiled/wiki
* http://www.hascode.com/2014/01/creating-grammar-parsers-in-java-and-scala-with-parboiled/
* http://www.alexecollins.com/antlr4-and-maven-tutorial/
* https://github.com/SeleniumHQ/fluent-selenium
* http://selenide.org
* http://www.gebish.org
* http://www.scalatest.org/user_guide/using_selenium
* https://github.com/cucumber/cucumber-jvm/blob/master/junit/src/main/java/cucumber/runtime/junit/FeatureRunner.java
* https://github.com/cucumber/cucumber-jvm/blob/20db608a5535850139ba25fcdb9be3ae46991855/junit/src/main/java/cucumber/runtime/junit/ExecutionUnitRunner.java
