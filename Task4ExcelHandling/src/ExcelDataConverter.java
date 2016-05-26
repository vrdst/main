import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ExcelDataConverter {
	public ExcelDataRow convertData(ExcelDataRow dataRow){
	    Map<String, String> countries = new HashMap<>();
	    
		dataRow.setName(dataRow.getName().substring(0, 1).toUpperCase() + dataRow.getName().substring(1).toLowerCase());
		dataRow.setSurName(dataRow.getSurName().substring(0, 1).toUpperCase() + dataRow.getSurName().substring(1).toLowerCase());
		dataRow.setAddress(dataRow.getAddress().substring(0, 1).toUpperCase() + dataRow.getAddress().substring(1).toLowerCase());
		dataRow.setCity(dataRow.getCity().substring(0, 1).toUpperCase() + dataRow.getCity().substring(1).toLowerCase());
		if(dataRow.getCountry().length() == 2){
		    for (String isoCode : Locale.getISOCountries()) {
		        Locale l = new Locale("", isoCode);
		        countries.put(isoCode, l.getDisplayCountry());
		    }
		    dataRow.setCountry(countries.get(dataRow.getCountry()));
		} else if (dataRow.getCountry().length() == 3){
		    for (String unCode : Locale.getISOCountries()) {
		        Locale l = new Locale("", unCode);
		        countries.put(l.getISO3Country(), l.getDisplayCountry());
		    }
		    dataRow.setCountry(countries.get(dataRow.getCountry()));
		}
		return dataRow;
	}
}
