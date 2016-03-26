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
                DescriptionLine(),
                OneOrMore(
                        FirstOf(StepLine(), CommentLine(), BlankLine())
                ),
                EOI,
                push(dto.build())
        );
    }

    Rule DescriptionLine() {
        return Sequence(Description(), NewLine());
    }

    Rule BlankLine() {
        return NewLine();
    }

    Rule StepLine() {
        return Sequence(Step(), NewLine());
    }

    Rule CommentLine() {
        return Sequence(Whitespace(), Comment(), NewLine());
    }

    Rule Comment() {
        return Sequence("#", OneOrMore(TestNot(NewLine()), ANY));
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
                Title(),
                Text()

        );
    }

    Rule Text() {
        return Sequence(
                "text of",
                Whitespace(),
                Quote(),
                Chars(),
                push(match()),
                Quote(),
                Whitespace(),
                "should be",
                Whitespace(),
                Quote(),
                Chars(),
                push(dto.step(TextOfShouldBe.builder().selector((String) pop()).expectedText(match()).build())),
                Quote()
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
                push(dto.step(TitleShouldBe.expectedTitle(match()))),
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
