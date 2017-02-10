package com.mingslife.web.patch;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.databind.ObjectMapper;

@Deprecated
public class StringEscapeObjectMapper extends ObjectMapper {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public StringEscapeObjectMapper() {
		getJsonFactory().setCharacterEscapes(new MyCharacterEscapes());
	}

	private static class MyCharacterEscapes extends CharacterEscapes {
		private static final long serialVersionUID = 1L;
		
		private final int[] asciiEscapes;
		
		@Override
		public int[] getEscapeCodesForAscii() {
			return asciiEscapes;
		}
		
		@Override
		public SerializableString getEscapeSequence(int ch) {
			return null;
		}
		
		public MyCharacterEscapes() {
			int[] esc = CharacterEscapes.standardAsciiEscapesForJSON();
			esc['<'] = CharacterEscapes.ESCAPE_STANDARD;
			esc['>'] = CharacterEscapes.ESCAPE_STANDARD;
			esc['&'] = CharacterEscapes.ESCAPE_STANDARD;
			esc['\''] = CharacterEscapes.ESCAPE_STANDARD;
			asciiEscapes = esc;
		}
	}
}
