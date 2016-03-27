package wl.infrastructure;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import wl.domain.JourneysBuilder;
import wl.domain.Selector;
import wl.domain.step.examination.*;
import wl.domain.step.interaction.*;

import java.net.URI;
import java.nio.file.Paths;
import java.util.Collections;

@SuppressWarnings("WeakerAccess")
@BuildParseTree
public class JourneysParser extends BaseParser<Object> {
    JourneysBuilder dto = new JourneysBuilder();

    Rule Journeys() {
        return Sequence(
                ZeroOrMore(Page()),
                OneOrMore(Journey()),
                EOI,
                push(dto.build())
        );
    }

    Rule Page() {
        return Sequence(
                PageDescription(), LineTerminator(),
                ZeroOrMore(
                        FirstOf(
                                UrlIs(),
                                ElementIs(),
                                TitleShouldBe(),
                                TitleShouldContain(),
                                TextShouldBe(),
                                AttributeShouldBe(),
                                ShouldBeChecked(),
                                ShouldNotBeChecked(),
                                EMPTY
                        ), LineTerminator()
                )
        );
    }

    Rule PageDescription() {
        return Sequence("Page: ", QuStr(), push(dto.addPage((String) pop())));
    }

    Rule UrlIs() {
        return Sequence("url is ", QuStr(), push((dto.setPageUrl(URI.create((String) pop())))));
    }

    Rule ElementIs() {
        return Sequence("element ", QuStr(), " is ", QuStr(), push((dto.addPageElement((String) pop(), (String) pop()))));
    }

    Rule QuStr() {
        return Sequence(
                Quote(),
                Chars(),
                push(match()),
                Quote()
        );
    }

    Rule Comment() {
        return Sequence("#", OneOrMore(TestNot(NewLine()), ANY));
    }

    Rule Journey() {
        return Sequence(
                JourneyDescription(),
                LineTerminator(),
                OneOrMore(
                        FirstOf(
                                // interactions
                                GoTo(),
                                ClickOn(),
                                Type(),
                                Submit(),
                                Check(),
                                Uncheck(),
                                Select(),
                                ExecuteScript(),
                                DismissAlert(),
                                SwitchToFrameByName(),
                                SwitchToFrameByIndex(),
                                SwitchToDefaultContent(),
                                // inspections
                                PageShouldBe(),
                                TitleShouldBe(),
                                TitleShouldContain(),
                                TextShouldBe(),
                                AttributeShouldBe(),
                                ShouldBeChecked(),
                                ShouldNotBeChecked(),
                                // misc
                                CaptureTo(),
                                EMPTY
                        ),
                        LineTerminator()
                )
        );
    }

    Rule LineTerminator() {
        return Sequence(
                Whitespace(),
                Optional(Comment()),
                NewLine(),
                Whitespace()
        );
    }

    Rule JourneyDescription() {
        return Sequence("Journey: ", QuStr(), push(dto.addJourney((String) pop())));
    }

    Rule TextShouldBe() {
        return Sequence(
                "text of ", QuStr(), " should be ", QuStr(),
                push(dto.addStep(TextOfShouldBe.builder().expectedText((String) pop()).selector(Selector.valueOf((String) pop())).build()))
        );
    }


    Rule Submit() {
        return Sequence("submit", push(dto.addStep(Submit.INSTANCE)));
    }

    Rule Type() {
        return Sequence(
                "type ", QuStr(), " into ", QuStr(),
                push(dto.addStep(Type.builder().selector(Selector.valueOf((String) pop())).text((String) pop()).build()))
        );
    }

    Rule ClickOn() {
        return Sequence("click on ", QuStr(), push(dto.addStep(ClickOn.selector(Selector.valueOf((String) pop())))));
    }

    Rule GoTo() {
        return Sequence("go to ", QuStr(), push(dto.addStep(GoTo.place((String) pop()))));
    }

    Rule Check() {
        return Sequence("check ", QuStr(), push(dto.addStep(Check.selector(Selector.valueOf((String) pop())))));
    }

    Rule Uncheck() {
        return Sequence("uncheck ", QuStr(), push(dto.addStep(Uncheck.selector(Selector.valueOf((String) pop())))));
    }

    Rule Select() {
        return Sequence(
                "select ", QuStr(), " in ", QuStr(),
                push(dto.addStep(Select.builder().selector(Selector.valueOf((String) pop())).values(Collections.singletonList((String) pop())).build()))
        );
    }

    Rule PageShouldBe() {
        return Sequence("page should be ", QuStr(), push(dto.addStep(PageShouldBe.expectedPage((String) pop()))));
    }

    Rule TitleShouldBe() {
        return Sequence("title should be ", QuStr(), push(dto.addStep(TitleShouldBe.expectedTitle((String) pop()))));
    }

    Rule TitleShouldContain() {
        return Sequence("title should contain ", QuStr(), push(dto.addStep(TitleShouldContain.expectedTitle((String) pop()))));
    }

    Rule AttributeShouldBe() {
        return Sequence(
                "attribute ", QuStr(), " of ", QuStr(), " should be ", QuStr(),
                push(dto.addStep(AttributeShouldBe.builder()
                        .expectedValue((String) pop())
                        .selector(Selector.valueOf((String) pop()))
                        .attributeName((String) pop())
                        .build()))
        );
    }

    Rule ShouldBeChecked() {
        return Sequence(QuStr(), " should be checked", push(dto.addStep(ShouldBeChecked.selector(Selector.valueOf((String) pop())))));
    }

    Rule ShouldNotBeChecked() {
        return Sequence(QuStr(), " should not be checked", push(dto.addStep(ShouldNotBeChecked.selector(Selector.valueOf((String) pop())))));
    }

    Rule ExecuteScript() {
        return Sequence("execute script ", QuStr(), push(dto.addStep(ExecuteScript.script((String) pop()))));
    }

    Rule SwitchToFrameByName() {
        return Sequence("switch to frame ", QuStr(), push(dto.addStep(SwitchToFrameByName.name((String) pop()))));
    }

    Rule SwitchToFrameByIndex() {
        return Sequence("switch to frame ", Int(), push(dto.addStep(SwitchToFrameByIndex.index(Integer.parseInt(match())))));
    }

    Rule SwitchToDefaultContent() {
        return Sequence("switch to default content", push(dto.addStep(SwitchToDefaultContent.INSTANCE)));
    }

    Rule DismissAlert() {
        return Sequence("dismiss alert", push(dto.addStep(DismissAlert.INSTANCE)));
    }

    Rule CaptureTo() {
        return Sequence("capture to ", QuStr(), push(dto.addStep(Capture.to(Paths.get((String) pop())))));
    }


    Rule Chars() {
        return OneOrMore(TestNot(NewLine()), TestNot(Quote()), ANY);
    }

    Rule Int() {
        return OneOrMore(CharRange('0', '9'));
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
