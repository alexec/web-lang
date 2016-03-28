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

You can find more examples under [src/test/resources/it](src/test/resources/it).

## Selectors

By default, selectors are CSS (as these are fast and versatile). If you want to use a specific selector:

| Example               | As CSS             | Description           |
|--------------------------------------------|-----------------------|
| `id:foo`              | `#myId`            | By ID.                |
| `linkText:foo`        | -                  | By link text.         |
| `partialLinkText:foo` | -                  | By partial link text. |
| `name:foo`            | `*[name='foo']`    | By name.              |
| `tagName:foo`         | `foo`              | By tag name.          |
| `xpath:foo`           | -                  | By xpath.             |
| `foo`                 | `foo`              | By CSS, no prefix.    |

## Road-map

* Support for all locators.
* Standalone run.
* Windows.
* Prompts.
* Confirm.
* Multi-select.
* Clicking by link text.
* Tags.
* Table support. First-class?
* Full support for changing driver at runtime.

## Resources

* https://github.com/sirthias/parboiled/wiki
* http://www.hascode.com/2014/01/creating-grammar-parsers-in-java-and-scala-with-parboiled/
* http://www.alexecollins.com/antlr4-and-maven-tutorial/
* https://github.com/SeleniumHQ/fluent-selenium
* http://selenide.org
* http://www.gebish.org
* http://www.scalatest.org/user_guide/using_selenium
