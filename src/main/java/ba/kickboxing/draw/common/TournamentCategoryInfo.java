package ba.kickboxing.draw.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Describes a discipline as present in a tournament. A discipline on a
 * tournament may have specific age categories, weight categories, etc.
 * 
 * @author hosmanag
 * 
 */
public class TournamentCategoryInfo {
	// age ----> (sex ----> (list of weight categories))
	private Map<String, Map<String, List<String>>> data = new HashMap<String, Map<String, List<String>>>();

	public Set<String> getAgeCategories() {
		return data.keySet();
	}

	public List<String> getWeightCategories(String age, String sex) {
		List<String> weightCategories = null;

		Map<String, List<String>> age2sex2weightMapping = data.get(age);

		if (age2sex2weightMapping != null) {
			weightCategories = age2sex2weightMapping.get(sex);
		}

		return weightCategories != null ? weightCategories : Collections.EMPTY_LIST;
	}

	/**
	 * Add weight categories for this age and sex.
	 * @param age
	 * @param sex
	 * @param weightCategories
	 */
	public void put(String age, String sex, List<String> weightCategories) {
		Map<String, List<String>> sex2weightMapping = null;
		
		// check if we already have information about weight categories for this age
		sex2weightMapping = data.get(age);
		
		if (sex2weightMapping == null) {
			sex2weightMapping = new HashMap<String, List<String>>();
		}
		
		sex2weightMapping.put(sex, weightCategories);
		
		data.put(age, sex2weightMapping);
		
	}

}
