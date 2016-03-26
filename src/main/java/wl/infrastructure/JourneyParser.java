package wl.infrastructure;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import wl.domain.*;

import java.net.URI;

@SuppressWarnings("WeakerAccess")
@BuildParseTree
public class JourneyParser extends BaseParser<Object> {
    Journey.JourneyBuilder dto = Journey.builder();

    Rule Journey() {
        return Sequence(
                Description(),
                NewLine(),
                ZeroOrMore(Sequence(Step(), NewLine())),
                EOI,
                push(dto.build())
        );
    }

    Rule Description() {
        return Sequence("Journey:", Whitespace(), Name());
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
                ClickOn(),
                Type(),
                Submit(),
                Title()
        );
    }

    Rule Title() {
        return Sequence(
                "title",
                Whitespace(),
                "should be",
                Whitespace(),
                Quote(),
                Chars(),
                push(dto.step(TitleShouldBe.builder().expectedTitle(match()).build())),
                Quote()
        );
    }

    Rule Submit() {
        return Sequence("submit", push(dto.step(Submit.INSTANCE)));
    }

    Rule Type() {
        return Sequence(
                "type",
                Whitespace(),
                Quote(),
                Chars(),
                push(match()),
                Quote(),
                Whitespace(),
                "into",
                Whitespace(),
                Quote(),
                Chars(),
                push(dto.step(Type.builder().text((String) pop()).selector(match()).build())),
                Quote()
        );
    }

    Rule ClickOn() {
        return Sequence(
                "click on",
                Whitespace(),
                Quote(),
                Chars(),
                push(dto.step(ClickOn.selector(match()))),
                Quote()

        );
    }

    Rule GoTo() {
        return Sequence(
                "go to",
                Whitespace(),
                Quote(),
                Url(),
                push(dto.step(GoTo.builder().url(URI.create(match())).build())),
                Quote()
        );
    }

    Rule Url() {
        return Chars();
    }

    Rule Name() {
        return Sequence(Quote(), Chars(), push(dto.name(match())), Quote());
    }

    Rule Chars() {
        return OneOrMore(TestNot(NewLine()), TestNot(Quote()), ANY);
    }

    Rule Quote() {
        return Ch('\"');
    }

}
