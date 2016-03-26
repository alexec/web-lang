package wl;

import org.parboiled.Parboiled;
import org.parboiled.errors.ErrorUtils;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;
import wl.model.Journey;

import java.io.IOException;

class JourneyFactory {

    //    String text = new Scanner(in).useDelimiter("\\A").next();
    Journey create(String text) throws IOException {
        JourneyParser parser = Parboiled.createParser(JourneyParser.class);

        ParsingResult<Journey> result = new RecoveringParseRunner<Journey>(parser.Journey()).run(text);
        if (result.hasErrors()) {
            throw new IllegalStateException(ErrorUtils.printParseErrors(result));
        }
        return result.resultValue;
    }

}
