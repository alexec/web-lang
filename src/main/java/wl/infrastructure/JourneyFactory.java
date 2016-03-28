package wl.infrastructure;

import org.parboiled.Parboiled;
import org.parboiled.errors.ErrorUtils;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;
import wl.domain.Journey;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class JourneyFactory {

    private List<Journey> create(String text) throws JourneyParsingException {
        JourneysParser parser = Parboiled.createParser(JourneysParser.class);
        RecoveringParseRunner<List<Journey>> runner = new RecoveringParseRunner<>(parser.Journeys());
        ParsingResult<List<Journey>> result = runner.run(text);
        if (result.hasErrors()) {
            throw new JourneyParsingException(ErrorUtils.printParseErrors(result));
        }
        return result.resultValue;
    }

    public List<Journey> create(InputStream in) throws JourneyParsingException {
        return create(new Scanner(in).useDelimiter("\\A").next());
    }
}
