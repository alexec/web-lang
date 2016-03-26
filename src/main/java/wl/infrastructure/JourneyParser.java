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
                OneOrMore(
                        Sequence(
                                FirstOf(
                                        Description(),
                                        GoTo(),
                                        ClickOn(),
                                        Type(),
                                        Submit(),
                                        Title(),
                                        Text(),
                                        EMPTY), Optional(Whitespace()), Optional(Comment()), NewLine())
                ),
                EOI,
                push(dto.build())
        );
    }


    Rule Whitespace() {
        return AnyOf(" \t");
    }

    Rule Comment() {
        return Sequence("#", OneOrMore(TestNot(NewLine()), ANY));
    }

    Rule Description() {
        return Sequence("Journey: ", Name());
    }

    Rule NewLine() {
        return Ch('\n');
    }

    Rule Text() {
        return Sequence(
                "text of ",
                Quote(),
                Chars(),
                push(match()),
                Quote(),
                " should be ",
                Quote(),
                Chars(),
                push(dto.step(TextOfShouldBe.builder().selector((String) pop()).expectedText(match()).build())),
                Quote()
        );
    }

    Rule Title() {
        return Sequence(
                "title should be ",
                Quote(),
                Chars(),
                push(dto.step(TitleShouldBe.expectedTitle(match()))),
                Quote()
        );
    }

    Rule Submit() {
        return Sequence("submit", push(dto.step(Submit.INSTANCE)));
    }

    Rule Type() {
        return Sequence(
                "type ",
                Quote(),
                Chars(),
                push(match()),
                Quote(),
                " into ",
                Quote(),
                Chars(),
                push(dto.step(Type.builder().text((String) pop()).selector(match()).build())),
                Quote()
        );
    }

    Rule ClickOn() {
        return Sequence(
                "click on ",
                Quote(),
                Chars(),
                push(dto.step(ClickOn.selector(match()))),
                Quote()

        );
    }

    Rule GoTo() {
        return Sequence(
                "go to ",
                Quote(),
                Url(),
                push(dto.step(GoTo.url(URI.create(match())))),
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
