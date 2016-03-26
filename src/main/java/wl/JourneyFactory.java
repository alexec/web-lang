package wl;

import org.parboiled.Parboiled;
import org.parboiled.errors.ErrorUtils;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;
import wl.model.Journey;

import java.io.InputStream;
import java.util.Scanner;

class JourneyFactory {

    private Journey create(String text) {
        JourneyParser parser = Parboiled.createParser(JourneyParser.class);
        RecoveringParseRunner<Journey> runner = new RecoveringParseRunner<>(parser.Journey());
        ParsingResult<Journey> result = runner.run(text);
        if (result.hasErrors()) {
            throw new IllegalStateException(ErrorUtils.printParseErrors(result));
        }
        return result.resultValue;
    }

    Journey create(InputStream in) {
        return create(new Scanner(in).useDelimiter("\\A").next());
    }
}
