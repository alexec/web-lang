package wl.infrastructure;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import wl.domain.JourneysBuilder;
import wl.domain.step.examination.ShouldBeChecked;
import wl.domain.step.examination.TextOfShouldBe;
import wl.domain.step.examination.TitleShouldBe;
import wl.domain.step.interaction.*;

import java.net.URI;

@SuppressWarnings("WeakerAccess")
@BuildParseTree
public class JourneysParser extends BaseParser<Object> {
    JourneysBuilder dto = new JourneysBuilder();

    Rule Journeys() {
        return Sequence(
                OneOrMore(
                        Sequence(
                                Optional(Whitespace()),
                                FirstOf(
                                        Description(),
                                        GoTo(),
                                        ClickOn(),
                                        Type(),
                                        Submit(),
                                        Title(),
                                        Text(),
                                        Check(),
                                        ShouldBeChecked(),
                                        EMPTY), Optional(Whitespace()), Optional(Comment()), NewLine())
                ),
                EOI,
                push(dto.build())
        );
    }

    Rule Comment() {
        return Sequence("#", OneOrMore(TestNot(NewLine()), ANY));
    }

    Rule Description() {
        return Sequence(
                "Journey: ",
                Quote(),
                Chars(),
                push(dto.addJourney(match())),
                Quote()
        );
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
                push(dto.addStep(TextOfShouldBe.builder().selector((String) pop()).expectedText(match()).build())),
                Quote()
        );
    }

    Rule Title() {
        return Sequence(
                "title should be ",
                Quote(),
                Chars(),
                push(dto.addStep(TitleShouldBe.expectedTitle(match()))),
                Quote()
        );
    }

    Rule Submit() {
        return Sequence("submit", push(dto.addStep(Submit.INSTANCE)));
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
                push(dto.addStep(Type.builder().text((String) pop()).selector(match()).build())),
                Quote()
        );
    }

    Rule ClickOn() {
        return Sequence(
                "click on ",
                Quote(),
                Chars(),
                push(dto.addStep(ClickOn.selector(match()))),
                Quote()

        );
    }

    Rule GoTo() {
        return Sequence(
                "go to ",
                Quote(),
                Url(),
                push(dto.addStep(GoTo.url(URI.create(match())))),
                Quote()
        );
    }

    Rule Check() {
        return Sequence(
                "check ",
                Quote(),
                Chars(),
                push(dto.addStep(Check.selector(match()))),
                Quote()
        );
    }

    Rule ShouldBeChecked() {
        return Sequence(
                Quote(),
                Chars(),
                push(dto.addStep(ShouldBeChecked.selector(match()))),
                Quote(),
                " should be checked"
        );
    }

    Rule Url() {
        return Chars();
    }

    Rule Chars() {
        return OneOrMore(TestNot(NewLine()), TestNot(Quote()), ANY);
    }

    Rule Quote() {
        return Ch('\"');
    }

    Rule Whitespace() {
        return ZeroOrMore(AnyOf(" \t"));
    }

    Rule NewLine() {
        return Ch('\n');
    }

}
