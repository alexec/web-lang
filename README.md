[![Circle CI](https://circleci.com/gh/alexec/web-lang.svg?style=svg)](https://circleci.com/gh/selenium-webdriver-in-practice/source)
# Web Application Testing Lang

A domain specific language for automating and testing web applications.

## Usage

To run tests, you'll need to set-up a Junit runner, within your test sources:

~~~java
@RunWith(JourneysRunner.class)
@ContextConfig(FirefoxConfig.class)
public class JourneyIT {
}
~~~

You'll need to create a config file too, this allows you to choose which driver you're using. If you want to have

~~~java
public class Config {

    public WebDriver webDriver() {
        return new FirefoxDriver();
    }
}
~~~

If you need to do any set-up before a journey, your config can have annotated methods as follows:

~~~java
public class Config {
    @BeforeJourney
    public void beforeJourney(Journey journey) {
        System.out.printf("journey \"%s\" starting%n", journey.getName());
    }

    @AfterJourney
    public void afterJourney(Journey journey) {
        System.out.printf("journey \"%s\" over%n", journey.getName());
    }
    // ...
}
~~~

In the same path as your test (e.g. if your tests are in `src/test/java/myapp` then your resources should be in `src/test/resources/myapp`), you should create journey files. Each file should container one or more journeys:

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

**Page Objects** are a first-class concept, the above journey could be written as follows:

~~~scala
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


You can run a group of tests to those just containing a string:

~~~shell
mvn test-compile failsafe:integration-test failsafe:verify -Dwl.journey=radio
~~~

You can find more examples under [src/test/resources/it](src/test/resounces/it).

| Example                     | Description          |
|-----------------------------|----------------------|
| `capture to "filename.png"` | Capture a screenshot |
|                             |                      |
|                             |                      |

## Road-map

* Windows.
* Prompts.
* Confirm.
* Multi-select.
* Clicking by link text.
* Tags.
* Full support for changing driver at runtime.

## Resources

* https://github.com/sirthias/parboiled/wiki
* http://www.hascode.com/2014/01/creating-grammar-parsers-in-java-and-scala-with-parboiled/
* http://www.alexecollins.com/antlr4-and-maven-tutorial/
* https://github.com/SeleniumHQ/fluent-selenium
* http://selenide.org
* http://www.gebish.org
* http://www.scalatest.org/user_guide/using_selenium
