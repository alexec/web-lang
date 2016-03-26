package wl;

import org.openqa.selenium.By;
import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import wl.model.ClickOn;
import wl.model.GoTo;
import wl.model.Journey;

import java.net.URI;

@SuppressWarnings("WeakerAccess")
@BuildParseTree
public class JourneyParser extends BaseParser<Object> {
    Journey.JourneyBuilder dto = Journey.builder();

    Rule Journey() {
        return Sequence(
                // TODO -- move to own rule
                "Journey:",
                Whitespace(),
                Name(),
                NewLine(),
                ZeroOrMore(Sequence(Step(), NewLine())),
                EOI,
                push(dto.build())
        );
    }

    Rule NewLine() {
        return Ch('\n');
    }

    Rule Whitespace() {
        return OneOrMore(AnyOf(" \t"));
    }

    Rule Step() {
        return FirstOf(
                GoTo(),
                ClickOn()
        );
    }

    Rule ClickOn() {
        return Sequence(
                "click on",
                Whitespace(),
                Selector(),
                push(dto.step(ClickOn.builder().selector(By.cssSelector(match())).build()))
        );
    }

    Rule Selector() {
        return Chars();
    }

    Rule GoTo() {
        return Sequence(
                "go to",
                Whitespace(),
                Url(),
                push(dto.step(GoTo.builder().url(URI.create(match())).build()))
        );
    }

    Rule Url() {
        return Chars();
    }

    Rule Name() {
        return Sequence(Chars(), push(dto.name(match())));
    }

    Rule Chars() {
        return OneOrMore(TestNot(NewLine()), TestNot(Quote()), ANY);
    }

    Rule Quote() {
        return Ch('\"');
    }

}
