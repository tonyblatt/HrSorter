package application.adapters;

import java.io.IOException;

import org.joda.time.LocalDate;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import application.parser.LineParser;

/**
 * Adapter for converting Dates to Json.
 * 
 * @author tony
 *
 */
public class DateJsonAdapter extends TypeAdapter<LocalDate> {

	@Override
	public void write(JsonWriter out, LocalDate value) throws IOException {
		out.value(value.toString(LineParser.DATE_FORMAT));
	}

	@Override
	public LocalDate read(JsonReader in) throws IOException {
		throw new UnsupportedOperationException("Not implemented");
	}
}
