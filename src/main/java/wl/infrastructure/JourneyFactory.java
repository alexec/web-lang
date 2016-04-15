package wl.infrastructure;

import lombok.val;
import org.parboiled.Parboiled;
import org.parboiled.errors.ErrorUtils;
import org.parboiled.parserunners.RecoveringParseRunner;
import wl.domain.Journey;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class JourneyFactory {

    private List<Journey> create(String text) throws JourneyParsingException {
        val parser = Parboiled.createParser(JourneysParser.class);
        val runner = new RecoveringParseRunner<List<Journey>>(parser.Journeys());
        val result = runner.run(text);
        if (result.hasErrors()) {
            throw new JourneyParsingException(ErrorUtils.printParseErrors(result));
        }
        return result.resultValue;
    }

    public List<Journey> create(InputStream in) throws JourneyParsingException {
        return create(new Scanner(in).useDelimiter("\\A").next());
    }
}
