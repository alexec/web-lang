package wl;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import wl.model.GoTo;
import wl.model.Journey;

import java.net.URI;

@SuppressWarnings("WeakerAccess")
@BuildParseTree
public class JourneyParser extends BaseParser<Object> {
    Journey.JourneyBuilder dto = Journey.builder();

    Rule Journey() {
        return Sequence(
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
        return Sequence("go to", Whitespace(), Url(), push(dto.step(GoTo.builder().url(URI.create(match())).build())));
    }

    Rule Url() {
        return Chars();
    }

    Rule Name() {
        return Sequence(Chars(), push(dto.name(match())));
    }

    Rule Chars() {
        return OneOrMore(TestNot(NewLine()), ANY);
    }

}
