package com.mingslife.util;

import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.lionsoul.jcseg.tokenizer.ASegment;
import org.lionsoul.jcseg.tokenizer.core.ADictionary;
import org.lionsoul.jcseg.tokenizer.core.DictionaryFactory;
import org.lionsoul.jcseg.tokenizer.core.IWord;
import org.lionsoul.jcseg.tokenizer.core.JcsegTaskConfig;
import org.lionsoul.jcseg.tokenizer.core.SegmentFactory;

public class JcsegUtil {
	public static Set<String> getWords(String content) {
		Set<String> words = new HashSet<String>();
		try {
//			JcsegTaskConfig config = new JcsegTaskConfig();
			JcsegTaskConfig config = new JcsegTaskConfig(JcsegUtil.class.getResource("/jcseg.properties").openStream());
//			JcsegTaskConfig config = new JcsegTaskConfig("D:/Tomcat/apache-tomcat-8.0.36/me-webapps/LightCMS/WEB-INF/classes/jcseg.properties");
			String[] lexiconPath = config.getLexiconPath();
			if (lexiconPath != null) {
				for (String path : lexiconPath) {
					System.out.println(path);
				}
			} else {
				System.out.println("Empty!!!");
			}
			ADictionary dic = DictionaryFactory.createDefaultDictionary(config, config.isAutoload());
			dic.loadDirectory(lexiconPath[0]);
			ASegment seg = (ASegment) SegmentFactory.createJcseg(JcsegTaskConfig.COMPLEX_MODE, new Object[] {config, dic});
			seg.reset(new StringReader(content));
			IWord word = null;
			while ((word = seg.next()) != null) {
				String value = word.getValue();
				words.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return words;
	}

	public static Set<String> getKeywords(String content) {
		final int KEYWORDS_SIZE = 5;
		long[] keywordFrequency = new long[KEYWORDS_SIZE];
		Map<String, Long> words = new HashMap<String, Long>();
		try {
//			JcsegTaskConfig config = new JcsegTaskConfig();
			JcsegTaskConfig config = new JcsegTaskConfig(JcsegUtil.class.getResource("/jcseg.properties").openStream());
//			JcsegTaskConfig config = new JcsegTaskConfig("D:/Tomcat/apache-tomcat-8.0.36/me-webapps/LightCMS/WEB-INF/classes/jcseg.properties");
			String[] lexiconPath = config.getLexiconPath();
			if (lexiconPath != null) {
				for (String path : lexiconPath) {
					System.out.println(path);
				}
			} else {
				System.out.println("Empty!!!");
			}
			ADictionary dic = DictionaryFactory.createDefaultDictionary(config, config.isAutoload());
//			dic.loadDirectory(lexiconPath[0]);
			dic.loadDirectory("C:/LightCMS/keywords");
//			dic.loadWords(config, dic, lexiconPath[0] + "/lex-admin.lex");
//			dic.loadWords(config, dic, lexiconPath[0] + "/lex-cn-mz.lex");
//			dic.loadWords(config, dic, lexiconPath[0] + "/lex-cn-place.lex");
//			dic.loadWords(config, dic, lexiconPath[0] + "/lex-company.lex");
//			dic.loadWords(config, dic, lexiconPath[0] + "/lex-ecmixed.lex");
//			dic.loadWords(config, dic, lexiconPath[0] + "/lex-festival.lex");
//			dic.loadWords(config, dic, lexiconPath[0] + "/lex-food.lex");
			ASegment seg = (ASegment) SegmentFactory.createJcseg(JcsegTaskConfig.COMPLEX_MODE, new Object[] {config, dic});
			seg.reset(new StringReader(content));
			IWord word = null;
			while ((word = seg.next()) != null) {
				String value = word.getValue();
				if (value.matches("[\\pP‘’“”，。？！：《》『』—…·]") || value.length() <= 1) {
					continue;
				}
				long frequency = 1L;
				if (words.containsKey(value)) {
					frequency = words.get(value) + 1L;
				}
				keywordFrequency = addAndSort(keywordFrequency, frequency);
				words.put(value, frequency);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (long element : keywordFrequency) {
			System.out.print(element + " ");
		}
//		long max = keywordFrequency[0];
		long min = keywordFrequency[KEYWORDS_SIZE - 1];
		Set<String> keywords = new HashSet<String>(KEYWORDS_SIZE);
		for (Map.Entry<String, Long> entry : words.entrySet()) {
			if (entry.getValue() >= min) {
				keywords.add(entry.getKey());
			}
		}
		return keywords;
	}

	private static long[] addAndSort(long[] array, long element) {
		int length = array.length;
		if (element <= array[length - 1] || element == array[0]) {
			return array;
		}
		int index = 0;
		for (int i = 1; i < length; i++) {
			if (element == array[i]) {
				return array;
			} else if (element < array[i - 1] && element > array[i]) {
				index = i;
				break;
			}
		}
		for (int i = length - 1; i > index; i--) {
			array[i] = array[i - 1];
		}
		array[index] = element;
		return array;
	}
}
